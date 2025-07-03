package com.example.eve_anton.Controller;

import com.example.eve_anton.Entity.Sponsor;
import com.example.eve_anton.Service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SponsorController {

  @Autowired
  private SponsorService sponsorService;

  @PostMapping("/sponsor/add")
  public String addSponsor(@RequestBody Sponsor sponsor){
    sponsorService.saveSponsor(sponsor);
    return "Sponsor Added Successfully..";
  }

  @RequestMapping("/sponsor/{id}")
  public Sponsor getSponsorById(@PathVariable("id") long id){
    return sponsorService.getSponsorById(id);
  }

  @RequestMapping("/sponsor")
  public List<Sponsor> getSponsor(){
    return sponsorService.getAllSponsor();
  }

  @PutMapping("/sponsor/{id}")
  public Sponsor updateSponsor(@PathVariable("id") long id, @RequestBody Sponsor sponsor) {
    return sponsorService.updateSponsor(sponsor);
  }

  @DeleteMapping("/sponsor/{id}")
  public String deleteSponsor(@PathVariable("id") long id){
    sponsorService.deleteSponsor(id);
    return "Sponsor Successfully Deleted";
  }
}
