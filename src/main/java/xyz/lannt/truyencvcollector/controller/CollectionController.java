package xyz.lannt.truyencvcollector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.lannt.truyencvcollector.application.service.CollectionService;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @RequestMapping(method = RequestMethod.GET)
    public String get(@RequestParam(required = true) String name, @RequestParam(required = true) String from,
            @RequestParam String to) {
        collectionService.get(name, from, to);
        return "OK!";
    }
}
