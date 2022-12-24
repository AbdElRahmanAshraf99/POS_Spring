package com.pos.Domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address
{
	String country;
	String city;
	String address;
	String zipCode;
}
