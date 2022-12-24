package com.pos.Repository;

import com.pos.Domain.Customer;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID>
{
}