package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.entity.SongUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongUserRepository extends JpaRepository<SongUser, Long> {
    List<SongUser> findBySong(Song song);

    @Query("select su.song.songId from SongUser su where su.user.userId = ?1")
    List<Long> listSongOfUser(long userId);
}