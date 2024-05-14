package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
  
    @Id
    //@Pattern(regexp = "^[A-Za-z0-9._-]{6,12}$",
           //message = "username must be of 6 to 12 length with no special characters")
    private String username;
   
    //@Pattern(regexp = "^(?=.*[!@#$&*])(?=.*\\d)).{6,}$",
            //message = "password must contain 1 special character and 1 digit")
    private String password;
  
    private boolean active;
  
    @ManyToMany
    private Collection<Role> roles;

}
