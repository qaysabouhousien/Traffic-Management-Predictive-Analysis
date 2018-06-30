package com.Controller;


import com.Configration.ApplicationConfigration;
import com.Entity.Admin;
import com.Entity.User;
import com.Service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@Import(ApplicationConfigration.class)
public class AdminControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private final static String BASE_CONTROLLER_URL = "/Admin";

    @MockBean
    private AdminService adminService;

    @Test
    public void getAllUsers() throws Exception{
        String userId= "1";
        User user = new Admin();
        user.setId(userId);
        Collection<User> users = Arrays.asList(user);
        given(adminService.getAllUsers()).willReturn(users);
        mockMvc.perform(get(BASE_CONTROLLER_URL+"/GetAllUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(users.size())))
                .andExpect(jsonPath("$[0].id",is(user.getId())));
    }

}
