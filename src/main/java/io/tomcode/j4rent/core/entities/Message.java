package io.tomcode.j4rent.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Message")
@Table(name = "message")
@Getter
@Setter
public class Message extends BaseEntity {
    @Column(name = "msg")
    private String msg;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "reply_to")
    private Message reply_to;

    //Referencing

    @OneToMany(mappedBy = "message_id", orphanRemoval = true)
    private Set<Attachment> attachments = new LinkedHashSet<>();


    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Account sender;

}