package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Role;
import liquibase.pro.packaged.R;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role, UUID> {

    List<Role> findByName(String name);

    Role findRoleByName(String name);

    Role findByAccounts_IdEquals(UUID id);

    String where = " r.id = :id and rp.role_id = r.id and rp.permissions_id =  p.id and  p.name = :name";

//    @Query(value = "select distinct r  from Account a,Role  r , RolePermissions rp , Permission p where  a.id= :id and a.role.id = r.id and rp.role.id = r.id and rp.permission.id = p.id and  p.name = :name" )
//    Role checkPermissionOfRole(@Param("id") UUID id, @Param("name") String name);

    boolean existsByAccounts_IdIsAndRolePermissions_Permission_NameIsAllIgnoreCase(UUID id, String name);

}