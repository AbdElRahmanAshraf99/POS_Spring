package com.pos.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class SalesInvoice extends BaseEntity
{
	@ManyToOne
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "salesInvoice_id")
	private List<SalesInvoiceLine> lines;
}
