package io.tomcode.j4rent.core.entities;

import io.tomcode.j4rent.mapper.PostCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.*;

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


    @OneToMany(mappedBy = "post", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public Post(PostDetails postDetails) {
        setId(postDetails.getId());
        this.content = postDetails.getContent();
        this.latitude = postDetails.getLatitude();
        this.longitude = postDetails.getLongitude();
        this.price = postDetails.getPrice();
        this.floorArea = postDetails.getFloorArea();
        this.address = postDetails.getAddress();
        this.furnitureStatus = postDetails.getFurnitureStatus();
    }

    public Post(PostCreate postCreate) {
        this.content = postCreate.getContent();
        this.latitude = postCreate.getLatitude();
        this.longitude = postCreate.getLongitude();
        this.price = postCreate.getPrice();
        this.floorArea = postCreate.getFloorArea();
        this.address = postCreate.getAddress();
        this.furnitureStatus = postCreate.getFurnitureStatus();
    }
}