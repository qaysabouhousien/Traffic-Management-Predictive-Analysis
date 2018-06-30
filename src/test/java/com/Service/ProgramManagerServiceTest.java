package com.Service;

import com.Configration.ApplicationConfigration;
import com.DAO.ProgramMangerDao;
import com.Entity.ProgramManger;
import com.Entity.User;
import com.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
      ApplicationConfigration.class,
        TestConfig.class}
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
        User found = programMangerDao.getUserById(id);
        assertThat(found.getId()).isEqualTo(String.valueOf(id));
    }
}
