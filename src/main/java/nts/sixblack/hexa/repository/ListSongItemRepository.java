package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.ListSong;
import nts.sixblack.hexa.entity.ListSongItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListSongItemRepository extends JpaRepository<ListSongItem, Long> {
    List<ListSongItem> findByListSong(ListSong listSong);
}