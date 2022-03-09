package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.entity.SongComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongCommentRepository extends JpaRepository<SongComment, Long> {
    List<SongComment> findBySong(Song song);

}