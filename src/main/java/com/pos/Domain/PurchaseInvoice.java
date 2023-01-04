package com.pos.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PurchaseInvoice extends BaseEntity
{
	@ManyToOne
	private Supplier supplier;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "salesInvoice_id")
	private List<SalesInvoiceLine> lines;

}
