package org.java.project.serv;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Ingrediente;
import org.java.project.repo.RepoIngrediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceIngrediente {
	@Autowired
	private RepoIngrediente repoIngrediente;
	
	public List<Ingrediente> findAll() {
		
		return repoIngrediente.findAll();
	}
	public Ingrediente save(Ingrediente ingrediente) {
		
		return repoIngrediente.save(ingrediente);
	}
	public Optional<Ingrediente> findById(int id) {
		return repoIngrediente.findById(id);
	}
	public void delete(Ingrediente ingrediente) {
		repoIngrediente.delete(ingrediente);
	}
}
