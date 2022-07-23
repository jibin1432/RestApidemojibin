package ApiTests.Tests;

import ApiTests.Models.User;
import ApiTests.Service.UserServie;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class UserTest {
    private UserServie service;

    @BeforeTest
    public void setup(){
        service = new UserServie();
    }

    @Test
    public void AddNewUser()throws IOException{
        ObjectMapper mapper= new ObjectMapper();
       User user= mapper.readValue(new File("src/test/resources/user.json"),User.class);
        service.AddNewUser(user);
    }

    @Test
    public void getAllUsrs() throws IOException {
        service.GetAllUsers();
    }
    @Test
    public void getSingleUser() throws IOException {
        int id;
        ObjectMapper mapper= new ObjectMapper();
        User user= mapper.readValue(new File("src/test/resources/user.json"),User.class);
        id= service.AddNewUser(user);
        service.GetSingleUser(id);
    }
    @Test
    public void updateUser() throws IOException {
        int id;
        ObjectMapper mapper= new ObjectMapper();
        User user= mapper.readValue(new File("src/test/resources/user.json"),User.class);
        id= service.AddNewUser(user);
        user.setId(id);
        user.setUsername("updated username "+id );
        service.UpdateUserName(id,user);
    }
    @Test
    public void deleteUser() throws IOException {
        int id;
        ObjectMapper mapper= new ObjectMapper();
        User user= mapper.readValue(new File("src/test/resources/user.json"),User.class);
        id= service.AddNewUser(user);
        service.DeleteUser(id);
    }
}
