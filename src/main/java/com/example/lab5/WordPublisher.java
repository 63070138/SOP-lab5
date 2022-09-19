package com.example.lab5;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class WordPublisher{
    protected Word words;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public WordPublisher(){
        words = new Word();
        words.goodWords.add("happy");
        words.goodWords.add("enjoy");
        words.goodWords.add("life");
        words.badWords.add("fuck");
        words.badWords.add("olo");
    }
    @RequestMapping(value="/addBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> addBadWord(@PathVariable("word") String s){
        words.badWords.add(s);
        return words.badWords;
    }

    @RequestMapping(value="/delBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteBadWord(@PathVariable("word") String s){
        words.badWords.remove(words.badWords.indexOf(s));
        return words.badWords;
    }

    @RequestMapping(value="/addGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> addGoodWord(@PathVariable("word") String s){
        words.goodWords.add(s);
        return words.goodWords;
    }

    @RequestMapping(value="/delGood/{word}", method = RequestMethod.DELETE)
    public ArrayList<String> deleteGoodWord(@PathVariable("word") String s){
        words.goodWords.remove(words.goodWords.indexOf(s));
        return words.goodWords;
    }

    @RequestMapping(value="/proof/{word}", method = RequestMethod.GET)
    public void proofSentence(@PathVariable("word") String s){
        if (words.goodWords.contains("s") && words.badWords.contains("s")){
            rabbitTemplate.convertAndSend("Fanout", "", s);
        } else if (words.goodWords.contains("s")) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
        } else if (words.badWords.contains("s")) {
            rabbitTemplate.convertAndSend("Direct", "bad", s);
        }
    }


}
