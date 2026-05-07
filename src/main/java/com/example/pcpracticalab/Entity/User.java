package com.example.pcpracticalab.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuarios")
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;

    // minimo una letra mayuscula
    private String firstName;
    private String lastName;

    //Contraseña: mínimo 8 caracteres, al menos 1 letra y 1 número

    private String password;
    private String role;

    /*@ManyToOne
    @JoinColumn(name="vuelo")
    private Vuelo vuelo;
    */

}
