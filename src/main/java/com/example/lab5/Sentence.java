package com.example.lab5;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Sentence{
    public ArrayList<String> badSentences;
    public ArrayList<String> goodSentences;
}
