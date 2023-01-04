package com.pos.Domain;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Supplier extends BaseEntity
{
	private String name;
	private Address address;
}
