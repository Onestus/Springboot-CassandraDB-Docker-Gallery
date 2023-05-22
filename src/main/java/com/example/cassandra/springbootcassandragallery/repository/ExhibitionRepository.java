package com.example.cassandra.springbootcassandragallery.repository;

import com.example.cassandra.springbootcassandragallery.model.Exhibition;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ExhibitionRepository extends CassandraRepository<Exhibition, Integer> {
}
