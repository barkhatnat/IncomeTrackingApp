package ru.barkhatnat.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.barkhatnat.entity.Category;

import java.util.Collection;


public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.user.id IS NULL")
    Collection<Category> findCategoriesByUserEmpty();

}
