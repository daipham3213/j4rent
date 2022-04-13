package io.tomcode.j4rent.core.entities;

import io.tomcode.j4rent.mapper.PostCreate;
import io.tomcode.j4rent.mapper.PostDetails;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Post")
@Table(name = "post")
@Getter
@Setter
public class Post extends BaseEntity {

    @Column(name = "contents")
    private String contents;

    @NonNull
    @Column(name = "latitude")
    private double latitude;

    @NonNull
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "price")
    private BigInteger price;

    @NonNull
    @Column(name = "floor_area")
    private float floorArea;


    @NonNull
    @Column(name = "furniture_status")
    private String furnitureStatus;

    @NonNull
    @Column(name = "views")
    private int views;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;


    @OneToMany(mappedBy = "post", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public Post(PostDetails postDetails) {
        setId(postDetails.getId());
        this.contents = postDetails.getContents();
        this.latitude = postDetails.getLatitude();
        this.longitude = postDetails.getLongitude();
        this.price = postDetails.getPrice();
        this.floorArea = postDetails.getFloorArea();
        this.furnitureStatus = postDetails.getFurnitureStatus();
    }

    public Post(PostCreate postCreate) {
        this.contents = postCreate.getContents();
        this.latitude = postCreate.getLatitude();
        this.longitude = postCreate.getLongitude();
        this.price = postCreate.getPrice();
        this.floorArea = postCreate.getFloorArea();
        this.furnitureStatus = postCreate.getFurnitureStatus();
    }
}