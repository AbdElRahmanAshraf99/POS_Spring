package com.pos.Controller;

import com.pos.Domain.Item;
import com.pos.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/item")
public class ItemController
{
	@Autowired
	private ItemRepository itemRepository;

	@GetMapping("/all")
	public Map<String, Object> getAllItems()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", Arrays.asList("Code", "Creation Date", "Name", "Unit Price"));
		List<Item> items = (List<Item>) itemRepository.findAll();
		List<Map<String, String>> values = new ArrayList<>();
		for (Item item : items)
		{
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("Id", item.getId().toString());
			resultMap.put("Code", item.getCode());
			resultMap.put("Creation Date", item.getCreationDate().toLocalDate().toString());
			resultMap.put("Name", item.getName());
			resultMap.put("Unit Price", item.getUnitPrice().toString());
			values.add(resultMap);
		}
		result.put("values", values);
		return result;
	}

	@PostMapping("/save")
	public Item addItem(@RequestBody Item item)
	{
		return itemRepository.save(item);
	}

	@PostMapping("/delete")
	public Boolean deleteItem(@RequestBody String id)
	{
		Optional<Item> object = itemRepository.findById(Long.valueOf(id));
		if (object.isPresent())
		{
			itemRepository.delete(object.get());
			return true;
		}
		return false;
	}

}
