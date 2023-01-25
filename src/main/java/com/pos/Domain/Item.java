package com.pos.Domain;

import com.pos.Generator.FieldInfo;
import lombok.Data;

import javax.persistence.Entity;
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
