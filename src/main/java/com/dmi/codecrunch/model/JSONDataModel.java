package com.dmi.codecrunch.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
public class JSONDataModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String key;

    @Lob
    @Column (columnDefinition = "TEXT")
    private String values;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
