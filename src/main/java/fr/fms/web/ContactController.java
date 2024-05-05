package fr.fms.web;


import fr.fms.dao.ContactRepository;
import fr.fms.entities.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {
    private final ContactRepository contactRepository;
    String contactString = "contact";

    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /** "/index" mapping
     * @author Ramirez-LoyerA
     * @param model spring model
     * @param @RequestParam (name = page name, defaultValue = default page number, int page = page number)
     **/

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name= "page", defaultValue = "0") int page,
                                        @RequestParam(name="keyword", defaultValue = "") String kw) {
        Page<Contact> allContacts = contactRepository.findByLastNameContains(kw, PageRequest.of(page, 5));
        model.addAttribute("listContact", allContacts.getContent());
        model.addAttribute("pages", new int[allContacts.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);

        return "index";
    }


    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", new Contact());
        return contactString;
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        contactRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

   /* @GetMapping("/save")
    public String save (@Valid @ModelAttribute("contact")BindingResult bindingResult, Model model) {
      Contact contact = contactRepository.findById(Long);
      contactRepository.save(contact);
      return "redirect:/index";
        }*/
    }





