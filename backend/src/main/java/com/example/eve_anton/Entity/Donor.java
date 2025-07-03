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

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "donor")
public class Donor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_donor;

  @Column(name = "donor_name")
  private String donor_name;

  @Column(name = "contact_email")
  private String contact_email;

  @Column(name = "contact_phone")
  private String contact_phone;
}
