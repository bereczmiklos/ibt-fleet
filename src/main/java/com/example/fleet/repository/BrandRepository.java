package com.example.fleet.repository;

import com.example.fleet.model.Brand;
import com.example.fleet.model.BrandName;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    List<Brand> findAll();

    Brand findById(int id);

    Brand findByName(BrandName name);
}