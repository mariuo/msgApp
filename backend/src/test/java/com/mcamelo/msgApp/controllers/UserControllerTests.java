package com.mcamelo.msgApp.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.services.UserService;
import com.mcamelo.msgApp.services.exceptions.ResourceNotFoundException;
import com.mcamelo.msgApp.utils.Factory;
import com.mcamelo.msgApp.utils.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private PageImpl<UserDTO> page;
    private UserDTO userDTO;
    private Long existingId;
    private Long nonExistingId;
    private String adminUsername;
    private String adminPassword;


    @BeforeEach
    void setUp() throws Exception {
        adminUsername = "mario";
        adminPassword = "123";

        existingId = 1L;
        nonExistingId = 1000L;
        userDTO = Factory.createUserDTO();
        page = new PageImpl<>(List.of(userDTO));

        when(userService.getAllUsers(ArgumentMatchers.any())).thenReturn(page);
        when(userService.findById(existingId)).thenReturn(userDTO);
        when(userService.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

    }


    public void getAllUsersShouldReturnPage() throws Exception{
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());

    }


    public void findByIdShouldReturnUserWhenIdExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result = mockMvc.perform(post("/user/{id}", existingId)
                .header("Authorization", "Bearer " + accessToken)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
    }


    public void findByIdShouldReturnNotFoundWhenIdNonExists() throws Exception{

    }

}
