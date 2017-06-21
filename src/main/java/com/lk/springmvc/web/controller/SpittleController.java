package com.lk.springmvc.web.controller;

import com.lk.springmvc.domain.model.spittr.Spittle;
import com.lk.springmvc.domain.mappers.spittr.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by luokai on 17-6-21.
 */
@Controller
public class SpittleController {

    private SpittleRepository spittleRepository;
    public final static String MAX_LONG_AS_STRING = Long.MAX_VALUE + "";

    @Autowired
    public SpittleController(SpittleRepository spittleRepository){
        this.spittleRepository = spittleRepository;
    }

    public SpittleController() {

    }

    @RequestMapping(value = "spittles", method = RequestMethod.GET)
    public String spittles(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING)long max,
            @RequestParam(value = "count", defaultValue = "20")int count, Model model){
        model.addAttribute(spittleRepository.findSpittles(max, count));
        return "spittles";
    }

    @RequestMapping(value = "/spittle/{id}", method = RequestMethod.GET)
    public String spittle(@PathVariable(value = "id") int id, Model model){
        model.addAttribute(spittleRepository.findOne(id));
        return "spittle";
    }

    @RequestMapping(value = "spittle/register", method = RequestMethod.GET)
    public String registerFrom(){
        return "registerFrom";
    }

    @RequestMapping(value = "spittle/register", method = RequestMethod.POST)
    public String processRegistration(Spittle spittle){
        spittleRepository.save(spittle);
        return "redirect:/spittle/" + spittle.getMessage();
    }

}
