package org.java.project.pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nomeIngrediente;
	
	@ManyToMany(mappedBy="ingredientes")
	private List<Pizza> pizza;
	
	public Ingrediente() {}
	
	public Ingrediente(String nomeIngrediente) {
		setNomeIngrediente(nomeIngrediente);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeIngrediente() {
		return nomeIngrediente;
	}
	public void setNomeIngrediente(String nomeIngrediente) {
		this.nomeIngrediente = nomeIngrediente;
	}
	public List<Pizza> getPizza() {
		return pizza;
	}
	public void setPizza(List<Pizza> pizza) {
		this.pizza = pizza;
	}

	@Override
	public String toString() {
		return "nome dell'ingrediente" + getNomeIngrediente();
	}
}
