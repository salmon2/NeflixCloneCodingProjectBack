package com.sparta.NeflixCloneCodingProjectBack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SmallCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String smallCategory;

    @Column(nullable = false)
    private int smallCategorynumber;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "largecategoryId")
    private LargeCategory largeCategory;

}
