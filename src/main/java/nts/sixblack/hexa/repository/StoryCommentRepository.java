package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.StoryComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryCommentRepository extends JpaRepository<StoryComment, Long> {
}