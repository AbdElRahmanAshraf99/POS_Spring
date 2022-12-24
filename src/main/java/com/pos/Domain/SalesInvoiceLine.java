package com.pos.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class SalesInvoiceLine extends BaseEntity
{
	@OneToOne
	Item item;
	int quantity;
	int totalPrice;
}
