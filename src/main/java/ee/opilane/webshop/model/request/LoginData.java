package ee.opilane.webshop.model.request;


import lombok.Data;

@Data
public class LoginData {
    private String email;
    private String password;
}
