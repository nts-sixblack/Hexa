package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.SongUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongUserRepository extends JpaRepository<SongUser, Long> {
}