package com.lms.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private LocalDateTime loanDate;

    @Column(nullable = false)
    private LocalDateTime expireDate;

    @Column
    private LocalDateTime returnDate;

    @Column
    @Size(max = 300)
    private String notes;

}
