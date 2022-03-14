package io.tomcode.j4rent.core.entities;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Status")
@Table(name = "status")
@Getter
@Setter
public class Status extends BaseEntity {
    @Column( name = "name")
    private String name;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "parent_n_id")
    private Status parentN;

    //Reference
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Workflow> workflows = new HashSet<>();
}