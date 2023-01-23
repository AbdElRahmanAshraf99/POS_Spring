package com.pos.Repository;

import com.pos.Domain.SalesInvoice;
import org.springframework.data.repository.CrudRepository;

public interface SalesInvoiceRepository extends CrudRepository<SalesInvoice, Long>
{
}