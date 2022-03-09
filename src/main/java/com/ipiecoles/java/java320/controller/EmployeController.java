package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.model.Commercial;
import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.model.Manager;
import com.ipiecoles.java.java320.model.Technicien;
import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @RequestMapping(value = "/employes/{id}")
    public ModelAndView detailEmploye(@PathVariable("id")Long id){
        ModelAndView model = new ModelAndView("detail");
        model.addObject("nbEmployes", employeService.countAllEmploye());
        model.addObject("employe", employeService.findById(id));
        return model;
    }

    @RequestMapping(value = "/employes", params = "matricule")
    public ModelAndView detailEmploye(@RequestParam String matricule){
        ModelAndView model = new ModelAndView("detail");
        model.addObject("nbEmployes", employeService.countAllEmploye());
        model.addObject("employe", employeService.findMyMatricule(matricule));
        return model;
    }

    @RequestMapping(value = "/employes")
    public ModelAndView listeEmployes(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortProperty, @RequestParam String sortDirection){
        ModelAndView model = new ModelAndView("list");
        Page<Employe> employes = employeService.findAllEmployes(page, size, sortProperty, sortDirection);
        model.addObject("employes", employes);
        model.addObject("nbEmployes", employes.getTotalElements());
        model.addObject("start", page * size + 1);
        model.addObject("end", page * size + employes.getNumberOfElements());
        model.addObject("sortProperty", sortProperty);
        model.addObject("sortDirection", sortDirection);
        return model;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/employes/new/{typeEmploye}"
    )
    public ModelAndView newEmploye(@PathVariable String typeEmploye){
        ModelAndView modelAndView = new ModelAndView("detail");
        switch (typeEmploye.toLowerCase()){
            case "commercial":
                modelAndView.addObject("employe", new Commercial());
                break;
            case "technicien":
                modelAndView.addObject("employe", new Technicien());
                break;
            case "manager":
                modelAndView.addObject("employe", new Manager());
                break;
        }
        return modelAndView;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/employes/technicien",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public RedirectView newEmploye(Technicien technicien){
        if(technicien.getId() == null){
            //Création
            technicien = employeService.creerEmploye(technicien);
        }
        else {
            //Modification
            technicien = employeService.updateEmploye(technicien.getId(), technicien);
        }
        //Redirection vers /employes/id
        return new RedirectView("/employes/" + technicien.getId());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/employes/{id}/delete"
    )
    public RedirectView deleteEmploye(@PathVariable Long id){
        employeService.deleteEmploye(id);
        return new RedirectView("/employes?page=0&size=10&sortProperty=matricule&sortDirection=ASC");
    }
}
