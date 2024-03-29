package com.pos.Controller;

import com.pos.Domain.Customer;
import com.pos.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
		result.put("fields", Arrays.asList("Code", "Creation Date", "Name", "Address", "City", "Country"));
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

	@PostMapping("/save")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		return customerRepository.save(customer);
	}

	@PostMapping("/delete")
	public Boolean deleteCustomer(@RequestBody String id)
	{
		Optional<Customer> object = customerRepository.findById(Long.valueOf(id));
		if (object.isPresent())
		{
			customerRepository.delete(object.get());
			return true;
		}
		return false;
	}
}