package com.pos.Domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address
{
	private String country;
	private String city;
	private String address;
}
