package com.mcamelo.msgApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.repositories.UserRepository;
import com.mcamelo.msgApp.utils.Factory;
import com.mcamelo.msgApp.utils.TokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@Transactional
@AutoConfigureMockMvc
public class UserExemple {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    public UserRepository userRepository;

    private Long existingId;
    private Long nonExistingId;
    private String adminUsername;
    private String adminPassword;

    private Long countTotalUsers;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalUsers = 1l;

        adminUsername = "mario";
        adminPassword = "123";

    }

    @Test
    public void firstTest() throws Exception{
        var user1 = Factory.createUser();

//        userRepository.save(user1);
        var result = userRepository.findById(1L);
       Assertions.assertTrue(result.isPresent());

    }

    @Test
    public void findAllShouldReturnUserDTOWhenIdExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        UserDTO userDTO = Factory.createUserDTO();
        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
}
