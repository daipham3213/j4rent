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
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "parent_n")
    private Comment parent_n;

//    @ManyToOne
//    @JoinColumn(name = "parent_n", nullable = false)
//    private Comment parentN;
//
//    @ManyToOne
//    @JoinColumn(name = "album", nullable = false)
//    private Album album;
//
//    @ManyToOne
//    @JoinColumn(name = "post", nullable = false)
//    private Post post;
//
//    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
//    private List<Comment> replies;
}