package com.aluracursos.foro_hub.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicController {


    @GetMapping
    public String topic() {
        return "Listando tópicos";
    }
}
