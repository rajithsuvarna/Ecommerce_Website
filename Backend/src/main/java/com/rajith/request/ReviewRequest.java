package com.rajith.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReviewRequest {

	private Long productId;
	private String review;
}
