package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Attachment")
@Table(name = "attachment")
@Getter
@Setter
public class Attachment extends BaseEntity {

    @Column(name = "url")
    private String url;

    @Column(name = "caption")
    private String caption;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message_id;
}