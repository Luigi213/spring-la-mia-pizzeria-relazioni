package org.java.project.repo;

import org.java.project.pojo.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoIngrediente extends JpaRepository<Ingrediente, Integer>{
	
}
