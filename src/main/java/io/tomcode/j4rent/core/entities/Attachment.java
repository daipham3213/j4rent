package io.tomcode.j4rent.core.entities;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Attachment")
@Table(name = "attachment")
@Getter
@Setter
public class Attachment  extends BaseEntity {

    @Column(name = "url")
    private String url;

    @Column(name = "caption")
    private String caption;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message_id;
}