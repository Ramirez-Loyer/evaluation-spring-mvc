package fr.fms;

import fr.fms.dao.ContactRepository;
import fr.fms.dao.RoleRepository;
import fr.fms.dao.UserRepository;
import fr.fms.entities.Contact;
import fr.fms.dao.RoleRepository;
//import fr.fms.dao.UserRepository;
//import fr.fms.entities.Role;
//import fr.fms.entities.User;

import fr.fms.entities.Role;
import fr.fms.entities.User;
import fr.fms.security.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SecurityConfig securityConfig;

	@Autowired
	public ContactSpringMvcApplication(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(ContactSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		contactRepository.save(new Contact(null, "Alejandra", "Ramirez", "alex@mail.com", "0651409070", "48 Allee d'Ancely, 31300"));
		contactRepository.save(new Contact(null, "Frédéric", "BEC", "fred@gmail.com", "0651408596", "12 Rue des Pommes, 31395"));
		contactRepository.save(new Contact(null, "Claire", "Avila", "claire@mail.com", "059408596", "90 Route de Bayonne, 40230"));
		contactRepository.save(new Contact(null, "Arthur", "Gibert", "arthur@mail.com", "059567496", "56 Route de la Gare, 40230"));
		contactRepository.save(new Contact(null, "Donovan", "Seulin", "donovan@mail.com", "059561230", "1 Chemin de la Clé, 40230"));
		contactRepository.save(new Contact(null, "Max", "Thiroux", "max@mail.com", "069558796", "2 Rue des Fleurs, 40230"));
		contactRepository.save(new Contact(null, "Gilles", "Quodbach", "gilles@mail.com", "059259788", "45 Avenue des Oiseux, 40230"));
		contactRepository.save(new Contact(null, "Mohamed", "El Babili", "mohamed@mail.com", "059481230", "Rue de Metz, 31000"));
		contactRepository.save(new Contact(null, "Martial", "Bret", "martial@mail.com", "069558456", "10 Rue du canard, 31000"));
		contactRepository.save(new Contact(null, "Tristan", "Laclau", "tristan@mail.com", "063359788", "4 Rue Neuve, 40230"));


		contactRepository.findAll().forEach(a -> logger.info(a.toString()));
		System.out.println("contactRepository" + contactRepository.toString());


		generateData(); //pour les rôles
	}


	public void generateData() {
		Role user = roleRepository.save(new Role("users", null));
		createUserWithRoles("Alejandra", "Alejandra2024!", true, user);
		createUserWithRoles("Coco", "Coco2024!", true, user);
	}

private void createUserWithRoles(String username, String password, boolean active, Role... roles) {
	List<Role> userRoles = Arrays.asList(roles);
	String encodedPassword = passwordEncoder.encode(password);
	userRepository.save(new User(username, encodedPassword, active, userRoles));
	}
}
