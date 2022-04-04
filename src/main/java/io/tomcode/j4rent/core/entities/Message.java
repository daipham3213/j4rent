package io.tomcode.j4rent.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Message")
@Table(name = "message")
@Getter
@Setter
public class Message extends BaseEntity {
    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "reply_to")
    private Message reply_to;

    //Referencing

    @OneToMany(mappedBy = "message", orphanRemoval = true)
    private Set<Attachment> attachments = new LinkedHashSet<>();

//
//    @ManyToOne
//    @JoinColumn(name = "sender", nullable = false)
//    private Account sender;
//
//    @ManyToOne
//    @JoinColumn(name = "reply_to")
//    private Message replyTo;


//    @OneToMany(mappedBy = "message_id", cascade = CascadeType.ALL)
//    Set<Attachment> attachments = new HashSet<>();
//
//    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
//    Set<Message> messages = new HashSet<>();
}