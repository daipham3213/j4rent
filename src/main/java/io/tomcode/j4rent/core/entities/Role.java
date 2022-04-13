package io.tomcode.j4rent.core.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Role")
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Account> accounts = new HashSet<>();

    public Role(String role) {
        this.name = role;
    }
//
//    @OneToMany(mappedBy = "role", orphanRemoval = true,fetch = FetchType.LAZY)
//    private List<RolePermissions> rolePermissions = new ArrayList<>();

    @Override
    public String getAuthority() {
        return getId().toString();
    }


}