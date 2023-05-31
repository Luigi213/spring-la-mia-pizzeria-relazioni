package org.java.project.repo;

import org.java.project.pojo.Offerte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoOfferte extends JpaRepository<Offerte, Integer>{
}
