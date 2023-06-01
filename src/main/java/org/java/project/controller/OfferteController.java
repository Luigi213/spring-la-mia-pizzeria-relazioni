package org.java.project.controller;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Offerte;
import org.java.project.pojo.Pizza;
import org.java.project.serv.ServiceOfferte;
import org.java.project.serv.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class OfferteController {
	
	@Autowired
	private ServicePizza servicePizza;
	
	@Autowired
	private ServiceOfferte serviceOfferte;
	
	@GetMapping("/new-offerte/create/{id}")
	public String createNewOfferta( 
			Model model,
			@PathVariable("id") int id
		) {
		
		List<Pizza> p = servicePizza.findAll();
		
		model.addAttribute("pizza", p);
		model.addAttribute("id", id);
		model.addAttribute("offerte", new Offerte());
		
		return "new-offerte";
	}
	
	@PostMapping("/new-offerte/create")
	public String storeNewOfferta(
			Model model,
			@Valid @ModelAttribute Offerte offerte,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Pizza> p = servicePizza.findAll();
			
			model.addAttribute("pizza", p);
			
			model.addAttribute("offerte", offerte);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("id", offerte.getPizza().getId());
			
			return "new-offerte";
		}
		
		
		serviceOfferte.save(offerte);
		
		return "redirect:/pizza/" + offerte.getPizza().getId();
	}
	
	@GetMapping("/new-offerte/update/{id}")
	public String editOfferte(
			Model model,
			@PathVariable("id") int id
		) {
		
		List<Pizza> p = servicePizza.findAll();
		model.addAttribute("pizz", p);
		
		Optional<Offerte> optPizza = serviceOfferte.findById(id);
		Offerte offerte = optPizza.get();
		
		model.addAttribute("offerte", offerte);
		
		return "offerte-update";
	}
	
	
	@PostMapping("/new-offerte/update/{id}")
	public String updateOfferte(
			Model model,
			@Valid @ModelAttribute Offerte offerte,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("offerte", offerte);
			model.addAttribute("errors", bindingResult);
			
			return "offerte-update";
		}
		
		serviceOfferte.save(offerte);
		
		return "redirect:/pizza/" + offerte.getPizza().getId();
	}
}
