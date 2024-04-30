package fr.fms.dao;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import fr.fms.entities.*;
//import java.util.List;

public interface ContactRepository extends JpaRepository <Contact, Long> {
    //Page<Contact> findByDescriptionContains(String name, Pageable pageable);
}
