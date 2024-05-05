package fr.fms.entities;

//import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
//import java.util.Collection;
import javax.persistence.*;
//import javax.validation.constraints.DecimalMin;
//import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
   //@Size(min = 2, max = 30)
    private String name;

    //@NotNull
   //@Size(min = 2, max = 30)
    private String lastName;

    //@NotNull
    //@Size(min = 10, max = 30)
    private String email;

    //@NotNull
    //@Size(min = 8, max = 20)
    private String phone;

    //@NotNull
    //@Size(min = 10, max = 50)
    private String address;

}
