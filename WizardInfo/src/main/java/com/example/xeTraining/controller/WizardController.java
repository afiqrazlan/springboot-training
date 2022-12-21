package com.example.xeTraining.controller;

import com.example.xeTraining.entity.WizardInfo;
import com.example.xeTraining.repository.WizardInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public void addWizardInfo(@RequestBody WizardInfo wizardInfo)
    {
        wizardRepo.save(wizardInfo);
    }

    @PutMapping("/update/{id}")
    public void updateWizardInfo(@PathVariable Long id, @RequestBody WizardInfo wizardInfo)
    {
        WizardInfo wizardInfoUpdated = new WizardInfo();
        wizardInfoUpdated.setId(id);
        wizardInfoUpdated.setName(wizardInfo.getName());
        wizardInfoUpdated.setAge(wizardInfo.getAge());
        wizardInfoUpdated.setStatus(wizardInfo.getStatus());
        wizardInfoUpdated.setJoined_date(wizardInfo.getJoined_date());

        wizardRepo.save(wizardInfoUpdated);
    }

    @PostMapping("/delete/{id}")
    public void deleteWizardInfo(@PathVariable(value = "wizard_id") Long id)
    {
        wizardRepo.deleteById(id);
    }
}
