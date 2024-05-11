/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.manoelfilho902.Portifolio_be.model.emunerate.DocumentType;
import com.github.manoelfilho902.Portifolio_be.model.emunerate.RoleType;
import com.github.manoelfilho902.Portifolio_be.model.emunerate.TransactionStatus;
import com.github.manoelfilho902.Portifolio_be.model.emunerate.TransactionType;
import com.github.manoelfilho902.Portifolio_be.model.entity.Client;
import com.github.manoelfilho902.Portifolio_be.model.entity.Document;
import com.github.manoelfilho902.Portifolio_be.model.entity.Person;
import com.github.manoelfilho902.Portifolio_be.model.entity.Role;
import com.github.manoelfilho902.Portifolio_be.model.entity.Transaction;
import com.github.manoelfilho902.Portifolio_be.model.entity.User;
import com.github.manoelfilho902.Portifolio_be.model.support.RandomNamesRepository;
import com.github.manoelfilho902.Portifolio_be.service.ClientService;
import com.github.manoelfilho902.Portifolio_be.service.DocumentService;
import com.github.manoelfilho902.Portifolio_be.service.PersonService;
import com.github.manoelfilho902.Portifolio_be.service.RoleService;
import com.github.manoelfilho902.Portifolio_be.service.TransactionService;
import com.github.manoelfilho902.Portifolio_be.service.UserService;
import de.svenjacobs.loremipsum.LoremIpsum;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * create and insert fake data.
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Component
public class AfterStart {

    @Autowired
    private RoleService rs;
    @Autowired
    private DocumentService ds;
    @Autowired
    private PersonService ps;
    @Autowired
    private UserService us;
    @Autowired
    private TransactionService ts;
    @Autowired
    private ClientService cs;

    @Autowired
    private LoremIpsum ipsum;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    @Qualifier(value = "AdminPass")
    private String AdminPass;

    @Autowired
    private PasswordEncoder encoder;
    private static final Logger LOG = Logger.getLogger(AfterStart.class.getName());

    @PostConstruct
    public void init() {
        populateRoles();
        createMainAdmin();
        populateDocument();
    }

    @Transactional
    public void populateRoles() {
        long count = rs.count();
        if (count == 0) {
            for (RoleType value : RoleType.values()) {
                Role role = new Role(value, value.getDescription());
                role.setActive(Boolean.TRUE);
                rs.save(role);
            }
        }
    }

    @Transactional
    public void populateDocument() {
        String[] InvalidDocuments = {"11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "66666666666", "77777777777", "88888888888", "99999999999", "12345678909"};
        LocalDate now = LocalDate.now();
        List<Document> documents = new ArrayList<>();
        for (String InvalidDocument : InvalidDocuments) {
            Document document = new Document();
            document.setExpirationDate(getRamdonDateBetween(now, now.plusYears(5)));
            document.setType(DocumentType.CPF);
            document.setNumber(InvalidDocument);

            documents.add(ds.save(document));
        }

        populatePerson(documents);
    }

    @Transactional
    public void populatePerson(List<Document> documents) {
        List<Person> people = new ArrayList<>();
        for (Document document : documents) {
            Person person = new Person();
            person.setBirthday(getRamdonDateBetween(LocalDate.now().minusYears(40), LocalDate.now().minusYears(10)));
            person.setDocuments(Set.of(document));
            person.setFirstName(RandomNamesRepository.getRandomFirstName());
            person.setLastName(RandomNamesRepository.getRandomLastName());

            people.add(ps.save(person));

        }
        populateUser(people);
    }

    @Transactional
    public void populateUser(List<Person> people) {
        Optional<Role> findOne = rs.findOne(Example.of(new Role(RoleType.USER)));
        List<User> users = new ArrayList<>();

        for (Person person : people) {
            long nextLong = ThreadLocalRandom.current().nextLong(0, 100);
            User user = new User(person.getFirstName().concat(Long.toString(nextLong)), person.getFirstName().concat(Long.toString(nextLong)).concat("@myExamplemail.com"));
            user.setPassword(encoder.encode("thisIsmypassword"));
            user.setPerson(person);
            user.setRoles(Set.of(findOne.orElseThrow()));
            users.add(us.save(user));
        }
        populateClients(people, users);
    }

    @Transactional
    public void populateClients(List<Person> people, List<User> users){
        List<Client> clients = new ArrayList<>();
        for (Person person : people) {
            Client client = new Client();
            client.setPerson_id(person);
            clients.add(cs.save(client));
        }
        populateTransaction(users, clients);
    }
    @Transactional
    public void populateTransaction(List<User> users, List<Client> clients) {
        if (!users.isEmpty() && !clients.isEmpty()) {
            LocalDate now = LocalDate.now();
            TransactionType[] types = TransactionType.values();
            TransactionStatus[] statusEnum = TransactionStatus.values();

            for (int i = 0; i < 1000; i++) {
                Integer user = ThreadLocalRandom.current().nextInt(0, users.size());
                Integer client = ThreadLocalRandom.current().nextInt(0, clients.size());

                Transaction transaction = new Transaction();

                transaction.setValue(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1999.99)));
                transaction.setCreation_date(now);
                transaction.setDue_date(getRamdonDateBetween(now, now.plusMonths(8)));
                transaction.setClient_id(clients.get(client));
                transaction.setUser_id(users.get(user));
                transaction.setDescription(ipsum.getWords(8));
                transaction.setObs(ipsum.getParagraphs(2));
                transaction.setType(types[ThreadLocalRandom.current().nextInt(0, types.length)]);
                transaction.setStatus(statusEnum[ThreadLocalRandom.current().nextInt(0, statusEnum.length)]);
                
                ts.save(transaction);
            }
        }

    }

    @Transactional
    public void createMainAdmin() {
        LocalDate now = LocalDate.now();
        Document adminDocument = new Document();
        adminDocument.setExpirationDate(getRamdonDateBetween(now, now.plusYears(5)));
        adminDocument.setType(DocumentType.CPF);
        adminDocument.setNumber("00000000000");
        adminDocument = ds.save(adminDocument);

        Person admin = new Person();
        admin.setBirthday(now);
        admin.setDocuments(Set.of(adminDocument));
        admin.setFirstName("Administrator");
        admin.setLastName("");
        admin = ps.save(admin);

        Optional<Role> findOne = rs.findOne(Example.of(new Role(RoleType.ADMINISTRATOR)));

        User adminUser = new User("admin", "admin@myaplication.com");
        adminUser.setPassword(encoder.encode(AdminPass));
        adminUser.setPerson(admin);
        adminUser.setRoles(Set.of(findOne.orElseThrow()));
        us.save(adminUser);
    }

    private LocalDate getRamdonDateBetween(LocalDate startBound, LocalDate finalBound) {
        long nextLong = ThreadLocalRandom.current().nextLong(startBound.toEpochDay(), finalBound.toEpochDay());
        return LocalDate.ofEpochDay(nextLong);
    }

}
