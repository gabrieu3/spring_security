package com.balestech.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "gb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    /**
     * Um usuário pode ter múltiplas permissões
     */
    @OneToMany(mappedBy = "user")
    private Set<Authority> authorities;

}
