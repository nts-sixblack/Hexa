package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsUser;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Posts findByPostsId(long postsId);
    @Query("select p from Posts p")
    List<Posts> getAll();

    @Query("select p from Posts p order by p.postsId")
    List<Posts> listNumberPost(Pageable pageable);

    @Query("select p.postsId " +
            "from Posts p join p.postsUserList pu " +
            "where pu.user in (select f.userRecipient from Follow f where f.userSender = ?1 and f.status = TRUE )" +
            "order by p.dateCreate")
    List<Long> listPostShow(User userSender);

    @Query("select p.postsId " +
            "from Posts p join p.postsUserList pu " +
            "where pu.user in (select f.userRecipient from Follow f where f.userSender = ?1 and f.status = TRUE )" +
            "order by p.dateCreate")
    List<Long> listNumberPostShow(User userSender, Pageable pageable);


}