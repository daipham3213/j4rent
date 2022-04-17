package io.tomcode.j4rent.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfo {
//    private UUID id;

    private String username;

    private String firstName;

    private String lastName;

    private Date dob;

    private String idCard;

    private String gender;

    private String phoneNumber;

    private String email;

}
