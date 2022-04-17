package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Image")
@Table(name = "image")
@Getter
@Setter
public class Image extends BaseEntity {


    @Column(name = "url")
    private String url;
    @Column(name = "caption")
    private String caption;
    @Column(name = "is_hidden")
    private Boolean isHidden;
    //
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    public Image(String caption, String url) {
        this.caption = caption;
        this.url = url;
    }


}