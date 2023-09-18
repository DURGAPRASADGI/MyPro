package com.example.BrandIMAGE.update;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandUpdate {
	private String fileName;
    private String fileType;
    private String resetToken;
    @Lob
    private byte[] data;
}
