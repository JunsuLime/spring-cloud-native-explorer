package junsulime.cloud.jdbc.impl;

import junsulime.cloud.jdbc.Memo;
import junsulime.cloud.jdbc.MemoRepository;
import junsulime.cloud.jdbc.MemoSpecification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcMemoRepository implements MemoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcMemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("memo")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Memo save(Memo memo) {
        if (isNewEntity(memo)) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("title", memo.getTitle());
            parameters.put("content", memo.getContent());
            long id = simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
            setId(memo, id);
            return memo;
        }
        jdbcTemplate.update("update memo set " +
                "title = ?" +
                "content = ?" +
                " value (?, ?)", memo.getTitle(), memo.getContent());
        return memo;
    }

    @Override
    public Optional<Memo> findById(long id) {
        final Memo memo = jdbcTemplate.queryForObject("select id, title, content from memo where id = ?",
                (rs, rowNum) ->
                        new Memo(
                                rs.getLong("id"),
                                rs.getString("title"),
                                rs.getString("content")),
                id);
        return Optional.ofNullable(memo);
    }

    @Override
    public List<Memo> findBy(MemoSpecification specification) {
        return jdbcTemplate.query("select id, title, content from memo where title = ?",
                (rs, rowNum) ->
                        new Memo(
                                rs.getLong("id"),
                                rs.getString("title"),
                                rs.getString("content")),
                specification.getTitle());
    }

    private boolean isNewEntity(Memo memo) {
        return memo.getId() == 0;
    }

    private void setId(Memo memo, long id) {
        try {
            final Class<? extends Memo> clazz = memo.getClass();
            final Field fieldId = clazz.getDeclaredField("id");
            fieldId.setAccessible(true);
            fieldId.setLong(memo, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
