package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.entity.SongFeel;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongFeelRepository extends JpaRepository<SongFeel, Long> {
    List<SongFeel> findBySong(Song song);

    SongFeel findBySongAndUser(Song song, User user);
}