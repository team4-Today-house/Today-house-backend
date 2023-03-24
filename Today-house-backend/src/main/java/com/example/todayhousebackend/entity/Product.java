package com.example.todayhousebackend.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(updatable = false)
    private String categoryname;

    @Column(updatable = false)
    private String brandname;

    @Column(updatable = false)
    private String title;

    @Column(updatable = false)
    private int discountrate;

    @Column(updatable = false)
    private int price;

    @Column
    private String imgsrc;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;   // product 안에서 댓글이 여러개니까 코멘트들을 리스트 형식으로 감싸서 보내준다.

}
