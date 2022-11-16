package com.balestech.security.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "gb_authority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue
    private Long id;

    private String authority;

    /**
     * Muitas permissões para um único usuário
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
