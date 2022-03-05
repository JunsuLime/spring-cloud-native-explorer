package junsulime.cloud.board.adapter;

import junsulime.cloud.board.domain.Memo;
import junsulime.cloud.board.domain.MemoSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class JdbcMemoRepositoryTest {

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    private JdbcMemoRepository jdbcMemoRepository;


    @BeforeEach
    void setUp() throws SQLException {
        jdbcMemoRepository = new JdbcMemoRepository(new JdbcTemplate(dataSource));

        connection = dataSource.getConnection();
        String create = "create table if not exists memo (" +
                "id bigint auto_increment primary key," +
                "title varchar(255)," +
                "content varchar(255))";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(create)) {
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        String drop = "drop table memo if exists";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(drop)) {
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void 메모를_저장한다() {
        final Memo memo = jdbcMemoRepository.save(new Memo("hello", "world"));

        try (final PreparedStatement preparedStatement = connection.prepareStatement("select id, title, content from memo")) {
            final ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                assertEquals(result.getLong("id"), memo.getId());
                assertEquals(result.getString("title"), memo.getTitle());
                assertEquals(result.getString("content"), memo.getContent());
            } else {
                fail();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void 식별자로_메모를_조회한다() {
        final Memo memo = jdbcMemoRepository.save(new Memo("hello", "world"));

        final Memo saved = jdbcMemoRepository.findById(memo.getId()).orElseThrow(NoSuchElementException::new);
        assertEquals(saved.getId(), memo.getId());
    }

    @Test
    void 검색_spec으로_메모를_조회한다() {
        final Memo found1 = jdbcMemoRepository.save(new Memo("hello", "world"));
        final Memo found2 = jdbcMemoRepository.save(new Memo("hello", "bye"));
        final Memo notFound = jdbcMemoRepository.save(new Memo("bye", "world"));

        final List<Memo> found = jdbcMemoRepository.findBy(new MemoSpecification("hello"));
        assertEquals(found.size(), 2);
        assertTrue(found.contains(found1));
        assertTrue(found.contains(found2));
    }
}