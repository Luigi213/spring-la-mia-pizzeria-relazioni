package org.java.project.serv;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Pizza;
import org.java.project.repo.RepoPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePizza {
	@Autowired
	private RepoPizza repoPizza;
	
	public List<Pizza> findAll() {
		
		return repoPizza.findAll();
	}
	public Pizza save(Pizza pizza) {
		
		return repoPizza.save(pizza);
	}
	public Optional<Pizza> findById(int id) {
		
		return repoPizza.findById(id);
	}
	public List<Pizza> findByName(String name){
		return repoPizza.findByNameContaining(name);
	}
	public void delete(Pizza pizza) {
		repoPizza.delete(pizza);
	}
}
