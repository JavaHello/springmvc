package com.lk.springmvc.web.controller;

import com.lk.springmvc.service.spittr.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by luokai on 17-6-21.
 */
@Controller
public class SpittleController {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository){
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping("spittles")
    public String list(){
        return "spittles";
    }
}
