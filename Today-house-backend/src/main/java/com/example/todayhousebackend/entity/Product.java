package com.example.todayhousebackend.entity;


import java.util.ArrayList;

import com.example.todayhousebackend.dto.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import lombok.Setter;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(updatable = false)
    private String brandname;

    @Column(updatable = false)
    private String title;

    @Column
    private String discountrate;

    @Column(updatable = false)
    private String price;

    @Column(updatable = false)
    private String img;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;   // product 안에서 댓글이 여러개니까 코멘트들을 리스트 형식으로 감싸서 보내준다.

    public Product(ProductResponseDto productResponseDto) {
        this.brandname = productResponseDto.getBrandname();
        this.title = productResponseDto.getTitle();
        this.discountrate = productResponseDto.getDiscountrate();
        this.price = productResponseDto.getPrice();
    }


    //    @OneToMany(mappedBy = "product")
//    private List<ProductImages> productImages = new ArrayList<>();


}
