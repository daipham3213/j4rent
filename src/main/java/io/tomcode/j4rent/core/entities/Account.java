package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Account")
@Table(name = "account")
public class Account extends BaseEntity {
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

    @Column(length = 100, unique = true)
    private String email;

    @Column(name = "is_verify", columnDefinition = "BOOL DEFAULT false")
    private boolean isVerify;

    @Column(name = "is_admin", columnDefinition = "BOOL DEFAULT false")
    private boolean isAdmin;
}
