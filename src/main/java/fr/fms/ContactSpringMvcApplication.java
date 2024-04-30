package fr.fms;

import fr.fms.dao.ContactRepository;
import fr.fms.entities.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import java.util.Arrays;
//import java.util.List;


@SpringBootApplication
public class ContactSpringMvcApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ContactSpringMvcApplication.class);

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	public ContactSpringMvcApplication(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(ContactSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		contactRepository.save(new Contact(null, "Alejandra", "Ramirez", "alera@gmail.com", "065254565", "Allee dAncely" ));
		contactRepository.save(new Contact(null, "Frédéric", "BEC", "fred@gmail.com", "085555565", "Muret" ));

		contactRepository.findAll().forEach(a -> logger.info(a.toString()));
		System.out.println("contactRepository" + contactRepository.toString());


		//generateData(); //pour les rôles
	}
}
