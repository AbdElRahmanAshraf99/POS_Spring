package com.pos.Controller;

import com.pos.Domain.Item;
import com.pos.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		result.put("fields", List.of("Code", "Creation Date", "Name", "Unit Price"));
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

	@GetMapping("/fieldsInfo")
	public Map<String, Object> getItemFieldsInfo()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", List.of("Code", "Name", "Unit Price"));
		Map<String, String> fieldsInfo = new HashMap<>();
		fieldsInfo.put("Code", "text");
		fieldsInfo.put("Name", "text");
		fieldsInfo.put("Unit Price", "number");
		result.put("info", fieldsInfo);
		return result;
	}

}
