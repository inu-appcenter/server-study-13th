package inu.appcenter.yunah.repository;

import inu.appcenter.yunah.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
