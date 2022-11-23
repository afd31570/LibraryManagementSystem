package com.lms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
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

    @Column(length =17, nullable = false)
    private String isbn;// format : 999 99 99999 99 9

    @Column
    private int pageCount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authorId;

    @ManyToOne
    @JoinColumn(name = "publisher_id")

    private Publisher publisherId;

    @Column//year
    private int publishDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @OneToOne
    private Image image;

    @Column
    private Boolean loanable=true;

    @Column(nullable = false, length = 6)//format:AA-999
    private String shelfCode;

    @Column(nullable = false)
    private Boolean active=true;

    @Column(nullable = false)
    private Boolean featured=false;

    @Column(nullable = false)
    private LocalDateTime createDate;// ex: 1990-10-25T10:35:25Z

    @Column(nullable = false)
    private Boolean builtIn=false;

}

