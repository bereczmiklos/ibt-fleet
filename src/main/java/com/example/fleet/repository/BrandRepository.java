package com.example.fleet.repository;

import java.util.List;

import com.example.fleet.model.Brand;
import com.example.fleet.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Integer> {

    Brand findById(int id);
}