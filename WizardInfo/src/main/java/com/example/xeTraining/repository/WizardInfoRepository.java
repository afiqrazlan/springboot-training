package com.example.xeTraining.repository;

import com.example.xeTraining.entity.WizardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardInfoRepository extends JpaRepository<WizardInfo, Long> {
}
