package org.java.project.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Offerte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@FutureOrPresent(message = "The date must be in the present or in the future")
	private LocalDate start_date;
	
	@FutureOrPresent(message = "The date must be in the present or in the future")
	private LocalDate end_date;
	
	@NotBlank(message = "title can't be null")
	private String title;

	@Min(0)
	@NotNull(message="discount can't be null")
	private Integer discount;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	public Offerte() {}
	public Offerte(LocalDate start_date, LocalDate end_date, String title, int discount, Pizza pizza) {
		setStart_date(start_date);
		setEnd_date(end_date);
		setTitle(title);
		setDiscount(discount);
		setPizza(pizza);
	}

	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public LocalDate getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public String printPriceDiscount() {
		double res =  getPizza().getPrice() - ((getPizza().getPrice() * getDiscount()) / 100f);
		return String.format("%,.2f", res) + "€";
	}

	@Override
	public String toString() {
		return "[" + getId() + "]" 
				+ " titolo: "+ getTitle() + ","
				+ " sconto: " + getDiscount() + "%,"
				+ " data di inizio: " + getStart_date() + ","
				+ " data di fine: " + getEnd_date();
	}
}
