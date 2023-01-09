package com.pos.Controller;

import com.pos.Domain.Customer;
import com.pos.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/all")
	public Map<String, Object> getAllCustomers()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", List.of("Code", "Creation Date", "Name", "Address", "City", "Country"));
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		List<Map<String, String>> values = new ArrayList<>();
		for (Customer customer : customers)
		{
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("Id", customer.getId().toString());
			resultMap.put("Code", customer.getCode());
			resultMap.put("Creation Date", customer.getCreationDate() != null ? customer.getCreationDate().toLocalDate().toString() : "null");
			resultMap.put("Name", customer.getName());
			resultMap.put("Address", customer.getAddress().getAddress());
			resultMap.put("City", customer.getAddress().getCity());
			resultMap.put("Country", customer.getAddress().getCountry());
			values.add(resultMap);
		}
		result.put("values", values);
		return result;
	}

	@GetMapping("/fieldsInfo")
	public Map<String, Object> getCustomerFieldsInfo()
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
