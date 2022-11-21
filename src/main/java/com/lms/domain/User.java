package com.lms.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="t_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Length(min = 2, max = 30)
    private String firstName;

    @Column(nullable = false)
    @Length(min = 2, max = 30)
    private String lastName;

    @Column(nullable = false)
    @Size(min = -2, max = +2)
    private Integer score = 0;

    @Column(nullable = false)
    @Length(min = 10, max = 100)
    private String address;

    @Column(length = 14, nullable = false)
    private String phone;

    @Column
    private Date birthDate;

    @Column(nullable = false)
    @Length(min = 10, max = 80)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(length = 15, nullable = false)
    private String resetPasswordCode;

    @NotNull
    private Boolean builtIn = false;
}
