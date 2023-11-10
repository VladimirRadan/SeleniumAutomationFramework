package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class LoginUser {

    //pojo
    private String username;
    private String password;


}
