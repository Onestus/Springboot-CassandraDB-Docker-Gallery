package com.example.cassandra.springbootcassandragallery.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {

    @PrimaryKey
    private int id;
    private String name;
    private String items;
    private String authors;
}
