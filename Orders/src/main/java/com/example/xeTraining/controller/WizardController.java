package com.example.xeTraining.controller;

import com.example.xeTraining.service.WizardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo/wizard")
public class WizardController
{
    @Autowired
    private final WizardService wizardService;

    public WizardController(WizardService wizardService) {
        this.wizardService = wizardService;
    }

    @GetMapping("/wizardList")
    public JsonNode getWizardList() throws JsonProcessingException
    {
        return wizardService.getWizardList();
    }

    @GetMapping("/wizard/{id}")
    public ResponseEntity<?> getWizardByID(@PathVariable (value = "id") Long id)
    {
        return wizardService.getWizardByID(id);
    }
}
