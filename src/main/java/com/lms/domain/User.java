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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Column(nullable = false, length= 30)

    private String firstName;

    @Column(nullable = false,length= 30)
    private String lastName;

    @Column(nullable = false)
    @Size(min = -2, max = +2)
    private Integer score = 0;

    @Column(nullable = false,length= 100)
    private String address;

    @Column(length = 14, nullable = false)
    private String phone;

    @Column
    private LocalDate birthDate;

    @Column(nullable = false,length= 80)
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

    @ManyToMany
    @JoinTable(name="t_user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles=new HashSet<>();
}
