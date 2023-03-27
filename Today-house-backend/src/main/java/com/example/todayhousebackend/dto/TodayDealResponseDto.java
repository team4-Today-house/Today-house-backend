package com.example.todayhousebackend.dto;

import com.example.todayhousebackend.entity.HotItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodayDealResponseDto {

    private Long productId;
    private String brandname;
    private String title;
    private String discountrate;
    private String price;
//    private int commentcount;
    private String imgsrc;

    public TodayDealResponseDto(HotItem hotItem) {
        this.productId = hotItem.getHotitemId();
        this.brandname = hotItem.getBrandname();
        this.title = hotItem.getTitle();
        this.discountrate = hotItem.getDiscountrate();
        this.price = hotItem.getPrice();
        this.imgsrc = hotItem.getImgsrc();
    }
}
