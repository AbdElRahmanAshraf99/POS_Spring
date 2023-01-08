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
	public List<Map<String, String>> getAllCustomers()
	{
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		List<Map<String, String>> resultList = new ArrayList<>();
		for (Customer customer : customers)
		{
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("Id", customer.getId().toString());
			resultMap.put("Code", customer.getCode());
			resultMap.put("Creation Date", customer.getCreationDate().toLocalDate().toString());
			resultMap.put("Name", customer.getName());
			resultMap.put("Address", customer.getAddress().getAddress());
			resultMap.put("City", customer.getAddress().getCity());
			resultMap.put("Country", customer.getAddress().getCountry());
			resultMap.put("ZIP Code", customer.getAddress().getZipCode());
			resultList.add(resultMap);
		}
		return resultList;
	}
}
