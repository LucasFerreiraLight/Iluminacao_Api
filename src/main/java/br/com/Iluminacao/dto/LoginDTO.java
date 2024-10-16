package br.com.Iluminacao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(

        @NotBlank(message = "O email do usuário é obrigatório!")
        @Email(message = "O email está em formato incorreto!")
        String email,

        @NotBlank(message = "A senha do usuário é obrigatório!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 a 20 characteres")
        String senha
) {

}
