package org.java.project.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	public Pizza () {};
	public Pizza (String name, String description, String urlPhoto, double price) {
		setName(name);
		setDescription(description);
		setUrlPhoto(urlPhoto);
		setPrice(price);
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
