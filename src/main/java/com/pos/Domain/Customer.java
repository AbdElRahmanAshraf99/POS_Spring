package com.pos.Domain;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Customer extends BaseEntity
{
	private String name;
	private Address address;
}
