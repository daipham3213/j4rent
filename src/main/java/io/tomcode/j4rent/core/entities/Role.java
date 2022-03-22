package io.tomcode.j4rent.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Role")
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity {
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    //Reference
    @JsonManagedReference
    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    Set<Account> accounts = new HashSet<>();
//
}