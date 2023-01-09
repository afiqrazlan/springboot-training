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
        wizardInfo.setJoinedDate(String.valueOf(java.time.LocalDate.now()));
        wizardInfo.setStatus(true);
        wizardRepo.save(wizardInfo);
    }

    public void updateWizardInfo(Long id,WizardInfo wizardInfo)
    {
        if(getWizardById(id) != null) {
            WizardInfo wizardInfoUpdated = new WizardInfo();
            String joinedDate = "";
            if(wizardInfo.getJoinedDate() != null)
            {   joinedDate = String.valueOf(wizardInfo.getJoinedDate()); }
            else
            {   joinedDate = String.valueOf(getWizardById(id).getJoinedDate()); }
            wizardInfoUpdated.setId(id);
            wizardInfoUpdated.setName(wizardInfo.getName());
            wizardInfoUpdated.setAge(wizardInfo.getAge());
            wizardInfoUpdated.setStatus(wizardInfo.getStatus());
            wizardInfoUpdated.setJoinedDate(joinedDate);

            wizardRepo.save(wizardInfoUpdated);
        }
    }

    public void deleteWizardInfo(Long id)
    {
        if(getWizardById(id) != null)
        {
            wizardRepo.deleteById(id);
        }
    }

    public List<WizardInfo> getWizardByDate(String date)
    {
        return wizardRepo.findWizardsByJoinedDate(date);
    }

    public List<WizardInfo> getWizardByStatus(boolean status)
    {
        return wizardRepo.findWizardsByStatus(status);
    }

    public List<WizardInfo> getWizardByAgeBetween(int age1, int age2)
    {
        return wizardRepo.findWizardsByAgeBetween(age1, age2);
    }
}
