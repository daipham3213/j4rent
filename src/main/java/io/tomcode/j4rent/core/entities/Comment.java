package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@EqualsAndHashCode
@Data //Set -Get
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Comment")
@Table(name = "Comment")
public class Comment extends BaseEntity {

    @Column(name = "contents")
    private String contents;
    //Foreign key
    @ManyToOne
    @JoinColumn(name = "parent_n_id", nullable = false)
    private Comment parent_n_id;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album_id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post_id;
    //Reference
    @OneToMany(mappedBy = "parent_n_id")
    List<Comment> comments ;
}