package inu.appcenter.chanhee.repository;

import inu.appcenter.chanhee.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
