package com.example.cassandra.springbootcassandragallery.controller;

import com.example.cassandra.springbootcassandragallery.ResourceNotFoundException;
import com.example.cassandra.springbootcassandragallery.model.Painting;
import com.example.cassandra.springbootcassandragallery.repository.PaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PaintingController {

    @Autowired
    PaintingRepository paintingRepository;

    @PostMapping("/paintings")
    public Painting addPainting(@RequestBody Painting painting){
        paintingRepository.save(painting);
        return painting;
    }

    @GetMapping("/paintings/{id}")
    public ResponseEntity<Painting> findById(@PathVariable("id") Integer paintingId){
        Painting painting = paintingRepository.findById(paintingId).orElseThrow(
                () -> new ResolutionException("Painting not found" + paintingId)
        );
        return ResponseEntity.ok().body(painting);
    }

    @GetMapping("/paintings")
    public List<Painting> getPaintings(){
        return paintingRepository.findAll();
    }

    @PutMapping("paintings/{id}")
    public ResponseEntity<Painting> updatePainting(@PathVariable(value = "id") Integer paintingId,
                                                  @RequestBody Painting paintingDetails){
        Painting painting = paintingRepository.findById(paintingId).orElseThrow(
                () -> new ResourceNotFoundException("Painting not found for this id: " + paintingId)
        );
        painting.setName(paintingDetails.getName());
        final Painting updatedPainting = paintingRepository.save(painting);
        return ResponseEntity.ok(updatedPainting);
    }

    @DeleteMapping("paintings/{id}")
    public ResponseEntity<Void> deletePainting(@PathVariable(value = "id") Integer paintingId) {
        Painting painting = paintingRepository.findById(paintingId).orElseThrow(
                () -> new ResourceNotFoundException("Painting not found for this id: " + paintingId)
        );
        paintingRepository.delete(painting);
        return ResponseEntity.ok().build();
    }
}
