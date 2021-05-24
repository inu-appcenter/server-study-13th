package inu.appcenter.chanhee.repository;

import inu.appcenter.chanhee.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
