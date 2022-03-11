package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data //Set -Get
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Conversation")
@Table(name = "Conversation")
public class Conversation extends BaseEntity  {

    @OneToMany(mappedBy = "conversation",cascade = CascadeType.ALL)
    Set<Message> messages = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "Member",
            joinColumns = @JoinColumn(name = "thread_id"),
            inverseJoinColumns = @JoinColumn(name = "accounts_id"))
    private Set<Account> accounts = new LinkedHashSet<>();

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

}