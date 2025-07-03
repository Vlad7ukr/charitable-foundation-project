package com.example.eve_anton.Controller;

import com.example.eve_anton.Entity.Donation;
import com.example.eve_anton.Entity.Event;
import com.example.eve_anton.Service.DonationService;
import com.example.eve_anton.Service.DonorService;
import com.example.eve_anton.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DonationController {

  @Autowired
  private DonationService donationService;

  @Autowired
  private DonorService donorService;

  @Autowired
  private EventService eventService;


  @PostMapping("/donation/add")
  public String addDonation(@RequestBody Donation donation){
    donationService.saveData(donation);
    return "Donation Added Successfully..";
  }

  @RequestMapping("/donation/{id}")
  public Donation getDonationById(@PathVariable("id") long id){
    return donationService.getDonationById(id);
  }

  @RequestMapping("/donation")
  public List<Donation> getDonation(){
    return donationService.getAllDonation();
  }

  @PutMapping("/donation")
  public Donation updateDonation(@RequestBody Donation donation){
    return donationService.updateData(donation);
  }

  @DeleteMapping("/donation/{id}")
  public String deleteDonation(@PathVariable("id") long id){
    donationService.deleteData(id);
    return "Donation Successfully Deleted";
  }
}
