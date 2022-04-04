package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccount {

    private  int otp;

    private String password;

    private String firstName;

    private String lastName;

    private Date dob;

    private String idCard;

    private String gender;

}
