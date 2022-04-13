package io.tomcode.j4rent.core.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Entity(name = "Member")
@Table(name = "member")
@Getter
@Setter
public class Member extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;
}