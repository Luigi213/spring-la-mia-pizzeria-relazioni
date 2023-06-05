package org.java.project.serv;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Offerte;
import org.java.project.pojo.Pizza;
import org.java.project.repo.RepoOfferte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceOfferte {
	@Autowired
	private RepoOfferte repoOfferte;
	
	public List<Offerte> findAll() {
		
		return repoOfferte.findAll();
	}
	public Offerte save(Offerte offerte) {
		
		return repoOfferte.save(offerte);
	}
	public Optional<Offerte> findById(int id) {
		return repoOfferte.findById(id);
	}
	public void delete(Offerte offerte) {
		repoOfferte.delete(offerte);
	}
}
