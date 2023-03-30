package com.example.todayhousebackend.entity;

import com.example.todayhousebackend.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Setter
public class Comment extends Timestamped {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(nullable = false)
  private String comment;

  @Column
  private String imgUrl;

  @ManyToOne
  @JoinColumn(name = "USER_ID", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID", nullable = false)
  private Product product;

  public Comment(CommentRequestDto commentRequestDto, User user, Product product) {
    this.comment = commentRequestDto.getContents();
    this.user = user;
    this.product = product;
  }

//  public Comment(CommentRequestDto commentRequestDto, User user, Product product) {
//    this.comment = commentRequestDto.getContents();
//    this.user = user;
//    this.product = product;
//    this.imgUrl = commentRequestDto.getImgUrl();
//  }

  @Transactional
  public void update(CommentRequestDto commentRequestDto) {
    this.comment = commentRequestDto.getContents();
  }
}
