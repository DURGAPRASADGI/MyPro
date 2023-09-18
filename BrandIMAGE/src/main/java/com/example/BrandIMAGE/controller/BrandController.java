package com.example.BrandIMAGE.controller;

import java.io.BufferedWriter;
import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.BrandIMAGE.Repository.BrandRepository;
import com.example.BrandIMAGE.ResponseData.ResponseData;
import com.example.BrandIMAGE.Service.BrandService;
import com.example.BrandIMAGE.entity.Brand;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {
	@Autowired
	private BrandService brandService;

	@Autowired
	private BrandRepository brandRepository;

	@PostMapping("/upload")
public ResponseData CreateBrand(@RequestParam("file") MultipartFile file) throws Exception {
	Brand brand=brandService.createBrand(file);
	String downloadUri=ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(brand.getId()).toUriString();
	
	return new ResponseData(brand.getFileName(),downloadUri,brand.getFileType(),file.getSize(),brand.getId());
}

	@GetMapping("/download/{fileid}")
	public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable String fileid) throws Exception{
		Brand brand=brandService.getBrand(fileid);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(brand.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"brand;fileName=\""+brand.getFileName()+"\"")
				.body(new ByteArrayResource(brand.getData()));
	}
	
	@GetMapping("brand/{fileid}")
	public ResponseData getBrand(@PathVariable("fileid") String fileid) throws Exception {
		Brand brand=brandService.getBrand(fileid);
		
		String downloadUri=ServletUriComponentsBuilder.fromCurrentContextPath().path("download").path(brand.getId()).toUriString();
		
		return  new ResponseData(brand.getFileName(), downloadUri, brand.getFileType(), (long) brand.getData().length,brand.getId());
	}
	
	@GetMapping("getAllBrands")
	public List<ResponseData> getAllBrands(){
		List<Brand> brands=brandService.getAllBrands();
		List<ResponseData> responseDataList=new ArrayList<>();
		for(Brand brand:brands) {
			String downloadUri=ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(brand.getId()).toUriString();
		responseDataList.add(new ResponseData(brand.getFileName(),downloadUri,brand.getFileType(),(long) brand.getData().length,brand.getId()));
		}
		
		return responseDataList;
	}
	@DeleteMapping("removeBrand/{fileid}")
	public ResponseEntity<String> deleteBrand(@PathVariable("fileid") String fileid) throws Exception{
		try {
			brandService.deleteBrand(fileid);
return ResponseEntity.noContent().build();
			
		} catch (Exception e) {
			
			// TODO: handle exception
		return ResponseEntity.ok("could not found image");
		}
		
	}
	
	@PutMapping("update/{fileid}")
	public ResponseData Update(@PathVariable String fileid ,@RequestParam("file") MultipartFile file) throws Exception {
		Brand brand=brandService.getBrand(fileid);
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
	brand.setFileName(fileName);
	brand.setFileType(file.getContentType());
	brand.setData(file.getBytes());
	brandRepository.save(brand);
		
String downloaddUri=ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileid).toUriString();

		return new ResponseData(brand.getFileName(), downloaddUri, brand.getFileType(), (long)brand.getData().length, brand.getId());
	}
	
}
