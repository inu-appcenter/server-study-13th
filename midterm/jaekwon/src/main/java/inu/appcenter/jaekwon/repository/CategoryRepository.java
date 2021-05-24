package inu.appcenter.jaekwon.repository;

import inu.appcenter.jaekwon.domain.Category;
import inu.appcenter.jaekwon.domain.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByContent(String content);

    List<Category> findAllByStatus(CategoryStatus status);
}
