package com.example.xeTraining.repository;

import com.example.xeTraining.entity.WizardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WizardInfoRepository extends JpaRepository<WizardInfo, Long>
{
    List<WizardInfo> findWizardsByJoinedDate(String date);
    List<WizardInfo> findWizardsByStatus(boolean status);

    List<WizardInfo> findWizardsByAgeBetween(int age1, int age2);
}
