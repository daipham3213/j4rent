package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@EqualsAndHashCode
@Data //Set -Get
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Post")
@Table(name = "Post")

public class Post extends BaseEntity  {
    @Column( name = "content")
    private String content;

    @Column( name = "latitude")
    private double latitude;

    @Column ( name = "longitude")
    private double longitude;

    @Column ( name = "price")
    private double price;

    @Column ( name = "floorArea")
    private float floorArea;

    @Column ( name = "address")
    private String address;

    @Column ( name = "furnitureStatus")
    private String furnitureStatus;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "create_by_id", nullable = false)
    private Account create_by_id;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album_id;

    //Reference
    @OneToMany(mappedBy = "post_id",cascade = CascadeType.ALL)
    Set<Comment> post_id= new HashSet<>();
}