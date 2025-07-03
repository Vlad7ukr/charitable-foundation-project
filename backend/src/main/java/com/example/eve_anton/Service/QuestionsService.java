package com.example.eve_anton.Service;

import com.example.eve_anton.Entity.Questions;
import com.example.eve_anton.Repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    public Questions getQuestionById(long id) {
        return questionsRepository.findById(id).get();
    }

    public List<Questions> getQuestions() {
        return (List<Questions>) questionsRepository.findAll();
    }

    public List<Questions> findByQuestion(String question) {
        return questionsRepository.findByQuestion(question);
    }
}
