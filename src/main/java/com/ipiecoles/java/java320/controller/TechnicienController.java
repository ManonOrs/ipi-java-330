package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.service.ManagerService;
import com.ipiecoles.java.java320.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TechnicienController {
    @Autowired
    private TechnicienService technicienService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/techniciens/{id}/manager/delete"
    )
    public RedirectView deleteManager(@PathVariable("id") Long id){
        technicienService.deleteManager(id);
        return new RedirectView("/employes/" + id);
    }
}
