package com.example.todayhousebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class HotItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotitemId;

<<<<<<< HEAD
//    @Column(updatable = false)
=======
//    @Column
>>>>>>> 5f53f2dc021185d6a3fa44f764b7fd79f4a07924
//    private String categoryname;

    @Column(updatable = false)
    private String brandname;

    @Column(updatable = false)
    private String title;

    @Column(updatable = false)
    private String discountrate;

    @Column(updatable = false)
    private String price;

    @Column
    private String imgsrc;



}
