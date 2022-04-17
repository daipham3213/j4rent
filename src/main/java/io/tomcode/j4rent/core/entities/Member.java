package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "thead_id")
    private Conversation conversation;

    @Column(name = "is_blocked")
    private boolean isBlocked;
}