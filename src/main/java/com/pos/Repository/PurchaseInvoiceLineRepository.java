package com.pos.Repository;

import com.pos.Domain.PurchaseInvoiceLine;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseInvoiceLineRepository extends CrudRepository<PurchaseInvoiceLine, Long>
{
}