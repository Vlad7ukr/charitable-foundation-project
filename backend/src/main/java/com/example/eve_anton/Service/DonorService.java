package com.example.eve_anton.Service;

import com.example.eve_anton.Entity.Donor;
import com.example.eve_anton.Repository.DonorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorService {

  @Autowired
  private DonorRepository donorRepository;

  public List<Donor> getAllDonor() {
    return donorRepository.findAll();
  }

  public Donor getDonorById(Long id) {
    return donorRepository.findById(id).orElse(null);
  }

  public void saveDonor(Donor donor) {
    donorRepository.save(donor);
  }

  public Donor updateDonor(Donor donorUpdate) {
    donorRepository.save(donorUpdate);
    return donorUpdate;
  }

  public void deleteDonor(long id) {
    donorRepository.deleteById(id);
  }
}
