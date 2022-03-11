package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode
@Data //Set -Get
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Status")
@Table(name = "Status")
public class Status extends BaseEntity {
    @Column( name = "name")
    private String name;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "parent_n_id")
    private Status parent_n_id;

    //Reference
    @OneToMany(mappedBy = "status_id",cascade = CascadeType.ALL)
    Set<Workflow> workflows=new HashSet<>();

}