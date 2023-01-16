package com.pos.Domain;

import com.pos.Generator.FieldInfo;
import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Item extends BaseEntity
{
	@FieldInfo(required = true, order = 1)
	private String name;
	@FieldInfo(required = true)
	private BigDecimal unitPrice;
}
