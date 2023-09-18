package com.example.BrandIMAGE.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.BrandIMAGE.entity.Brand;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {

}
