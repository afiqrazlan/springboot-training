package com.example.xeTraining.repository;

import com.example.xeTraining.entity.MagicWandCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicWandRepository extends JpaRepository<MagicWandCatalogue, Long> {
}
