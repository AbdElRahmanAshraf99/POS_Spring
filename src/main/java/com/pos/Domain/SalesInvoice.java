package com.pos.Domain;

import lombok.Data;

import javax.persistence.*;
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
