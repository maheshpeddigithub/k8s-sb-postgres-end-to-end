package com.ssil.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssil.exception.ErrorMessage;
import com.ssil.model.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;
    private Book book;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        book = new Book();
        book.setId(1);
        book.setTitle("title1");
    }

    @Test
    public void testSaveBookSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/book").content(mapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(equalTo("[" + mapper.writeValueAsString(book) + "]")));
    }

    @Test
    public void testResourceNotFoundExceptionWhenResourceNotExists() throws Exception {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        errorMessage.setErrorMessage("Book not found");

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/book/999").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isPreconditionFailed())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(errorMessage))));
    }

    @After
    public void tearDown() {
        mapper = null;
        book = null;
    }

}
