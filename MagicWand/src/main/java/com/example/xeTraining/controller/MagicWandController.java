package com.example.xeTraining.controller;

import com.example.xeTraining.entity.MagicWandCatalogue;
import com.example.xeTraining.repository.MagicWandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public void addMagicWand(@RequestBody MagicWandCatalogue magicWand)
    {
        wandRepo.save(magicWand);
    }

    @PutMapping("/update/{id}")
    public void updateMagicWand(@PathVariable Long id, @RequestBody MagicWandCatalogue magicWand)
    {
        MagicWandCatalogue magicWandUpdated = new MagicWandCatalogue();
        magicWandUpdated.setId(id);
        magicWandUpdated.setName(magicWand.getName());
        magicWandUpdated.setDesc(magicWandUpdated.getDesc());
        magicWandUpdated.setStock(magicWand.getStock());
        magicWandUpdated.setAge_limit(magicWand.getAge_limit());

        wandRepo.save(magicWandUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMagicWand(@PathVariable(value = "wand_id") Long id)
    {
        wandRepo.deleteById(id);
    }


}
