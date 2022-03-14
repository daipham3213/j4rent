package io.tomcode.j4rent.core.entities;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Post")
@Table(name = "post")
@Getter
@Setter
public class Post extends BaseEntity {
    @Column(name = "content")
    private String content;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "price")
    private double price;

    @Column(name = "floor_area")
    private float floorArea;

    @Column(name = "address")
    private String address;

    @Column(name = "furniture_status")
    private String furnitureStatus;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    //Reference
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    Set<Comment> comments = new HashSet<>();
}