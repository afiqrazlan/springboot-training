package com.example.xeTraining.service;

import com.example.xeTraining.model.WizardInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class WizardService
{
    RestTemplate restTemplate = new RestTemplate();

    public JsonNode getWizardList() throws JsonProcessingException {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8082/api/demo/wizard/wizardList", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseEntity.getBody());

        return root;
    }

    public ResponseEntity<WizardInfo> getWizardByID(Long id)
    {
        try {
            ResponseEntity<WizardInfo> responseEntity = restTemplate.getForEntity("http://localhost:8082/api/demo/wizard/test/" + id, WizardInfo.class);
            WizardInfo wizardInfo = responseEntity.getBody();

            if (wizardInfo.getId() != null) {
                return ResponseEntity.ok().body(wizardInfo);
            } else {
                throw new NullPointerException("heh");
            }
        }

        catch(ResourceAccessException e)
        {
            throw new ResourceAccessException("Error: Check if WizardInfo Service is online");
        }

    }

}
