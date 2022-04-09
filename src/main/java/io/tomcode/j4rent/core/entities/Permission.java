package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Permission extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "permission_roles",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles = new ArrayList<>();

}