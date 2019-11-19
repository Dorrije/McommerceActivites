package com.mcommerce.microserviceexpedition.web.controller;

import com.mcommerce.microserviceexpedition.dao.ExpeditionDao;
import com.mcommerce.microserviceexpedition.model.Expedition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ExpeditionController {

    @Autowired
    ExpeditionDao expeditionDao;


    //ajouter une nouvelle expédition à la base de données
    @PostMapping(value="/Expedition")
    public ResponseEntity<Void> saveExpedition(@RequestBody Expedition exp) {

        Expedition expeditionCreated= expeditionDao.save(exp);
         if (expeditionCreated ==null)
             return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(expeditionCreated.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    //récupérer une expédition par son id.
    @GetMapping(value = "/Expeditions/{id}")
    public Expedition getExpeditionByID(@PathVariable int id )
    {
     Expedition exp = expeditionDao.findById(id);

    return  exp;
    }


    //mettre à jour une expédition
    @PutMapping(value="/Expedition/update/")
    public void updateExpedition(@RequestBody Expedition exp) {

        expeditionDao.save(exp);

    }
}
