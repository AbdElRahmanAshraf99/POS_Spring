package com.pos.Domain;

import com.pos.Generator.FieldInfo;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Customer extends BaseEntity
{
	@FieldInfo(required = true, order = 1)
	private String name;
	@FieldInfo(embeddable = true)
	private Address address;
}
