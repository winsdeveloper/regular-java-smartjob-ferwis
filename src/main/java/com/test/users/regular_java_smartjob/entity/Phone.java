package com.test.users.regular_java_smartjob.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {

  @Id
  @GeneratedValue
  private Long id;

  private String number;
  private String cityCode;
  private String countryCode;
}
