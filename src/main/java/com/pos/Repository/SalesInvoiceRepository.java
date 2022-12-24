package com.pos.Repository;

import com.pos.Domain.SalesInvoice;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface SalesInvoiceRepository extends CrudRepository<SalesInvoice, UUID>
{
}