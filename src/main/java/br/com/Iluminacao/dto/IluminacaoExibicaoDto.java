package br.com.Iluminacao.dto;

import br.com.Iluminacao.model.Iluminacao;

public record IluminacaoExibicaoDto(
         Long id,

         String nomeRua,
         String nomeCidade,
         String status_iluminacao
) {

    public IluminacaoExibicaoDto(Iluminacao iluminacaoSalva) {
        this(
                iluminacaoSalva.getIluminacaoId(),
                iluminacaoSalva.getNomeRua(),
                iluminacaoSalva.getNomeCidade(),
                iluminacaoSalva.getStatusIluminacao()
        );

    }
}
