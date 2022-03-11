package io.tomcode.j4rent.core.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Privilege")
@Table(name = "Privilege")
public class Privilege extends BaseEntity {
    @Column( name = "name")
    private String name;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "parent_n_id")
    private Privilege parent_n_id;

    //Reference
    @OneToMany(mappedBy = "privileges_id",cascade = CascadeType.ALL)
    Set<Account> accounts= new HashSet<>();

    @OneToMany(mappedBy = "parent_n_id",cascade = CascadeType.ALL)
    Set<Privilege> privileges = new HashSet<>();
}