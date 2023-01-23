package com.pos.Repository;

import com.pos.Domain.SalesInvoiceLine;
import org.springframework.data.repository.CrudRepository;

public interface SalesInvoiceLineRepository extends CrudRepository<SalesInvoiceLine, Long>
{
}