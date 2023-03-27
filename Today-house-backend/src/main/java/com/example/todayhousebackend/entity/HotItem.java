package com.example.todayhousebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class HotItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotitemId;

    @Column(updatable = false)
    private String brandname;

    @Column(updatable = false)
    private String title;

    @Column(updatable = false)
    private String discountrate;

    @Column(updatable = false)
    private String price;

    @Column(updatable = false)
    private String img;



}
