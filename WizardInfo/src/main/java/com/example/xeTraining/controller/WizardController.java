package com.example.xeTraining.controller;

import com.example.xeTraining.entity.WizardInfo;
import com.example.xeTraining.repository.WizardInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/demo/wizard")
public class WizardController
{
    @Autowired
    private final WizardInfoRepository wizardRepo;

    public WizardController(WizardInfoRepository wizardRepo) {
        this.wizardRepo = wizardRepo;
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<?> test(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(wizardRepo.findById(id).get());
    }

    @GetMapping("/wizardList")
    public List<WizardInfo> getWizardList ()
    {
        return wizardRepo.findAll();
    }
}
