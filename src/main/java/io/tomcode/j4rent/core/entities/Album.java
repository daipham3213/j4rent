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
@Entity(name = "Album")
@Table(name = "Album")
//@AttributeOverride(name = "id" , column = @Column(name = "id"))
public class Album extends BaseEntity   {

    @Column(name = "name")
    private String name;

    @Column(name = "is_hidden")
    private String is_hidden;
    // Foreign key

    //Reference
    @OneToMany(mappedBy = "album_id",cascade = CascadeType.ALL)
    Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "album_id",cascade = CascadeType.ALL)
    Set<Comment>comments = new HashSet<>();

    @OneToMany(mappedBy = "album_id",cascade = CascadeType.ALL)
    Set<Post>posts = new HashSet<>();

    @OneToMany(mappedBy = "album_id",cascade = CascadeType.ALL)
    Set<PendingPost>pending_post = new HashSet<>();
}