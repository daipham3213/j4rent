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
@Entity(name = "Attachment")
@Table(name = "Attachment")
//@AttributeOverride(name = "id" , column = @Column(name = "id"))
public class Attachment  extends BaseEntity  {

    //Primary key
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "attachment_id", nullable = false)
//    private UUID id;
    @Column(name = "url")
    private String url;

    @Column(name = "caption")
    private String caption;

    //Foreign key
    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message_id;

}