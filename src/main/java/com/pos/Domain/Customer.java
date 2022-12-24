package com.pos.Domain;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Customer extends BaseEntity
{
	String name;
	Address address;
}
