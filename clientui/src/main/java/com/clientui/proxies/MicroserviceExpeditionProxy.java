package com.clientui.proxies;

import com.clientui.beans.ExpeditionBean;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "zuul-server")
@RibbonClient("microservice-expedition")
public interface MicroserviceExpeditionProxy {

    @PostMapping(value="/microservice-expedition/Expedition")
    public ResponseEntity<Void> saveExpedition(@RequestBody ExpeditionBean exp);

    //récupérer une expédition par son id.
    @GetMapping(value = "/microservice-expedition/Expeditions/{id}")
    public ExpeditionBean getExpeditionByID(@PathVariable int id );

    @PutMapping(value="/microservice-expedition/Expedition/update/")
    public void updateExpedition(@RequestBody ExpeditionBean exp);

}
