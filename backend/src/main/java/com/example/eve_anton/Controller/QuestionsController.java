package com.example.eve_anton.Controller;

import com.example.eve_anton.Entity.Questions;
import com.example.eve_anton.Service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @RequestMapping("/faq/{id}")
    public Questions getQuestionById(@PathVariable("id") long id){
        return questionsService.getQuestionById(id);
    }

    @RequestMapping("/faq")
    public List<Questions> getQuestions(){
        return questionsService.getQuestions();
    }

}
