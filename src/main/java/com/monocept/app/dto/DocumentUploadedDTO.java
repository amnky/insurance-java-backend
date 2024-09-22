package com.monocept.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentUploadedDTO {
	
	
	private Long documentId;

    @NotBlank(message = "Document name is mandatory")
    private String documentType;

    private Boolean isApproved;

    private Long customerId;
    
    private Long agentId;
    
    private Long policyId;
    
    private String imageBase64;
}
