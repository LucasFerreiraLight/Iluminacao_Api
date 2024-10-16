package br.com.Iluminacao.dto;


import br.com.Iluminacao.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(
        Long id,
        @NotBlank(message = "O nome do usuário é Obrigatório!")
        String nome,
        @NotBlank(message = "O email do usuário é Obrigatório!")
        @Email(message = "O email do usuário não é valido")
        String email,

        @NotBlank(message = "A senha do usuário é Obrigatório!")
        @Size(min = 6, max = 20, message = "A senha deve conter, entre 6 a 20 Characteres.")
        String senha,

        UsuarioRole role
) {
}
