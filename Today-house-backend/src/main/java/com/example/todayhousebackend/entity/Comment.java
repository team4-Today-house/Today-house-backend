package com.example.todayhousebackend.entity;

import com.example.todayhousebackend.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(nullable = false)
  private String comment;

  @Column(nullable = false)
  private int star;

  @ManyToOne
  @JoinColumn(name = "USER_ID", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID", nullable = false)
  private Product product;

  public Comment(CommentRequestDto commentRequestDto, User user, Product product) {
    this.comment = commentRequestDto.getContents();
    this.star = commentRequestDto.getStar();
    this.user = user;
    this.product = product;
  }
}
