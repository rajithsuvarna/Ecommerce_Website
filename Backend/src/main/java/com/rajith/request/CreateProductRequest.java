package com.rajith.request;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rajith.model.Address;
import com.rajith.model.Size;
import com.rajith.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductRequest {

	private String title;
	private String description;
	private int price;
	
	private int discountedPrice;
	
	private int discountPercent;
	
	private int quantity;
	
	private String brand;
	
	private String color;
	
	private Set<Size> size=new HashSet<>();
	
	private String imageUrl;
	
	private String topLevelCategory;
	
	private String secondLevelCategory;
	
	private String thirdLevelCategory;
	
}
