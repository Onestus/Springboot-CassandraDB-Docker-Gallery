package com.example.cassandra.springbootcassandragallery.controller;

import com.example.cassandra.springbootcassandragallery.ResourceNotFoundException;
import com.example.cassandra.springbootcassandragallery.model.Exhibition;
import com.example.cassandra.springbootcassandragallery.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExhibitionController {

    @Autowired
    ExhibitionRepository exhibitionRepository;

    @PostMapping("/exhibitions")
    public Exhibition addExhibition(@RequestBody Exhibition exhibition){
        exhibitionRepository.save(exhibition);
        return exhibition;
    }

    @GetMapping("/exhibitions/{id}")
    public ResponseEntity<Exhibition> findById(@PathVariable("id") Integer exhibitionId){
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(
                () -> new ResolutionException("Exhibition not found" + exhibitionId)
        );
        return ResponseEntity.ok().body(exhibition);
    }

    @GetMapping("/exhibitions")
    public List<Exhibition> getExhibitions(){
        return exhibitionRepository.findAll();
    }

    @PutMapping("exhibitions/{id}")
    public ResponseEntity<Exhibition> updateExhibition(@PathVariable(value = "id") Integer exhibitionId,
                                               @RequestBody Exhibition exhibitionDetails){
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(
                () -> new ResourceNotFoundException("Exhibition not found for this id: " + exhibitionId)
        );
        exhibition.setName(exhibitionDetails.getName());
        exhibition.setLocation(exhibitionDetails.getLocation());
        exhibition.setDates(exhibitionDetails.getDates());
        exhibition.setThematics(exhibitionDetails.getThematics());
        final Exhibition updatedExhibition = exhibitionRepository.save(exhibition);
        return ResponseEntity.ok(updatedExhibition);
    }

    @DeleteMapping("exhibitions/{id}")
    public ResponseEntity<Void> deleteExhibition(@PathVariable(value = "id") Integer exhibitionId) {
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(
                () -> new ResourceNotFoundException("Exhibition not found for this id: " + exhibitionId)
        );
        exhibitionRepository.delete(exhibition);
        return ResponseEntity.ok().build();
    }
}
