package com.example.cassandra.springbootcassandragallery.repository;

import com.example.cassandra.springbootcassandragallery.model.Painting;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PaintingRepository extends CassandraRepository<Painting, Integer> {
}
