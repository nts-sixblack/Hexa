package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}