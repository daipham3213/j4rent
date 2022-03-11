package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data //Set -Get
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "message")
@Table(name = "message")
public class Message  extends BaseEntity{

        @Column(name = "Message")
    private String message;
    // Foreign key
    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private  Conversation conversation;
    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private  Account sender;
    @ManyToOne
    @JoinColumn(name = "reply_to")
    private  Message reply_to;
    //Reference
    @OneToMany(mappedBy = "message_id",cascade = CascadeType.ALL)
    Set<Attachment>Attachment = new HashSet<>();

    @OneToMany(mappedBy = "reply_to",cascade = CascadeType.ALL)
    Set<Message>messages = new HashSet<>();






}