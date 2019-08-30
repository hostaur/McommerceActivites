package com.clientui.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.clientui.beans.ExpeditionBean;
import com.clientui.beans.ProductBean;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-expedition")
public interface MicroserviceExpeditionProxy {

    @PostMapping(value = "/microservice-expedition/expedition")
    ResponseEntity<ExpeditionBean> expedierUneCommande(@RequestBody ExpeditionBean expedition);
    
    @PutMapping(value = "/microservice-expedition/expedition")
    ResponseEntity<ExpeditionBean> modifierExpeditionDUneCommande(@RequestBody ExpeditionBean expedition);
    
    @GetMapping( value = "/microservice-expedition/expeditions/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id);

}
