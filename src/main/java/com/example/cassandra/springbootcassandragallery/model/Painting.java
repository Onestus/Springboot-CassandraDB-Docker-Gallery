package com.example.cassandra.springbootcassandragallery.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Painting {

    @PrimaryKey
    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private int id;

    @Column("name")
    private String name;

    @Column("year")
    private int year;

    @Column("description")
    private String description;
}
