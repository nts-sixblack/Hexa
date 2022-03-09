package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.entity.SongCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    Song findBySongId(long songId);
    List<Song> findBySongCategory(SongCategory songCategory);
}