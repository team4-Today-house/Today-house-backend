package com.example.todayhousebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HotItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotitemId;

//    @Column(updatable = false)
//    private String categoryname;

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

}
