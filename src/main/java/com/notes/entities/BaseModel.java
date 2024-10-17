package com.notes.entities;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel {
	private Boolean isActive;
	private Boolean isDeleted;
	private Integer createdBy;
	private Date createdOn;
	private Integer updatedBy;
	private Date updateOn;
}