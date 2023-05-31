package org.java.project.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.QueryParameter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.Query;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {
	@Autowired
	private ServicePizza servicePizza;
	
	@Autowired
	private ServiceOfferte serviceOfferte;
	
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
		Optional<Pizza> optOfferte = servicePizza.findByIdWithOfferte(id);
		Pizza pizza = optPizza.get();
		Pizza pizzaOfferte = optOfferte.get();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("pizzaOfferte", pizzaOfferte.getOfferte());
		
		return "pizza";
	}
	
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
		
		model.addAttribute("pizza", pizza);
		
		return "pizza-update";
	}
	
	
	@PostMapping("/pizza/update/{id}")
	public String updatePizza(
			Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "pizza-update";
		}
		
		servicePizza.save(pizza);
		
		return "redirect:/";
	}
}
