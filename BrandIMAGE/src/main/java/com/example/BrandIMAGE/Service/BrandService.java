package com.example.BrandIMAGE.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.BrandIMAGE.entity.Brand;

public interface BrandService {

	Brand createBrand(MultipartFile file) throws Exception;

	Brand getBrand(String fileid) throws Exception;

	List<Brand> getAllBrands();

	

void deleteBrand(String fileid);

}
