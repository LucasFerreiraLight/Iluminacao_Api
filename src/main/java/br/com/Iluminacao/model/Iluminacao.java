package br.com.Iluminacao.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TBL_ILUMINACAO")
public class Iluminacao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ILUMINACAO"
    )
    @SequenceGenerator(
            name = "SEQ_ILUMINACAO",
            sequenceName = "SEQ_ILUMINACAO",
            allocationSize = 1
    )
    @Column(name = "ILUMINACAO_ID")
    private Long iluminacaoId;

    @Column(name = "NOME_RUA")
    private String nomeRua;

    @Column(name = "NOME_CIDADE")
    private String nomeCidade;

    @Column(name = "STATUS_ILUMINACAO")
    private String statusIluminacao;
}
