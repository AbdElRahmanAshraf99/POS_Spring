package com.pos.Repository;

import com.pos.Domain.SalesInvoiceLine;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface SalesInvoiceLineRepository extends CrudRepository<SalesInvoiceLine, UUID>
{
}