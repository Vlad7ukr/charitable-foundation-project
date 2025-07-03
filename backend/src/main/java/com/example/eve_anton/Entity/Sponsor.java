package com.example.eve_anton.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sponsor")
public class Sponsor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_sponsor;

  @Column(name = "sponsor_name")
  private String sponsor_name;

  @Column(name = "sponsor_description")
  private String sponsor_description;

  @Column(name = "img_url")
  private String imgURL;
}
