package com.microserviceexpedition.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microserviceexpedition.Exceptions.ExpeditionExistanteException;
import com.microserviceexpedition.Exceptions.ExpeditionImpossibleException;
import com.microserviceexpedition.Exceptions.ExpeditionNotFoundException;
import com.microserviceexpedition.dao.ExpeditionDao;
import com.microserviceexpedition.model.Expedition;



@RestController
public class ExpeditionController {

    @Autowired
    ExpeditionDao ExpeditionDao;

    Logger log = LoggerFactory.getLogger(this.getClass());


    //Récuperer une expedition par son id
    @GetMapping( value = "/expeditions/{id}")
    public Optional<Expedition> recupererUneExpedition(@PathVariable int id) {

        Optional<Expedition> Expedition = ExpeditionDao.findById(id);

        if(!Expedition.isPresent())  throw new ExpeditionNotFoundException("L'expedition correspondant à l'id " + id + " n'existe pas");

        return Expedition;
    }
    
  //Récuperer une expedition par son id
    @GetMapping( value = "/expeditions/getByIdCommande/{idCommande}")
    public Expedition etatExpedition(@PathVariable int idCommande) {

        Expedition Expedition = ExpeditionDao.findByIdCommande(idCommande);

        if(Expedition==null)  throw new ExpeditionNotFoundException("L'expedition correspondant à la commande " + idCommande + " n'existe pas");

        return Expedition;
    }
    
    
    /*
     * Opération pour enregistrer un Expedition et notifier le microservice commandes pour mettre à jour le statut de la commande en question
     **/
     @PostMapping(value = "/expedition")
     public ResponseEntity<Expedition>  envoyerUneCommande(@RequestBody Expedition expedition){


         //Vérifions s'il y a déjà un Expedition enregistrée pour cette commande
    	 Expedition expeditionExistant = ExpeditionDao.findByIdCommande(expedition.getIdCommande());
         if(expeditionExistant!=null)  throw new ExpeditionExistanteException("Cette commande est déjà expedie");

         //Enregistrer l'expedition
         Expedition nouvelleExpedition = ExpeditionDao.save(expedition);

         // si le DAO nous retourne null c'est que il ya eu un problème lors de l'enregistrement
         if(nouvelleExpedition == null) throw new ExpeditionImpossibleException("Erreur, impossible d'Expeditier, réessayez plus tard");
         return new ResponseEntity<Expedition>(nouvelleExpedition, HttpStatus.CREATED);

     }
     
     /*
      * Opération pour enregistrer un Expedition et notifier le microservice commandes pour mettre à jour le statut de la commande en question
      **/
      @PutMapping(value = "/expedition")
      public ResponseEntity<Expedition>  modificationEnvoisCommande(@RequestBody Expedition expedition){


          //Vérifions s'il y a déjà un Expedition enregistrée pour cette commande
    	  Expedition expeditionExistant = ExpeditionDao.findByIdCommande(expedition.getIdCommande());
    	  if(expeditionExistant!=null)  throw new ExpeditionNotFoundException("L'expedition correspondant à l'id \" + id + \" n'existe pas");

          //Enregistrer l'expedition
          Expedition nouvelleExpedition = ExpeditionDao.save(expedition);

          // si le DAO nous retourne null c'est que il ya eu un problème lors de l'enregistrement
          if(nouvelleExpedition == null) throw new ExpeditionImpossibleException("Erreur, impossible d'Expeditier, réessayez plus tard");

          //on renvoi 201 CREATED pour notifier le client au le Expedition à été enregistré
          return new ResponseEntity<Expedition>(nouvelleExpedition, HttpStatus.CREATED);

      }
}
