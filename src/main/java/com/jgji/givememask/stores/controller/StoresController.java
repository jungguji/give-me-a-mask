package com.jgji.givememask.stores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jgji.givememask.stores.model.Store.Stores;
import com.jgji.givememask.stores.service.StoresService;

@Controller
public class StoresController {

    @Autowired
    StoresService service;
    
    @GetMapping(value="/")
    public String home(Model model) {
        return "/index";
    }
    
    @GetMapping(value="/test")
    @ResponseBody
    public ResponseEntity<List<Stores>> test(@RequestParam(value="address") String address) {
        System.out.println("address >>> " + address);
        System.out.println("@@@@@ 서비스 진입 @@@@@@");
        
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.registerModule(new JavaTimeModule());
        objMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        
        List<Stores> list = service.getStoresByAddress(address);
        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
