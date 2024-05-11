package fr.fms;

import fr.fms.dao.ContactRepository;
import fr.fms.entities.Contact;
//import fr.fms.dao.RoleRepository;
//import fr.fms.dao.UserRepository;
//import fr.fms.entities.Role;
//import fr.fms.entities.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class ContactSpringMvcApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ContactSpringMvcApplication.class);

	@Autowired
	ContactRepository contactRepository;
	//@Autowired
	//UserRepository userRepository;
	//@Autowired
	//RoleRepository roleRepository;
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	//@Autowired
	//private SecurityConfig securityConfig;

	@Autowired
	public ContactSpringMvcApplication(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(ContactSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		contactRepository.save(new Contact(null, "Alejandra", "Ramirez", "alera@gmail.com", "065254565", "Allee dAncely"));
		contactRepository.save(new Contact(null, "Frédéric", "BEC", "fred@gmail.com", "085555565", "Muret"));
		contactRepository.save(new Contact(null, "Claire", "Avila", "claire@gmail.com", "05555965", "Saint-Geours"));
		contactRepository.save(new Contact(null, "Arthur", "Gibert", "arthur@gmail.com", "085558565", "Saint-Geourg"));
		contactRepository.save(new Contact(null, "Donovan", "Seulin", "donovan@gmail.com", "05578965", "Saint-Geours"));
		contactRepository.save(new Contact(null, "Max", "Thiroux", "max@gmail.com", "082228565", "Saint-Geourg"));
		contactRepository.save(new Contact(null, "Gilles", "Quodbach", "gilles@gmail.com", "085554465", "Saint-Geourg"));


		contactRepository.findAll().forEach(a -> logger.info(a.toString()));
		System.out.println("contactRepository" + contactRepository.toString());

	}
}
		//generateData(); //pour les rôles
	//}

/*	public void generateData() {
		Role visitor = roleRepository.save(new Role("visitor", null));
		Role user = roleRepository.save(new Role("user", null));

		createUserWithRoles("alejandra", "12345", true, user, visitor);
		createUserWithRoles("coco", "12345", true, user);
	}

		private void createUserWithRoles(String username, String password, boolean active, Role... roles) {
			List<Role> userRoles = Arrays.asList(roles);

			String encodedPassword = passwordEncoder.encode(password);

			userRepository.save(new User(username, encodedPassword, active, userRoles, null));

	}
}*/
