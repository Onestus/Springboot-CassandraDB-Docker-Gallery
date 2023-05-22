package com.example.cassandra.springbootcassandragallery.controller;

import com.example.cassandra.springbootcassandragallery.ResourceNotFoundException;
import com.example.cassandra.springbootcassandragallery.model.Artist;
import com.example.cassandra.springbootcassandragallery.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

    @PostMapping("/artists")
    public Artist addArtist(@RequestBody Artist artist){
        artistRepository.save(artist);
        return artist;
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> findById(@PathVariable("id") Integer artistId){
        Artist artist = artistRepository.findById(artistId).orElseThrow(
                () -> new ResolutionException("Artist not found" + artistId)
        );
        return ResponseEntity.ok().body(artist);
    }

    @GetMapping("/artists")
    public List<Artist> getArtists(){
        return artistRepository.findAll();
    }

    @PutMapping("artists/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable(value = "id") Integer artistId,
                                                   @RequestBody Artist artistDetails){
        Artist artist = artistRepository.findById(artistId).orElseThrow(
                () -> new ResourceNotFoundException("Artist not found for this id: " + artistId)
        );
        artist.setName(artistDetails.getName());
        artist.setYearsofliving(artistDetails.getYearsofliving());
        artist.setPaintings(artistDetails.getPaintings());
        artist.setBiography(artistDetails.getBiography());
        final Artist updatedArtist = artistRepository.save(artist);
        return ResponseEntity.ok(updatedArtist);
    }

    @DeleteMapping("artists/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable(value = "id") Integer artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(
                () -> new ResourceNotFoundException("Artist not found for this id: " + artistId)
        );
        artistRepository.delete(artist);
        return ResponseEntity.ok().build();
    }
}
