package com.notes.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryResponse {
	
	private Integer id;
	private String name;
	private String description;

}
