package io.tomcode.j4rent.core.entities;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Comment")
@Table(name = "comment")
@Setter
@Getter
public class Comment extends BaseEntity {

    @Column(name = "contents")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "parent_n_id", nullable = false)
    private Comment parentN;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<Comment> replies;
}