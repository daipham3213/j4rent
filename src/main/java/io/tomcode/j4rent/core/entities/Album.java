package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    //Reference
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();
}