package com.pos.Domain;

import com.pos.Generator.FieldInfo;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Supplier extends BaseEntity
{
	@FieldInfo(required = true, order = 1)
	private String name;
	@FieldInfo(embeddable = true)
	private Address address;
}
