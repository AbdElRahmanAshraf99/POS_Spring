package com.pos.Domain;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Item extends BaseEntity
{
	private String name;
	private BigDecimal unitPrice;
}
