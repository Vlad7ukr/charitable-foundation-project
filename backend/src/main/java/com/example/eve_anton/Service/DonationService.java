package com.example.eve_anton.Service;

import com.example.eve_anton.Entity.Donation;
import com.example.eve_anton.Repository.DonationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {

  @Autowired
  private DonationRepository donationRepository;

  public List<Donation> getAllDonation() {
    return donationRepository.findAll();
  }

  public Donation getDonationById(Long id) {
    return donationRepository.findById(id).orElse(null);
  }

  public void saveData(Donation donation) {
    donationRepository.save(donation);
  }

  public Donation updateData(Donation donation) {
    return donationRepository.save(donation);
  }

  public void deleteData(long id) {
    donationRepository.deleteById(id);
  }
}
