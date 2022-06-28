package ru.geekbrains.hw9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.hw9.model.Category;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
