package com.Avani.ECommerceApp.repositories;

import com.Avani.ECommerceApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {
}
