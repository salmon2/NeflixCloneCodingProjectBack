package com.sparta.NeflixCloneCodingProjectBack.smallcategory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.NeflixCloneCodingProjectBack.largecategory.LargeCategory;
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "largecategoryId")
    private LargeCategory largeCategory;


}
