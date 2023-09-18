package com.example.BrandIMAGE.ResponseData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
	
	private String fileName;
	private String downloadUri;
	private String fileType;
	private Long fileSize;
    private String Token;


}
