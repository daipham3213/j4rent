package io.tomcode.j4rent.core.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class RolePermissionsKey implements Serializable {
    @Column(name = "role_id")
    UUID roleId;
    @Column(name = "permission_id")
    UUID permissionId;
}
