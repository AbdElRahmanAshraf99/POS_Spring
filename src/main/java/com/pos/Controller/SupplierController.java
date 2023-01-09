package com.pos.Controller;

import com.pos.Domain.Customer;
import com.pos.Domain.Supplier;
import com.pos.Repository.CustomerRepository;
import com.pos.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
public class SupplierController
{
	@Autowired
	private SupplierRepository supplierRepository;

	@GetMapping("/all")
	public Map<String, Object> getAllCustomers()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", List.of("Code", "Creation Date", "Name", "Address", "City", "Country"));
		List<Supplier> suppliers = (List<Supplier>) supplierRepository.findAll();
		List<Map<String, String>> values = new ArrayList<>();
		for (Supplier supplier : suppliers)
		{
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("Id", supplier.getId().toString());
			resultMap.put("Code", supplier.getCode());
			resultMap.put("Creation Date", supplier.getCreationDate().toLocalDate().toString());
			resultMap.put("Name", supplier.getName());
			resultMap.put("Address", supplier.getAddress().getAddress());
			resultMap.put("City", supplier.getAddress().getCity());
			resultMap.put("Country", supplier.getAddress().getCountry());
			values.add(resultMap);
		}
		result.put("values", values);
		return result;
	}

	@GetMapping("/fieldsInfo")
	public Map<String, Object> getSupplierFieldsInfo()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", List.of("Code", "Creation Date", "Name", "Address", "City", "Country"));
		Map<String, String> fieldsInfo = new HashMap<>();
		fieldsInfo.put("Code", "text");
		fieldsInfo.put("Creation Date", "datetime");
		fieldsInfo.put("Name", "text");
		fieldsInfo.put("Address", "text");
		fieldsInfo.put("City", "text");
		fieldsInfo.put("Country", "text");
		result.put("info", fieldsInfo);
		return result;
	}

}
