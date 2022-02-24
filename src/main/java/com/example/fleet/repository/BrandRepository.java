package com.example.fleet.repository;

import java.util.List;

import com.example.fleet.model.Brand;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Integer> {

    List<Brand> findAll();

    Brand findById(int id);
}