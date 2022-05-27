package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.SongCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongCategoryRepository extends JpaRepository<SongCategory, Long> {

    @Query(value = "select s from SongCategory s")
    List<SongCategory> listSongCategory();

}