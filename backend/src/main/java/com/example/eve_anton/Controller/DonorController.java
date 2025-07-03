package com.example.eve_anton.Controller;

import com.example.eve_anton.Entity.Donor;
import com.example.eve_anton.Service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DonorController {

  @Autowired
  private DonorService donorService;

  @PostMapping("/donor/add")
  public String addDonor(@RequestBody Donor donor){
    donorService.saveDonor(donor);
    return "Donor Added Successfully..";
  }

  @RequestMapping("/donor/{id}")
  public Donor getDonorById(@PathVariable("id") long id){
    return donorService.getDonorById(id);
  }

  @RequestMapping("/donor")
  public List<Donor> getDonor(){
    return donorService.getAllDonor();
  }

  @PutMapping("/donor/{id}")
  public Donor updateDonor(@PathVariable("id") long id, @RequestBody Donor donor) {
    return donorService.updateDonor(donor);
  }

  @DeleteMapping("/donor/{id}")
  public String deleteDonor(@PathVariable("id") long id){
    donorService.deleteDonor(id);
    return "Donor Successfully Deleted";
  }
}