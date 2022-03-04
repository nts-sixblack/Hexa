package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.SongComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongCommentRepository extends JpaRepository<SongComment, Long> {
}