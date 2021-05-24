package com.example.midterm.repository;

import com.example.midterm.domain.Category;
import com.example.midterm.domain.status.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByContent(String content);

    List<Category> findAllByStatus(CategoryStatus status);
}
