package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
