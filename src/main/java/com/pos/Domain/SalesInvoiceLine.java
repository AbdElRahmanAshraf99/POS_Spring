package com.pos.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class SalesInvoiceLine extends BaseLine
{
	@OneToOne
	private Item item;
	private int quantity;
	private int totalPrice;
}
