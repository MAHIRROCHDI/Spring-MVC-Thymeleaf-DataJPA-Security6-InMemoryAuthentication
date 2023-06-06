package com.maher.hospitale.web;

import com.maher.hospitale.entities.Patient;
import com.maher.hospitale.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository ;
    @GetMapping("/user/index")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String index(Model model , @RequestParam(name = "page" , defaultValue = "0") int p ,@RequestParam(name = "size" , defaultValue = "4") int s
    ,@RequestParam(name = "keyword" , defaultValue = "") String kw) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(kw , PageRequest.of(p , s));
        model.addAttribute("listPatients" , pagePatients.getContent()) ;
        model.addAttribute("pages" , new int[pagePatients.getTotalPages()]) ;
        model.addAttribute("currentPage" ,p) ;
        model.addAttribute("keyword" , kw) ;
        return "patients" ;
    }
    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public String delete(Long id , String keyword , int page) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword ;
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String home() {
        return "redirect:/user/index" ;
    }

    @GetMapping("/admin/formPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatient(Model model) {
        model.addAttribute("patient" , new Patient());
        return "formPatient" ;
    }
    @PostMapping("/savePatient")
    public String savePatient(@Valid Patient patient , BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/user/index?keyword="+patient.getNom() ;
        }
        patientRepository.save(patient) ;
        return "redirect:/index?keyword="+patient.getNom() ;
    }

    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model , @RequestParam(name = "id")  Long id) {
        Patient patient = patientRepository.findById(id).get() ;
        model.addAttribute("patient" , patient);
        return "editPatient" ;
    }

}
