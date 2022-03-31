package io.tomcode.j4rent.core.entities;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Post")
@Table(name = "post")
@Getter
@Setter
public class Post extends BaseEntity {

    @NonNull
    @Column(name = "content")
    private String content;

    @NonNull
    @Column(name = "latitude")
    private double latitude;

    @NonNull
    @Column(name = "longitude")
    private double longitude;
    @NonNull
    @Column(name = "price")
    private double price;

    @NonNull
    @Column(name = "floor_area")
    private float floorArea;

    @NonNull
    @Column(name = "address")
    private String address;

    @NonNull
    @Column(name = "furniture_status")
    private String furnitureStatus;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();

}