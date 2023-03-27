package com.example.todayhousebackend.dto;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CrawlingDto {

    private String brandname;

    private String title;

    private String discountrate;

    private String price;

    private String img;


}
