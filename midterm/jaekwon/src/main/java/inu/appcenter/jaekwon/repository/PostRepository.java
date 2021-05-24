package inu.appcenter.jaekwon.repository;

import inu.appcenter.jaekwon.domain.Post;
import inu.appcenter.jaekwon.model.post.PostWithCommentCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select distinct p from Post p left join fetch p.commentList c where p.id =:postId")
    Optional<Post> findWithCommentsById(@Param("postId") Long postId);

}
