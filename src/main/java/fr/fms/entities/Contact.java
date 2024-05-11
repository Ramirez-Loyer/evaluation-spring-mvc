package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    //@Size(min = 10, max = 50)
    private String name;

    @NotNull
    //@Size(min = 10, max = 50)
    private String lastName;

    @NotNull
    //@Size(min = 10, max = 50)
    private String email;

    @NotNull
    //@Size(min = 10, max = 50)
    private String phone;

    @NotNull
    //@Size(min = 10, max = 50)
    private String address;
}


//https://www.youtube.com/watch?v=il_gXsZngUs