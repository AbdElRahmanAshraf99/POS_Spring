package com.pos.Repository;

import com.pos.Domain.PurchaseInvoice;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseInvoiceRepository extends CrudRepository<PurchaseInvoice, Long>
{
}