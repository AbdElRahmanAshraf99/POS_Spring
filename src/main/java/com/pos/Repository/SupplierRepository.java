package com.pos.Repository;

import com.pos.Domain.Supplier;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface SupplierRepository extends CrudRepository<Supplier, UUID>
{
}