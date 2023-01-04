package com.pos.Repository;

import com.pos.Domain.PurchaseInvoice;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface PurchaseInvoiceRepository extends CrudRepository<PurchaseInvoice, UUID>
{
}