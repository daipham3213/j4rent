package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data //Set -Get
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PendingPost")
@Table(name = "PendingPost")
public class PendingPost extends BaseEntity {

    @Column ( name = "content")
    private String content;

    @Column( name = "latitude")
    private double latitude;

    @Column ( name = "longitude")
    private double longitude;

    @Column ( name = "price")
    private double price;

    @Column ( name = "floor_rea")
    private float floor_rea;

    @Column ( name = "address")
    private String address;

    @Column ( name = "furniture_status")
    private String furniture_status;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "create_by_id", nullable = false)
    private Account create_by_id;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album_id;

    //Reference
    @OneToMany(mappedBy = "pending_post_id",cascade = CascadeType.ALL)
    Set<Workflow> workflows = new HashSet<>();


}