package org.java.project.pojo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "name can't be null")
	private String name;
	@NotBlank(message = "description can't be null")
	private String description;
	@NotBlank(message = "urlPhoto can't be null")
	private String urlPhoto;
	@DecimalMin(value = "0.1", message = "price can't be 0")
	private double price;
	
	@OneToMany(mappedBy="pizza")
	private List<Offerte> Offerte;
	
	@ManyToMany
	private List<Ingrediente> ingredientes;
	
	public Pizza () {};
	public Pizza (String name, String description, String urlPhoto, double price, Ingrediente... ingredientes) {
		setName(name);
		setDescription(description);
		setUrlPhoto(urlPhoto);
		setPrice(price);
		
		setIngredientes(ingredientes);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrlPhoto() {
		return urlPhoto;
	}
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<Offerte> getOfferte() {
		return Offerte;
	}
	public void setOfferte(List<Offerte> offerte) {
		Offerte = offerte;
	}
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	public void setIngredientes(Ingrediente[] ingredientes) {
		setIngredientes(Arrays.asList(ingredientes));
	}
	public void addIngrediente(Ingrediente ingrediente) {
		getIngredientes().add(ingrediente);
	}
	public void removeIngrediente(Ingrediente ingrediente) {
		getIngredientes().remove(ingrediente);
	}
	
	public String formatPrice() {
		double amount = getPrice();
		String newPrice = String.format("%,.2f", amount) + "€";
		return newPrice;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] " 
				+ "name: " + getName()
				+ " description: " + getDescription()
				+ " urlPhoto: " + getUrlPhoto()
				+ " price: " + formatPrice();
	}
}
