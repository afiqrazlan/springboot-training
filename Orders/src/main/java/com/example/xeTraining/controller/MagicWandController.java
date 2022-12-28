package com.example.xeTraining.controller;

import com.example.xeTraining.service.MagicWandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo/magic-wand")
public class MagicWandController
{
    @Autowired
    private final MagicWandService magicWandService;

    public MagicWandController(MagicWandService magicWandService) {
        this.magicWandService = magicWandService;
    }

    @GetMapping("/wandList")
    public JsonNode testTemplateWandList() throws JsonProcessingException {
        return magicWandService.getWandList();
    }

    @GetMapping("/wizard/{id}")
    public ResponseEntity<?> getWandByID(@PathVariable(value = "id") Long id)
    {
        return magicWandService.getWandByID(id);
    }
}
