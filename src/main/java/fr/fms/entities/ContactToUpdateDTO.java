package fr.fms.entities;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Setter
@Getter
public class ContactToUpdateDTO {
    @NotNull
    @Size(min = 10, max = 50)
    private String nom;

    private Long id;

}
