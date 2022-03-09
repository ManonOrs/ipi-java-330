package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.model.Technicien;
import com.ipiecoles.java.java320.service.EmployeService;
import com.ipiecoles.java.java320.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/managers/{id}/techniciens/add"
    )
    public RedirectView addTechnicien(@PathVariable("id") Long id, @RequestParam String matriculeToAdd){
        managerService.addTechniciens(id, matriculeToAdd);
        return new RedirectView("/employes/" + id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/managers/{idManager}/techniciens/{idTech}/delete"
    )
    public RedirectView deleteTechnicien(@PathVariable("idManager") Long idManager, @PathVariable("idTech") Long idTech){
        managerService.deleteTechniciens(idManager, idTech);
        return new RedirectView("/employes/" + idManager);
    }
}
