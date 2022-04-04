package io.tomcode.j4rent.core.entities;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Status")
@Table(name = "status")
@Getter
@Setter
public class Status extends BaseEntity {
    @Column(name = "name")
    private String name;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "parent_n")
    private Status status;

    //Reference
    @OneToMany(mappedBy = "status", orphanRemoval = true)
    private List<Workflow> workflows = new ArrayList<>();

}