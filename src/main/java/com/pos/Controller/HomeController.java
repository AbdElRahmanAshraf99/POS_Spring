package com.pos.Controller;

import com.pos.Generator.ControllerUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HomeController
{
	@GetMapping("/allModules")
	public Map<String, Object> getAllModules()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("modules", Arrays.asList("Basic", "Invoices"));
		Map<String, String> customerMap = new HashMap();
		customerMap.put("name", "Customer");
		customerMap.put("link", "customer");
		Map<String, String> supplierMap = new HashMap();
		supplierMap.put("name", "Supplier");
		supplierMap.put("link", "supplier");
		Map<String, String> itemMap = new HashMap();
		itemMap.put("name", "Item");
		itemMap.put("link", "item");
		Map<String, String> userMap = new HashMap();
		userMap.put("name", "User");
		userMap.put("link", "user");
		result.put("Basic", Arrays.asList(customerMap, supplierMap, itemMap, userMap));
		Map<String, String> purchaseInvoiceMap = new HashMap();
		purchaseInvoiceMap.put("name", "Purchase Invoice");
		purchaseInvoiceMap.put("link", "purchaseInvoice");
		Map<String, String> salesInvoiceMap = new HashMap();
		salesInvoiceMap.put("name", "Sales Invoice");
		salesInvoiceMap.put("link", "salesInvoice");
		result.put("Invoices", Arrays.asList(purchaseInvoiceMap, salesInvoiceMap));
		return result;
	}

	@GetMapping("/{entity}/fieldsInfo")
	public List<Map<String, Object>> fetchEntityFieldsInfo(@PathVariable String entity)
	{
		try
		{
			String className = "com.pos.Domain." + entity.substring(0, 1).toUpperCase() + entity.substring(1);
			return ControllerUtils.fetchClassFieldsInfo(Class.forName(className));
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}
}
