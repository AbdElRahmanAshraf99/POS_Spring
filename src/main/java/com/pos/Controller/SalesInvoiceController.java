package com.pos.Controller;

import com.pos.Domain.Customer;
import com.pos.Domain.SalesInvoice;
import com.pos.Repository.CustomerRepository;
import com.pos.Repository.SalesInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salesInvoice")
public class SalesInvoiceController
{
	@Autowired
	private SalesInvoiceRepository salesInvoiceRepository;

	@GetMapping("/all")
	public Map<String, Object> getAllCustomers()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", List.of("Code", "Creation Date", "Customer"));
		List<SalesInvoice> salesInvoices = (List<SalesInvoice>) salesInvoiceRepository.findAll();
		List<Map<String, String>> values = new ArrayList<>();
		for (SalesInvoice salesInvoice : salesInvoices)
		{
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("Id", salesInvoice.getId().toString());
			resultMap.put("Code", salesInvoice.getCode());
			resultMap.put("Creation Date", salesInvoice.getCreationDate().toLocalDate().toString());
			resultMap.put("Customer", salesInvoice.getCustomer().getCode());
			values.add(resultMap);
		}
		result.put("values", values);
		return result;
	}

	@GetMapping("/fieldsInfo")
	public Map<String, Object> getSalesInvoiceFieldsInfo()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", List.of("Code", "Creation Date", "Customer"));
		Map<String, String> fieldsInfo = new HashMap<>();
		fieldsInfo.put("Code", "text");
		fieldsInfo.put("Creation Date", "datetime");
		fieldsInfo.put("Customer", "text");
		result.put("info", fieldsInfo);
		return result;
	}
}
