package com.example.BrandIMAGE.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.BrandIMAGE.Repository.BrandRepository;
import com.example.BrandIMAGE.entity.Brand;
@Service
public class BrandServicesImpl implements BrandService  {
	
	@Autowired
	private BrandRepository brandRepository;
	
	

	@Override
	public Brand createBrand(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		Brand brand=null;
		try {
			if(fileName.contains("....")) {
				throw new Exception("invalid file"+fileName);
			}
			 brand=new Brand(fileName,file.getContentType(),file.getBytes());
			return brandRepository.save(brand);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("please check your file"+fileName);
		}
	}

	@Override
	public Brand getBrand(String fileid) throws Exception {
		// TODO Auto-generated method stub
		return brandRepository.findById(fileid).orElseThrow(()->new Exception("file not found"+fileid));
	}

	@Override
	public List<Brand> getAllBrands() {
		// TODO Auto-generated method stub
		return brandRepository.findAll();
	}

	@Override
	public void deleteBrand(String fileid) {
		// TODO Auto-generated method stub
		brandRepository.deleteById(fileid);
		
	}

	



	

}
