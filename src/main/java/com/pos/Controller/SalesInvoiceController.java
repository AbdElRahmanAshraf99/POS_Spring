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
	public List<Map<String, String>> getAllCustomers()
	{
		List<SalesInvoice> salesInvoices = (List<SalesInvoice>) salesInvoiceRepository.findAll();
		List<Map<String, String>> resultList = new ArrayList<>();
		for (SalesInvoice salesInvoice : salesInvoices)
		{
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("Id", salesInvoice.getId().toString());
			resultMap.put("Code", salesInvoice.getCode());
			resultMap.put("Creation Date", salesInvoice.getCreationDate().toLocalDate().toString());
			resultMap.put("Customer", salesInvoice.getCustomer().getCode());
			resultList.add(resultMap);
		}
		return resultList;
	}

}
