package com.example.xeTraining.controller;

import com.example.xeTraining.entity.WizardInfo;
import com.example.xeTraining.service.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> test(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(wizardService.getWizardById(id));
    }

    @GetMapping("/wizardList")
    public List<WizardInfo> findWizardList ()
    {
        return wizardService.getWizardList();
    }

    @PostMapping("/add")
    public void addWizard(@RequestBody WizardInfo wizardInfo)
    {
        wizardService.addWizardInfo(wizardInfo);
    }

    @PutMapping("/update/{id}")
    public void updateWizard(@PathVariable Long id, @RequestBody WizardInfo wizardInfo)
    {
        wizardService.updateWizardInfo(id, wizardInfo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWizard(@PathVariable(value = "wizard_id") Long id)
    {
        wizardService.deleteWizardInfo(id);
    }
}
