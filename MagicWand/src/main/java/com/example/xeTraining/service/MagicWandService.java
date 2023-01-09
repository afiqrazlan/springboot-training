package com.example.xeTraining.service;

import com.example.xeTraining.entity.MagicWandCatalogue;
import com.example.xeTraining.exception.WandNotFoundException;
import com.example.xeTraining.repository.MagicWandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagicWandService 
{
    @Autowired
    private final MagicWandRepository magicWandRepo;

    public MagicWandService(MagicWandRepository magicWandRepo) {
        this.magicWandRepo = magicWandRepo;
    }
    
    public List<MagicWandCatalogue> getMagicWandList ()
    {
        return magicWandRepo.findAll();
    }

    public MagicWandCatalogue getMagicWandById(Long id)
    {
        return magicWandRepo.findById(id).orElseThrow(() -> new WandNotFoundException("No wand with ID: " + id));
    }
    
    public void addMagicWandInfo(MagicWandCatalogue magicWand)
    {
        magicWandRepo.save(magicWand);
    }

    public void updateMagicWandInfo( Long id, MagicWandCatalogue magicWand)
    {
        if(getMagicWandById(id) != null)
        {
            MagicWandCatalogue magicWandUpdated = new MagicWandCatalogue();
            magicWandUpdated.setId(id);
            magicWandUpdated.setName(magicWand.getName());
            magicWandUpdated.setDesc(magicWand.getDesc());
            magicWandUpdated.setStock(magicWand.getStock());
            magicWandUpdated.setAge_limit(magicWand.getAge_limit());

            magicWandRepo.save(magicWandUpdated);
        }
    }
    
    public void deleteMagicWandInfo(Long id)
    {
        if(getMagicWandById(id) != null)
        {
            magicWandRepo.deleteById(id);
        }
    }
}
