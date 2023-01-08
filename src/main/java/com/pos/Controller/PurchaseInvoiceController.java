package com.pos.Controller;

import com.pos.Domain.PurchaseInvoice;
import com.pos.Repository.PurchaseInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchaseInvoice")
public class PurchaseInvoiceController
{
	@Autowired
	private PurchaseInvoiceRepository purchaseInvoiceRepository;

	@GetMapping("/all")
	public List<Map<String, String>> getAllCustomers()
	{
		List<PurchaseInvoice> purchaseInvoices = (List<PurchaseInvoice>) purchaseInvoiceRepository.findAll();
		List<Map<String, String>> resultList = new ArrayList<>();
		for (PurchaseInvoice purchaseInvoice : purchaseInvoices)
		{
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("Id", purchaseInvoice.getId().toString());
			resultMap.put("Code", purchaseInvoice.getCode());
			resultMap.put("Creation Date", purchaseInvoice.getCreationDate().toLocalDate().toString());
			resultMap.put("Supplier", purchaseInvoice.getSupplier().getCode());
			resultList.add(resultMap);
		}
		return resultList;
	}

}
