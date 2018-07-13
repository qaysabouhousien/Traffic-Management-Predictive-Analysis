package com.Service;


import com.Configration.ApplicationConfiguration;
import com.DAO.AdminDao;
import com.Entity.Admin;
import com.Entity.ProgramManger;
import com.Entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        ApplicationConfiguration.class,
        AdminTestConfig.class}
)
public class AdminServiceTest {



    @Autowired
    AdminService service;

    @MockBean
    AdminDao dao;


    @Before
    public void setUp(){
        User admin = new Admin();
        admin.setName("Qays");
        admin.setId("1");

        User manager = new ProgramManger();
        manager.setName("Manager");
        manager.setId("2");

        Collection<User> admins = Arrays.asList(admin);
        Collection<User> managers = Arrays.asList(manager);

        Mockito.when(dao.getUserById(Integer.parseInt(admin.getId()))).thenReturn(admin);
        Mockito.when(dao.getUserByName(admin.getName())).thenReturn(admin);
        Mockito.when(dao.getUsers()).thenReturn(admins);
        Mockito.when(dao.addAdmin(admin)).thenReturn(1);
        Mockito.when(dao.addManger(manager)).thenReturn(2);
        Mockito.when(dao.getUserByName(admin.getName())).thenReturn(admin);
        Mockito.when(dao.getMangers()).thenReturn(managers);
        Mockito.when(dao.deleteAdminById(Integer.parseInt(admin.getId()))).thenReturn(1);
        Mockito.when(dao.deleteAdminById(Integer.parseInt(manager.getId()))).thenReturn(1);
        Mockito.when(dao.updateUserByType(admin,"admin")).thenReturn(1);

    }

    @Test
    public void testGetAdminById(){
        int id = 1;
        User admin = service.getUserById(id);

        assertThat(admin.getId()).isEqualTo(String.valueOf(id));
    }
    @Test
    public void testDeleteAdminById(){
        int id = 1;
        assertThat(service.deleteAdminById(id)).isGreaterThan(-1);
    }

    @Test
    public void testDeleteManagerById(){
        int id = 1;
        assertThat(service.deleteMangerById(id)).isGreaterThan(-1);
    }

    @Test
    public void testUpdateUser(){
        String id = "1";
        String name = "Qays";
        String password = "1234";
        Date regDate = new Date(Calendar.getInstance().getTime().getTime());

        User admin = new Admin();
        admin.setId(id);
        admin.setName(name);
        admin.setPassword(password);
        admin.setRegistration_date(regDate);
        admin.setType("admin");

        User manager = new ProgramManger();
        manager.setId(id);
        manager.setName(name);
        manager.setPassword(password);
        manager.setRegistration_date(regDate);
        manager.setType("manager");

        assertThat(service.updateUser(admin)).isGreaterThan(-1);
        assertThat(service.updateUser(manager)).isGreaterThan(-1);

    }

    @Test
    public void testSaveUser(){
        String name = "qays";
        String password = "1234";
        Date regDate = new Date(Calendar.getInstance().getTime().getTime());

        User admin = new Admin();
        admin.setName(name);
        admin.setPassword(password);
        admin.setRegistration_date(regDate);
        admin.setType("admin");

        User manager = new ProgramManger();
        manager.setName(name);
        manager.setPassword(password);
        manager.setRegistration_date(regDate);
        manager.setType("manager");

        assertThat(service.saveUser(admin)).isGreaterThan(-1).as("Error In Saving Admin");
        assertThat(service.saveUser(manager)).isGreaterThan(-1).as("Error In Saving Manager");
    }

    @Test
    public void testGetAllUsers(){
        String id = "1";
        String name = "qays";
        String password = "1234";
        User admin  = new Admin();
        admin.setId(id);
        admin.setName(name);
        admin.setPassword(password);

        Collection<User> users = Arrays.asList(admin);
        System.out.println(service.getAllUsers());
//        assertThat(service.getAllUsers()).isEqualTo(admin);


    }


    @Test
    public void testLogin(){
        int id = 1;
        String name = "qays";
        String password = "1234";
        User admin = new Admin();
        admin.setName(name);
        admin.setPassword(password);
        assertThat(service.logIn(admin)).isEqualTo(id);
    }
}

@TestConfiguration
class AdminTestConfig{
    @Bean
    public AdminService configAdminService(){
        return new AdminService();
    }
}