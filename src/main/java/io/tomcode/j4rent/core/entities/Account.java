package io.tomcode.j4rent.core.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Account")
@Table(name = "account")
public class Account extends BaseEntity implements UserDetails {
    @Column(name = "username", nullable = false, unique = true, updatable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fist_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "id_card", length = 15, unique = true)
    private String idCard;

    @Column(length = 10, columnDefinition = "VARCHAR(10) DEFAULT 'male'")
    private String gender;

    @Column(name = "phone_number",length = 15, unique = true)
    private String phoneNumber;

    @Column(name = "email",length = 100, unique = true)
    private String email;

    @Column(name = "is_verify", columnDefinition = "BOOL DEFAULT false")
    private boolean isVerify;

    @Column(name = "is_admin", columnDefinition = "BOOL DEFAULT false")
    private boolean isAdmin;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private  Role role;

    @OneToMany(mappedBy = "createdById", orphanRemoval = true)
    private List<Album> albums = new ArrayList<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private List<Workflow> workflows = new ArrayList<>();

    @OneToMany(mappedBy = "createdById", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.isVerify;
    }

}
