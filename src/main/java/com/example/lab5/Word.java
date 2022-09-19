package com.example.lab5;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Word{
    public ArrayList<String> badWords;
    public ArrayList<String> goodWords;
    public Word(){
        badWords = new ArrayList<String>();
        goodWords = new ArrayList<String>();
    }
}
