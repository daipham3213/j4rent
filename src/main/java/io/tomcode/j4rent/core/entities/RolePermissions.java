package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Entity(name = "RolePermissions")
@Table(name = "pole_permissions")
@Getter
@Setter
public class RolePermissions extends  BaseEntity{
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permissions_id")
    private Conversation conversation;
}
