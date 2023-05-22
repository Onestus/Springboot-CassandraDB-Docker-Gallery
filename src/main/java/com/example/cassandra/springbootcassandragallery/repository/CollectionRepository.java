package com.example.cassandra.springbootcassandragallery.repository;

import com.example.cassandra.springbootcassandragallery.model.Collection;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface CollectionRepository extends CassandraRepository<Collection, Integer> {
}
