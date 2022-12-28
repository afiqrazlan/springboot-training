package com.example.xeTraining.service;

import com.example.xeTraining.entity.WizardInfo;
import com.example.xeTraining.exception.WizardNotFoundException;
import com.example.xeTraining.repository.WizardInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class WizardService
{
    @Autowired
    private final WizardInfoRepository wizardRepo;

    public WizardService(WizardInfoRepository wizardRepo) {
        this.wizardRepo = wizardRepo;
    }

    public WizardInfo getWizardById(Long id)
    {
        return wizardRepo.findById(id).orElseThrow(() -> new WizardNotFoundException("No wizard with ID: " + id));
    }

    public List<WizardInfo> getWizardList ()
    {

        return wizardRepo.findAll();
    }

    public void addWizardInfo(WizardInfo wizardInfo)
    {
        wizardInfo.setJoined_date(String.valueOf(java.time.LocalDate.now()));
        wizardInfo.setStatus(true);
        wizardRepo.save(wizardInfo);
    }

    public void updateWizardInfo(Long id,WizardInfo wizardInfo)
    {
        WizardInfo wizardInfoUpdated = new WizardInfo();
        String joinedDate = String.valueOf(wizardInfo.getJoined_date());
        wizardInfoUpdated.setId(id);
        wizardInfoUpdated.setName(wizardInfo.getName());
        wizardInfoUpdated.setAge(wizardInfo.getAge());
        wizardInfoUpdated.setStatus(wizardInfo.getStatus());
        wizardInfoUpdated.setJoined_date(joinedDate);

        wizardRepo.save(wizardInfoUpdated);
    }

    public void deleteWizardInfo(Long id)
    {
        wizardRepo.deleteById(id);
    }
}
