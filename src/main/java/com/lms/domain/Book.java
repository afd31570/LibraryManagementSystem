package com.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;

@Entity
@Table(name="t_books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length =80, nullable = false)
    private String name;
    private String isbn;
    private int pageCount;
    @ManyToOne
    private Author authorId;
    @ManyToOne
    private Publisher publisherId;
    private int publishDate;
    @ManyToOne
    private Category categoryId;
    private Boolean loanable;
    private String shelfCode;
    private Boolean active;
    private Boolean featured;
    private LocalDateTime createDate;
    private Boolean builtIn;
    //TODO EKSİK KALDI
}

