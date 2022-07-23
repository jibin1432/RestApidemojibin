package ApiTests.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {
    private int id;
    private String Username;
    private String Password;


}
