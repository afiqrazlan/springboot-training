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
    private WizardService wizardService;

    //public WizardController(WizardService wizardService) {this.wizardService = wizardService;}

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

    @GetMapping("/getDate/{date}")
    public List<WizardInfo> getWizardByDate(@PathVariable String date)
    {
        return wizardService.getWizardByDate(date);
    }

    @GetMapping("/getStatus/{status}")
    public List<WizardInfo> getWizardByStatus(@PathVariable boolean status)
    {
        return wizardService.getWizardByStatus(status);
    }

    @GetMapping("/getAgeRange/{age1}-{age2}")
    public List<WizardInfo> getWizardByAgeBetween(@PathVariable(value = "age1") int age1, @PathVariable(value = "age2") int age2)
    {
        return wizardService.getWizardByAgeBetween(age1, age2);
    }
}
