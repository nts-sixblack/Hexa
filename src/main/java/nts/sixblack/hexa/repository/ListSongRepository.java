package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.ListSong;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListSongRepository extends JpaRepository<ListSong, Long> {
    ListSong findByListSongId(long listSongId);
    List<ListSong> findByUser(User user);
}