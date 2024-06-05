package it.pit.security.authservice.security.payload;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDto {
    private String username;
    private String password;
}

//Il client dovrà inviare un oggetto JSON nel body con questa forma
/*{
    "username": "francescaneri",
    "password": "qwerty"
}*/
