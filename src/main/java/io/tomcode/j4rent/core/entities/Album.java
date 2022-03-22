package io.tomcode.j4rent.core.entities;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Album")
@Table(name = "album")
@Getter
@Setter
public class Album extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "is_hidden")
    private String isHidden;
    // Foreign key

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    //Reference
    @OneToMany(mappedBy = "album", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "album", orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "album", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();



}