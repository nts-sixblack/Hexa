package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}