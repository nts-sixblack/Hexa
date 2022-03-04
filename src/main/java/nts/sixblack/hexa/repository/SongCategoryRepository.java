package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.SongCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongCategoryRepository extends JpaRepository<SongCategory, Long> {
}