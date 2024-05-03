/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.manoelfilho902.Portifolio_be.model.emunerate.DocumentType;
import com.github.manoelfilho902.Portifolio_be.model.emunerate.RoleType;
import com.github.manoelfilho902.Portifolio_be.model.entity.Document;
import com.github.manoelfilho902.Portifolio_be.model.entity.Person;
import com.github.manoelfilho902.Portifolio_be.model.entity.Role;
import com.github.manoelfilho902.Portifolio_be.model.entity.User;
import com.github.manoelfilho902.Portifolio_be.model.support.RandomNamesRepository;
import com.github.manoelfilho902.Portifolio_be.service.DocumentService;
import com.github.manoelfilho902.Portifolio_be.service.PersonService;
import com.github.manoelfilho902.Portifolio_be.service.RoleService;
import com.github.manoelfilho902.Portifolio_be.service.UserService;
import de.svenjacobs.loremipsum.LoremIpsum;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
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
    private LoremIpsum ipsum;
    @Autowired
    private ObjectMapper mapper;
    
    @Autowired
    private PasswordEncoder encoder;
    private static final Logger LOG = Logger.getLogger(AfterStart.class.getName());

    @PostConstruct
    public void init() {
        populateRoles();
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
        String[] InvalidDocuments = {"00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "66666666666", "77777777777", "88888888888", "99999999999", "12345678909"};
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
            
            try {
                LOG.info(mapper.writeValueAsString(person));
            } catch (JsonProcessingException ex) {
                Logger.getLogger(AfterStart.class.getName()).log(Level.SEVERE, null, ex);
            }            
            people.add(ps.save(person));

        }
        populateUser(people);
    }

    @Transactional
    public void populateUser(List<Person> people) {
        Optional<Role> findOne = rs.findOne(Example.of(new Role(RoleType.USER)));

        for (Person person : people) {
            long nextLong = ThreadLocalRandom.current().nextLong(0, 100);
            User user = new User(person.getFirstName().concat(Long.toString(nextLong)), person.getFirstName().concat(Long.toString(nextLong)).concat("@myExamplemail.com"));
            user.setPassword(encoder.encode("thisIsmypassword"));
            user.setPerson(person);
            user.setRoles(Set.of(findOne.orElseThrow()));
            us.save(user);
        }
    }

    private LocalDate getRamdonDateBetween(LocalDate startBound, LocalDate finalBound) {
        long nextLong = ThreadLocalRandom.current().nextLong(startBound.toEpochDay(), finalBound.toEpochDay());
        return LocalDate.ofEpochDay(nextLong);
    }

}
