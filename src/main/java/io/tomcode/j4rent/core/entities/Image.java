package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.UUID;
@Data //Set -Get
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Image")
@Table(name = "Image")
public class Image extends BaseEntity {

    @Column(name = "url")
    private String url;
    @Column(name = "caption")
    private String caption;
    @Column(name = "is_hidden")
    private String is_hidden;
    // Foreign key
    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album_id;
}