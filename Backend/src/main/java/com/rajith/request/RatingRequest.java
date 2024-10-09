package com.rajith.request;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rajith.model.Address;
import com.rajith.model.PaymentInformation;
import com.rajith.model.Rating;
import com.rajith.model.Review;
import com.rajith.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RatingRequest {

	private Long productId;
	private double rating;
}
