package com.example.todayhousebackend.dto;


import com.example.todayhousebackend.entity.HotItem;
import com.example.todayhousebackend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotItemResponseDto {

    private Long hotitemId;
    private String brandname;
    private String title;
    private String discountrate;
    private String price;
    private String img;

    public HotItemResponseDto(HotItem hotItem) {
        this.hotitemId = hotItem.getHotitemId();
        this.brandname = hotItem.getBrandname();
        this.title = hotItem.getTitle();
        this.discountrate = hotItem.getDiscountrate();
        this.price = hotItem.getPrice();
        this.img =hotItem.getImg();
    }

}
