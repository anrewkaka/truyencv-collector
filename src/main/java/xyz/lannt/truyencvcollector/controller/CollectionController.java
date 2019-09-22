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
    public String get(@RequestParam String name, @RequestParam String from,
            @RequestParam(required = false) String to) {
        collectionService.get(name, from, to);
        return "OK!";
    }
}
