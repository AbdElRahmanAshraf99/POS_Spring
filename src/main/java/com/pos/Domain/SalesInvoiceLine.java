package com.pos.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class SalesInvoiceLine extends BaseLine
{
	@OneToOne
	private Item item;
	private int quantity;
	private int totalPrice;
}
