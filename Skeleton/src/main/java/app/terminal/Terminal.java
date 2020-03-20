package app.terminal;


import app.domain.dto.PersonDto;
import app.domain.model.Person;
import app.services.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Terminal implements CommandLineRunner {
    private static final String personJson = "{\n" +
            "  \"firstName\": \"Mitko3\",\n" +
            "  \"phoneNumbers\": [\n" +
            "    {\n" +
            "      \"number\": \"77\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"number\": \"8888\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"number\": \"999\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"addressDto\": {\n" +
            "    \"city\": \"Sofia\",\n" +
            "    \"country\": \"bg\"\n" +
            "  }\n" +
            "}";
    private PersonService personService;

    @Autowired
    public Terminal(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
       // gsonToJson();
        gsonFromJson();
    }

    private void gsonFromJson() {
        Gson  gson = new GsonBuilder().create();
        PersonDto personDto = gson.fromJson(personJson, PersonDto.class);
        ModelMapper mapper = new ModelMapper();

        Person person =  mapper.map(personDto, Person.class);
        this.personService.save(person);
    }


    private void gsonToJson() {
        Person p = personService.getById(1L);
        if (p == null) {
            System.out.println("ERRORR");
            return;
        }

        PersonDto dto = new PersonDto(p);
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        System.out.println(gson.toJson(dto));
    }
}
