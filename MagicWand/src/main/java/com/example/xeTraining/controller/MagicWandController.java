package com.example.xeTraining.controller;

import com.example.xeTraining.entity.MagicWandCatalogue;
import com.example.xeTraining.service.MagicWandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demo/magic-wand")
public class MagicWandController
{
    @Autowired
    private final MagicWandService magicWandService;

    public MagicWandController(MagicWandService magicWandService) {
        this.magicWandService = magicWandService;
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<?> test(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(magicWandService.getMagicWandById(id));
    }

    @GetMapping("/wandList")
    public List<MagicWandCatalogue> getList ()
    {
        return magicWandService.getMagicWandList();
    }

    @PostMapping("/add")
    public void addMagicWand(@RequestBody MagicWandCatalogue magicWand)
    {
        magicWandService.addMagicWandInfo(magicWand);
    }

    @PutMapping("/update/{id}")
    public void updateMagicWand(@PathVariable Long id, @RequestBody MagicWandCatalogue magicWand)
    {
        magicWandService.updateMagicWandInfo(id, magicWand);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMagicWand(@PathVariable(value = "wand_id") Long id)
    {
        magicWandService.deleteMagicWandInfo(id);
    }


}
