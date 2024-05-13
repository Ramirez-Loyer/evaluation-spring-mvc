package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty
    @Size(min = 2, max = 25)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 25)
    private String lastName;

    @NotEmpty
    @Size(min = 5, max = 50)
    private String email;

    @NotEmpty
    @Size(min = 5, max = 25)
    private String phone;

    @NotEmpty
    @Size(min = 5, max = 100)
    private String address;
}


//https://www.youtube.com/watch?v=il_gXsZngUs