package io.tomcode.j4rent.core.entities;

import io.tomcode.j4rent.mapper.ImageView;
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
    private Boolean isHidden;

    @OneToMany(mappedBy = "album", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "album", orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "album", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public Album(List<Image> imageLoads) {
        this.images = imageLoads;
    }

    public Album(String name, Boolean is_hidden, List<Image> imageLoads) {
        this.images = imageLoads;
        this.name = name;
        this.isHidden = is_hidden;
    }

    public Album(String name, Boolean is_hidden) {
        this.name = name;
        this.isHidden = is_hidden;
    }

    public Album(String name, List<ImageView> imageLoadList) {
        this.name = name;

    }
}