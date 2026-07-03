package br.com.gestao.api.category.repository;

import br.com.gestao.api.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
