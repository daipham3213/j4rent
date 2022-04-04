package io.tomcode.j4rent.core.entities;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Conversation")
@Table(name = "conversation")
@Getter
@Setter
public class Conversation extends BaseEntity {

    @OneToMany(mappedBy = "conversation",cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(name = "Member",
//            joinColumns = @JoinColumn(name = "thread_id"),
//            inverseJoinColumns = @JoinColumn(name = "accounts_id"))
//    private Set<Account> accounts = new LinkedHashSet<>();
//
//    public Set<Account> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(Set<Account> accounts) {
//        this.accounts = accounts;
//    }

}