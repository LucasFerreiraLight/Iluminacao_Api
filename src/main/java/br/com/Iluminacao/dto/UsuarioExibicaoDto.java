package br.com.Iluminacao.dto;


import br.com.Iluminacao.model.Usuario;
import br.com.Iluminacao.model.UsuarioRole;

public record UsuarioExibicaoDto(
        Long id,
        String nome,
        String email,
        UsuarioRole role
) {

    //Construtor
    public UsuarioExibicaoDto(Usuario usuario) {
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}

