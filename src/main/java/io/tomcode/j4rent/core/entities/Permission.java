package io.tomcode.j4rent.core.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "Permission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<RolePermissions> rolePermissions = new ArrayList<>();

//    @OneToMany(mappedBy = "Permission", orphanRemoval = true,fetch = FetchType.LAZY)
//    private List<RolePermissions> rolePermissions = new ArrayList<>();


//    @ManyToMany
//    @JoinTable(name = "role_permissions",
//            joinColumns = @JoinColumn(name = "permission_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private List<Role> roles = new ArrayList<>();

}
