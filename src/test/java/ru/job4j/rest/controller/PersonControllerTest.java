package ru.job4j.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.rest.model.Person;
import ru.job4j.rest.repo.PersonRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void whenGetAll() throws Exception {
        Person p1 = new Person(1, "l1", "p1");
        Person p2 = new Person(2, "l2", "p2");
        Mockito.when(personRepository.findAll()).thenReturn(List.of(p1, p2));
        String content = mockMvc.perform(get("/person"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String expect = mapper.writer().writeValueAsString(List.of(p1, p2));
        Assert.assertEquals(expect, content);
    }

    @Test
    public void whenGetById() throws Exception {
        Person p1 = new Person(1, "l1", "p1");
        Mockito.when(personRepository.findById(1)).thenReturn(java.util.Optional.of(p1));
        String content = mockMvc.perform(get("/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String expect = mapper.writer().writeValueAsString(p1);
        Assert.assertEquals(expect, content);
    }

    @Test
    public void whenCreate() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Person p1 = new Person(1, "l1", "p1");
        String requestBody = mapper.writer().writeValueAsString(p1);

        Mockito.when(personRepository.save(p1)).thenReturn(p1);

        String content = mockMvc.perform(post("/person")
                .contentType("application/json")
                .content(requestBody)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);

        Mockito.verify(personRepository).save(argument.capture());

        Assert.assertEquals(requestBody, content);
        Assert.assertEquals(1, p1.getId());
        Assert.assertEquals("l1", p1.getLogin());
        Assert.assertEquals("p1", p1.getPassword());
    }

}