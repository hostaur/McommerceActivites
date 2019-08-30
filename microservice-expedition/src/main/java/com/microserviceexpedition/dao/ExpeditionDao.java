package com.microserviceexpedition.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microserviceexpedition.model.Expedition;



@Repository
public interface ExpeditionDao extends JpaRepository<Expedition, Integer>{

	Expedition findByIdCommande(int idCommande);
}
