package junsulime.cloud.board.domain;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    Memo save(Memo memo);

    Optional<Memo> findById(long id);

    List<Memo> findBy(MemoSpecification specification);
}
