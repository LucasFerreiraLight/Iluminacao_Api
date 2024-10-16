package br.com.Iluminacao.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_USUARIOS")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USUARIOS"
    )
    @SequenceGenerator(
            name = "SEQ_USUARIOS",
            sequenceName = "SEQ_USUARIOS",
            allocationSize = 1
    )
    @Column(name = "USUARIO_ID")
    private Long usuarioId;

    private String nome;
    private String email;
    private String senha;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    // Enum
    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    // UserDetails


    // Pega o Password e pode return a Key ou senha
    @Override
    public String getPassword() {
        return this.senha;
    }

    // Pega o Username e pode return a Login do Usuario
    @Override
    public String getUsername() {
        return this.email;
    }


    // Returna a informação se a conta do Usuario está vencida ou não.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Returna a informação se a Conta do Usuario está locked ou seja está Bloquada.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Returna a informação se as credencias do Usuario estão bloquadas.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Returna a informação se a conta do Usuario está ativa ou não.
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER")
            );
        }
    }
}