package com.example.xeTraining.service;

import com.example.xeTraining.model.MagicWandCatalogue;
import com.example.xeTraining.model.Orders;
import com.example.xeTraining.model.WizardInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MagicWandService
{
    RestTemplate restTemplate = new RestTemplate();

    public JsonNode getWandList() throws JsonProcessingException {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8081/api/demo/magic-wand/wandList", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseEntity.getBody());

        return root;
    }

    public ResponseEntity<MagicWandCatalogue> getWandByID(Long id)
    {
        ResponseEntity<MagicWandCatalogue> responseEntity = restTemplate.getForEntity("http://localhost:8081/api/demo/magic-wand/test/" + id, MagicWandCatalogue.class);
        MagicWandCatalogue magicWand = responseEntity.getBody();

        if(magicWand.getId() != null)
        {    return ResponseEntity.ok().body(magicWand); }

        else
        {
            throw new NullPointerException("heh");
        }
    }
}
