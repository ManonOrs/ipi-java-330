package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccueilController {

    @Autowired
    private EmployeService employeService;

    @RequestMapping(value = "/")
    public ModelAndView accueil(){
        ModelAndView model = new ModelAndView("accueil");
        model.addObject("nbEmployes", employeService.countAllEmploye());
        return model;
    }
}
