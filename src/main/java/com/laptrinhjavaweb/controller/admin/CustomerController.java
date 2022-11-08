package com.laptrinhjavaweb.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.impl.CustomerService;
import com.laptrinhjavaweb.service.impl.TransactionService;
import com.laptrinhjavaweb.service.impl.UserService;
import com.laptrinhjavaweb.utils.MessageUtils;
import com.laptrinhjavaweb.utils.VaidationUtils;

@Controller(value = "customerOfAdmin")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private MessageUtils messageUtils;
	
	@RequestMapping(value="/admin/customerlist")
	public ModelAndView customerList(@ModelAttribute("modelSearch") CustomerSearchDTO searchDTO )
	{
		ModelAndView mav = new ModelAndView("admin/customer/customer_list");	
		VaidationUtils.validatePage(searchDTO,mav);
		if(searchDTO.getPage() > 0) {
			Pageable pageable = PageRequest.of(searchDTO.getPage() ,10);
    		ResponseDTO responseDTO = customerService.searchCustomer(searchDTO,pageable);
    		mav.addObject("model", responseDTO);
    		mav.addObject("staffmaps", userService.getUserMaps());
		}
		return mav;
	}
	
	
	@RequestMapping(value="/admin/customeredit")
	public ModelAndView customerEdit(@RequestParam(value="id", required= false) Long id,
									HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("admin/customer/customer_edit");
		CustomerDTO model = new CustomerDTO();
		
		
		if(id != null)
		{
			model = customerService.findById(id);
			List<TransactionDTO> transactionDTOs = transactionService.findTransactionsByCustomerId(id);
			mav.addObject("transactions", transactionDTOs); // lấy các giao dịch của customer 
		}
		
		if(request.getParameter("message")!= null)
		{
			Map<String, String> message = messageUtils.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		
		mav.addObject("model", model);
		mav.addObject("transactiontypemaps", customerService.getTransactionType());
		
		return mav;
	}

	
	@RequestMapping(value="/admin/customerdetail")
	public ModelAndView checkCustomDetail(@RequestParam(value="id") Long customerId)
	{
		CustomerDTO model = new CustomerDTO();
		ModelAndView mav = new ModelAndView("admin/customer/customer_detail");
		model = customerService.findById(customerId);
		List<TransactionDTO> transactionDTOs = transactionService.findTransactionsByCustomerId(customerId);
		mav.addObject("transactions", transactionDTOs);
		mav.addObject("model", model);
		mav.addObject("transactiontypemaps", customerService.getTransactionType());
		return mav;
	}
	
	
}
