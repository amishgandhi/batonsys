package com.bsys.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bsys.web.model.Client;
import com.bsys.web.service.ClientService;

@Controller
public class ClientController {

	@Autowired
	ClientService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showClientList(ModelMap model) {
		model.put("clients", service.retrieveClients());
		return "list-clients";
	}	
	
	@RequestMapping(value = "/list-clients", method = RequestMethod.GET)
	public String showClients(ModelMap model) {
		model.put("clients", service.retrieveClients());
		return "list-clients";
	}	
	
	@RequestMapping(value = "/toggle-client", method = RequestMethod.GET)
	public String disconnectClient(@RequestParam int id) {

		service.toggleConnection(id);
		return "redirect:/list-clients";
	}
	
	@RequestMapping(value = "/add-client", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("client", new Client(0, "Enter Name"));
		return "client";
	}
	
	@RequestMapping(value = "/add-client", method = RequestMethod.POST)
	public String addClient(ModelMap model, @Valid Client client, BindingResult result) {

		if (result.hasErrors()) {
			return "client";
		}

		service.addClient(client.getName());
		return "redirect:/list-clients";
	}	

}
