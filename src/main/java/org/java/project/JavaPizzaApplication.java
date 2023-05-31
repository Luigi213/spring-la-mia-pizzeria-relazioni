package org.java.project;

import org.java.project.pojo.Pizza;
import org.java.project.serv.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaPizzaApplication implements CommandLineRunner{
	@Autowired
	private ServicePizza servicePizza;

	public static void main(String[] args) {
		SpringApplication.run(JavaPizzaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Pizza p1 = new Pizza("margherita", "pom", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQglASWaP2qUY2CfIBQfpGhEY6vh8m3OgFUig&usqp=CAU", 10);
		Pizza p2 = new Pizza("bufala", "pom", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQglASWaP2qUY2CfIBQfpGhEY6vh8m3OgFUig&usqp=CAU", 10);
		Pizza p3 = new Pizza("diavola", "pom", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQglASWaP2qUY2CfIBQfpGhEY6vh8m3OgFUig&usqp=CAU", 10);
		
		servicePizza.save(p1);
		servicePizza.save(p2);
		servicePizza.save(p3);
	}
}
