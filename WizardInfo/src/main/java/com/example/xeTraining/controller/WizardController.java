package com.example.xeTraining.controller;

import com.example.xeTraining.entity.WizardInfo;
import com.example.xeTraining.exception.ApiError;
import com.example.xeTraining.service.WizardService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/demo/wizard")
public class WizardController
{
    @Autowired
    private final WizardService wizardService;

    public WizardController(WizardService wizardService) {
        this.wizardService = wizardService;
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<WizardInfo> test(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(wizardService.getWizardById(id));
    }

    @GetMapping("/wizardList")
    public List<WizardInfo> findWizardList ()
    {
        return wizardService.getWizardList();
    }

    @PostMapping("/add")
    public void addWizard(@RequestBody @Valid WizardInfo wizardInfo)
    {
        wizardService.addWizardInfo(wizardInfo);
    }

    @PutMapping("/update/{id}")
    public void updateWizard(@PathVariable Long id, @RequestBody @Valid WizardInfo wizardInfo)
    {
        wizardService.updateWizardInfo(id, wizardInfo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWizard(@PathVariable(value = "id") Long id)
    {
        wizardService.deleteWizardInfo(id);
    }
}
