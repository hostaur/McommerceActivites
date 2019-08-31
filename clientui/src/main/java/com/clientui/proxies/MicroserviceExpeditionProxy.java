package com.clientui.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clientui.beans.ExpeditionBean;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-expedition")
public interface MicroserviceExpeditionProxy {
    @GetMapping( value = "/microservice-expedition/expeditions/getByIdCommande/{idCommande}")
    ResponseEntity<ExpeditionBean> etatExpedition(@PathVariable("idCommande") int idCommande);

}
