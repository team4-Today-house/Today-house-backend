package com.example.todayhousebackend.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends Timestamped{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @Column(updatable = false)
  private String contents;

  @Column(updatable = false)
  private int star;

  @Column
  private int commentimg;

  @Column(updatable = false)
  private int commentcount;

  @Column
  private String imgsrc;

}
