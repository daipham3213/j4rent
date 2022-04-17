package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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