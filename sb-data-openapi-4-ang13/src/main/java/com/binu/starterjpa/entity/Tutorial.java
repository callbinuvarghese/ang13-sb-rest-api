package com.binu.starterjpa.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tutorials")
@Data
@ToString
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    @NotNull(message = "Title must be present")
    @Size(min = 4, max = 20, message
            = "Title name must be between 4 and 6 characters")

    private String title;
    @Column(name = "description")

    private String description;
    @Column(name = "published")

    private boolean published;

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

    public Tutorial() {
    }

    public Tutorial(Long id, String title, String description, boolean published) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.published = published;
    }
}
