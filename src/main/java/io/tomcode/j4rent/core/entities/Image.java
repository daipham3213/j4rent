package io.tomcode.j4rent.core.entities;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.UUID;
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

    public Image(String caption,String url) {
        this.caption=caption;
        this.url = url;
    }

    //
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;


}