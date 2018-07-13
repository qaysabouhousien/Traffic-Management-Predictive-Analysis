package com.Controller;


import com.Configration.ApplicationConfiguration;
import com.Entity.ProgramManger;
import com.Service.ProgramMangerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProgramMangerController.class)
@Import(ApplicationConfiguration.class)
public class ProgramManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    public final static String BASECONTROLLERURL = "/ProgramManager";

    @MockBean
    private ProgramMangerService programMangerService;

    @Test
    public void givenId_whenGetManager_thenReturnJsonArray()throws Exception {

        String id = "9";
        ProgramManger manager = new ProgramManger();
        manager.setId(id);
        given(programMangerService.getUserById(Integer.valueOf(id))).willReturn(manager);

        mockMvc.perform(get(BASECONTROLLERURL+"/{id}",id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(id)));
    }

    @Test
    public void givenManager_whenGetId_thenReturnId() throws Exception {
        int id = 9;
        String managerName = "qays";
        String managerPassword = "1234";
        ProgramManger manager = new ProgramManger();
        manager.setName(managerName);
        manager.setPassword(managerPassword);
        given(programMangerService.logIn(manager)).willReturn(id);
        mockMvc.perform(post(BASECONTROLLERURL+"/Login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content("{ }")

                .content("{ " +
                        "\"name\":\""+managerName+"\","
                        +"\"password\":\""+managerPassword+"\""
                        +" }")

                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(id)))
                .andDo(print());
    }

}
