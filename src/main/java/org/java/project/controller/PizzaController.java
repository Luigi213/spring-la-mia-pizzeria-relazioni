package org.java.project.controller;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Pizza;
import org.java.project.serv.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {
	@Autowired
	private ServicePizza servicePizza;
	
	@GetMapping("/")
	public String getHome(Model model) {
		
		List<Pizza> pizza = servicePizza.findAll();
		
		model.addAttribute("pizzas", pizza);
		
		return "index";
	}
	
	@GetMapping("/pizza/{id}")
	public String getBook(
			Model model,
			@PathVariable("id") int id
	) {
		
		Optional<Pizza> optPizza = servicePizza.findById(id);
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza";
	}
	
	@PostMapping("/pizza/by/name")
	public String getNamePizza(Model model, @RequestParam(required = false) String name) {
		
		List<Pizza> pizza = servicePizza.findByName(name);
		
		model.addAttribute("pizzas", pizza);
		
		return "index";
	}
	
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "pizza-create";
	}
	
	@PostMapping("/pizza/create")
	public String storePizza(
			Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult
			) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "pizza-create";
		}
		
		servicePizza.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(
			@PathVariable("id") int id
		) {
		
		Optional<Pizza> optPizza = servicePizza.findById(id);
		Pizza pizza = optPizza.get();
		
		servicePizza.delete(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/update/{id}")
	public String editPizza(
			Model model,
			@PathVariable("id") int id
		) {
		
		Optional<Pizza> optPizza = servicePizza.findById(id);
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizzas", pizza);
		
		return "pizza-update";
	}
	
	@PostMapping("/pizza/update/{id}")
	public String updatePizza(
			@PathVariable("id") int id,
			@ModelAttribute Pizza pizza
		) {
		
		servicePizza.save(pizza);
		
		return "redirect:/";
	}
}
