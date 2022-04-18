package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Comment")
@Table(name = "comment")
@Setter
@Getter
public class Comment extends BaseEntity {

    @Column(name = "contents")
    private String  contents;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "parent_n")
    private Comment parentN;

    public Comment(Album album, String content) {
        this.album = album;
        this.contents = content;
    }

    public Comment(Post post, String content) {
        this.post = post;
        this.contents = content;
    }

    public Comment(Comment comment, String content) {
        this.parentN = comment;
        this.contents = content;
    }

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