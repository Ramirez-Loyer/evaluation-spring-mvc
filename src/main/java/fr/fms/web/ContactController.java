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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ContactController {
    private final ContactRepository contactRepository;
    String contactString = "contact";
    private RedirectAttributes redirectAttributes;

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

    @GetMapping("/contactsList")
    public String contactsList (Model model, @RequestParam(name= "page", defaultValue = "0") int page,
                        @RequestParam(name="keyword", defaultValue = "") String kw) {
        Page<Contact> allContacts = contactRepository.findByLastNameContains(kw, PageRequest.of(page, 5));
        model.addAttribute("listContact", allContacts.getContent());
        model.addAttribute("pages", new int[allContacts.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);

        return "contactsList";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        contactRepository.deleteById(id);
        return "redirect:/contactsList?page="+page+"&keyword="+keyword;
    }


   @PostMapping("/saveContact")
    public String save (Model model, @Valid Contact contact , BindingResult bindingResult) {
      if(bindingResult.hasErrors()) {
          return "contact";
      }
        contactRepository.save(contact);
      //return "redirect:/contactsList?keyword="+contact.getName();
       return "redirect:/contactsList?keyword"+contact.getName();
        }



    @GetMapping("/update")
    public String update(Model model, @RequestParam(name="id") Long id) {
      Contact contact= contactRepository.findById(id).get();
        model.addAttribute("contact", contact);
       return "update";
    }





}



