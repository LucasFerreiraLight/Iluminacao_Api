package br.com.Iluminacao.dto;

import jakarta.validation.constraints.NotBlank;

public record IluminacaoCadastroDto(

        Long id,
        @NotBlank(message = "O nome da Rua que será instalado é Obrigatório!")
        String nomeRua,
        @NotBlank(message = "O nome da Cidade que será instalado é Obrigatório!")
        String nomeCidade,

        String status_luminaria
) {
}
