package com.example.xeTraining.controller;

import com.example.xeTraining.entity.MagicWandCatalogue;
import com.example.xeTraining.repository.MagicWandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/demo/magic-wand")
public class MagicWandController
{
    @Autowired
    private final MagicWandRepository wandRepo;

    public MagicWandController(MagicWandRepository wandRepo) {
        this.wandRepo = wandRepo;
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<?> test(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(wandRepo.findById(id).get());
    }

    @GetMapping("/wandList")
    public List<MagicWandCatalogue> getOrderList ()
    {
        return wandRepo.findAll();
    }
}
