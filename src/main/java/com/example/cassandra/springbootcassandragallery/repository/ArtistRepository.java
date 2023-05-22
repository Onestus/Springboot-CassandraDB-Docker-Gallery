package com.example.cassandra.springbootcassandragallery.repository;

import com.example.cassandra.springbootcassandragallery.model.Artist;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ArtistRepository extends CassandraRepository<Artist, Integer> {
}
