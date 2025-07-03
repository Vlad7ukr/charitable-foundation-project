package com.example.eve_anton.Service;

import com.example.eve_anton.Entity.Sponsor;
import com.example.eve_anton.Repository.SponsorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorService {

  @Autowired
  private SponsorRepository sponsorRepository;

  public List<Sponsor> getAllSponsor() {
    return sponsorRepository.findAll();
  }

  public Sponsor getSponsorById(Long id) {
    return sponsorRepository.findById(id).orElse(null);
  }

  public void saveSponsor(Sponsor sponsor) {
    sponsorRepository.save(sponsor);
  }

  public Sponsor updateSponsor(Sponsor sponsorUpdate) {
    sponsorRepository.save(sponsorUpdate);
    return sponsorUpdate;
  }

  public void deleteSponsor(long id) {
    sponsorRepository.deleteById(id);
  }
}
