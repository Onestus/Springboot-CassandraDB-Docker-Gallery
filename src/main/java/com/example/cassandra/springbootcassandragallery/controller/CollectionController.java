package com.example.cassandra.springbootcassandragallery.controller;

import com.example.cassandra.springbootcassandragallery.ResourceNotFoundException;
import com.example.cassandra.springbootcassandragallery.model.Collection;
import com.example.cassandra.springbootcassandragallery.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CollectionController {

    @Autowired
    CollectionRepository collectionRepository;

    @PostMapping("/collections")
    public Collection addCollection(@RequestBody Collection collection){
        collectionRepository.save(collection);
        return collection;
    }

    @GetMapping("/collections/{id}")
    public ResponseEntity<Collection> findById(@PathVariable("id") Integer collectionId){
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(
                () -> new ResolutionException("Collection not found" + collectionId)
        );
        return ResponseEntity.ok().body(collection);
    }

    @GetMapping("/collections")
    public List<Collection> getCollections(){
        return collectionRepository.findAll();
    }

    @PutMapping("collections/{id}")
    public ResponseEntity<Collection> updateCollection(@PathVariable(value = "id") Integer collectionId,
                                                       @RequestBody Collection collectionDetails){
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(
                () -> new ResourceNotFoundException("Collection not found for this id: " + collectionId)
        );
        collection.setName(collectionDetails.getName());
        collection.setItems(collectionDetails.getItems());
        collection.setAuthors(collectionDetails.getAuthors());
        final Collection updatedCollection = collectionRepository.save(collection);
        return ResponseEntity.ok(updatedCollection);
    }

    @DeleteMapping("collections/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable(value = "id") Integer collectionId) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(
                () -> new ResourceNotFoundException("Collection not found for this id: " + collectionId)
        );
        collectionRepository.delete(collection);
        return ResponseEntity.ok().build();
    }
}
