package com.pos.Repository;

import com.pos.Domain.PurchaseInvoiceLine;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface PurchaseInvoiceLineRepository extends CrudRepository<PurchaseInvoiceLine, UUID>
{
}