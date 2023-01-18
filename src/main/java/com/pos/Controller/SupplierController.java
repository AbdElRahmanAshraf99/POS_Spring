package com.pos.Controller;

import com.pos.Domain.Customer;
import com.pos.Domain.Supplier;
import com.pos.Generator.ControllerUtils;
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
	public List<Map<String, Object>> getSupplierFieldsInfo()
	{
		return ControllerUtils.fetchClassFieldsInfo(Supplier.class);
	}

	@PostMapping("/save")
	public Supplier addSupplier(@RequestBody Supplier supplier)
	{
		supplier.setCreationDate(LocalDateTime.now());
		return supplierRepository.save(supplier);
	}
}
