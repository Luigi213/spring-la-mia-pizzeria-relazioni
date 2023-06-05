package org.java.project.controller;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Ingrediente;
import org.java.project.pojo.Pizza;
import org.java.project.serv.ServiceIngrediente;
import org.java.project.serv.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {
	@Autowired
	ServiceIngrediente serviceIngrediente;
	
	@Autowired
	ServicePizza servicePizza;
	
	@GetMapping
	public String getIndex(Model model) {
		
		List<Ingrediente> ingredienti = serviceIngrediente.findAll();
		
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingrediente-index";
	}
	
	@GetMapping("/create")
	public String createIngrediente(
			Model model) {
		List<Pizza> pizzas = servicePizza.findAll();
		
		model.addAttribute("ingredienti", new Ingrediente());
		model.addAttribute("pizzas", pizzas);
		
		return "ingrediente-create";
	}
	
	@PostMapping("/create")
	public String storeIngrediente(@ModelAttribute Ingrediente ingredientes) {
		
		serviceIngrediente.save(ingredientes);
		
		for (Pizza p : ingredientes.getPizza()) {
			
			p.addIngrediente(ingredientes);
			servicePizza.save(p);
		}
		
		return "redirect:/ingrediente";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePizza(
			@PathVariable("id") int id
			) {
		
		Optional<Ingrediente> optIngrediente = serviceIngrediente.findById(id);
		Ingrediente ingrediente = optIngrediente.get();
		
		for(Pizza pizza : ingrediente.getPizza()) {
			
			pizza.removeIngrediente(ingrediente);
			servicePizza.save(pizza);
		}
		
		serviceIngrediente.delete(ingrediente);
		
		
		return "redirect:/ingrediente";
	}
	
	
}
