package io.tomcode.j4rent.core.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Role")
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {
    @JsonManagedReference
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Account> accounts = new ArrayList<>();
    @JsonManagedReference
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<RolePermissions> rolePermissions = new ArrayList<>();
    @Column(name = "name", unique = true, nullable = false)
    private String name;


    public Role(String role) {
        this.name = role;
    }

    @Override
    public String getAuthority() {
        return getId().toString();
    }


}