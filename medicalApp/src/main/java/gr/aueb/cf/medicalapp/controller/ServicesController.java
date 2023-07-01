package gr.aueb.cf.medicalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    /*-------------------------- SPEECH THERAPY & SPECIAL EDUCATION PAGES  --------------------------*/

    @GetMapping("/speech_therapy")
    public String speechTherapyPage(Model model) {
        model.addAttribute("pageTitle", "Speech Therapy");
        return "speech_therapy";
    }

    @GetMapping("/special_education")
    public String specialEducationPage(Model model) {
        model.addAttribute("pageTitle", "Special Education");
        return "special_education";
    }



}
