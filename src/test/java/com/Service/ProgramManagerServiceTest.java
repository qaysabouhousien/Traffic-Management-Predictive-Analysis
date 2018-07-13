package com.Service;

import com.Configration.ApplicationConfiguration;
import com.DAO.ProgramMangerDao;
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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
      ApplicationConfiguration.class,
        ManagerTestConfig.class}
)
public class ProgramManagerServiceTest {


    @Autowired
    private UserService programMangerService;

    @MockBean
    ProgramMangerDao programMangerDao;


    @Before
    public void setup(){
        User user = new ProgramManger();
        user.setName("Qays");
        user.setId("1");
        Mockito.when(programMangerDao.getUserById(Integer.parseInt(user.getId())))
                .thenReturn(user);
        Mockito.when(programMangerDao.getUserByName(user.getName())).thenReturn(user);

    }


    @Test
    public void getUserByIdTest(){
        int id = 1;
        User found = programMangerService.getUserById(id);
        assertThat(found.getId()).isEqualTo(String.valueOf(id));
    }
    @Test
    public void loginTest(){
        int id = 9;
        String name = "qays";
        String password = "1234";
        ProgramManger manger =  new ProgramManger();
        int logged = programMangerService.logIn(manger);
        assertThat(logged).isEqualTo(id);

    }

}


@TestConfiguration
class ManagerTestConfig{

    @Bean
    public UserService ProgramManagerService() {
        return new ProgramMangerService();
    }

}
