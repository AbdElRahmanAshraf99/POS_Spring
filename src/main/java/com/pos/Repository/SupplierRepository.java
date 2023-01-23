package com.pos.Repository;

import com.pos.Domain.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long>
{
}