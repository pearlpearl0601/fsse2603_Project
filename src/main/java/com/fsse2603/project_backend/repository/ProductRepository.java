package com.fsse2603.project_backend.repository;

import com.fsse2603.project_backend.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

}

