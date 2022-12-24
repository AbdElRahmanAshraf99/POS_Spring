package com.pos.Repository;

import com.pos.Domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemRepository extends CrudRepository<Item, UUID>
{
}
