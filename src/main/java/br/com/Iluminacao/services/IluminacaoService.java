package br.com.Iluminacao.services;

import br.com.Iluminacao.dto.IluminacaoCadastroDto;
import br.com.Iluminacao.dto.IluminacaoExibicaoDto;
import br.com.Iluminacao.model.Automatizacao;
import br.com.Iluminacao.model.Iluminacao;
import br.com.Iluminacao.repository.IluminacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class IluminacaoService {

    @Autowired
    IluminacaoRepository iluminacaoRepository;

    @Autowired
    Automatizacao automatizacao;


    // SALVAR / INSTALAR ILUMINACAO
    public IluminacaoExibicaoDto salvar(IluminacaoCadastroDto iluminacaoDto) {
        Iluminacao iluminacao = new Iluminacao();
        BeanUtils.copyProperties(iluminacaoDto, iluminacao);

        // Obter o horário atual
        LocalTime agora = LocalTime.now();

        // Definir o status da iluminação com base no horário
        String statusIluminacao;
        if (agora.isAfter(LocalTime.of(17, 0)) || agora.isBefore(LocalTime.of(6, 0))) {
            statusIluminacao = "Ligado";
        } else {
            statusIluminacao = "Desligado";
        }

        // Atribuir o status iluminação ao objeto iluminacao
        iluminacao.setStatusIluminacao(statusIluminacao);

        Iluminacao iluminacaoSalva = iluminacaoRepository.save(iluminacao);
        return new IluminacaoExibicaoDto(iluminacaoSalva);
    }


    // LISTAR TODAS AS ILUMINACOES

    public List<IluminacaoExibicaoDto> listarTodasAsIluminarias() {
        return iluminacaoRepository
                .findAll()
                .stream()
                .map(IluminacaoExibicaoDto::new)
                .toList();
    }

    // DELETAR ILUMINACAO
    public void excluir(Long id) {
        Optional<Iluminacao> iluminacaoOptional = iluminacaoRepository.findById(id);

        if (iluminacaoOptional.isPresent()) {
            iluminacaoRepository.delete(iluminacaoOptional.get());
        } else {
            throw new RuntimeException("ID da Iluminação solicitada NÃO encontrado!");
        }
    }

    // UPDATE
    public Iluminacao atualizar(Iluminacao iluminacao) {
        Optional<Iluminacao> iluminacaoOptional = iluminacaoRepository.findById(iluminacao.getIluminacaoId());

        if (iluminacaoOptional.isPresent()) {
            // Atualizar o status da iluminação
            String statusIluminacao = Automatizacao.verificarStatusIluminacao();
            iluminacao.setStatusIluminacao(statusIluminacao);

            return iluminacaoRepository.save(iluminacao);
        } else {
            throw new RuntimeException("ID da Iluminação solicitado não encontrado!");
        }
    }

    public Iluminacao desligarIluminacaoPorId(Long id) {
        Optional<Iluminacao> iluminacaoOptional = iluminacaoRepository.findById(id);
        if (iluminacaoOptional.isPresent()) {
            Iluminacao iluminacao = iluminacaoOptional.get();
            iluminacao.setStatusIluminacao("Desligado");
            return iluminacaoRepository.save(iluminacao);
        } else {
            throw new RuntimeException("ID da Iluminação solicitado não encontrado!");
        }
    }


    public Iluminacao findById(Long id) {
        Optional<Iluminacao> iluminacaoOptional = iluminacaoRepository.findById(id);

        if (iluminacaoOptional.isPresent()) {
            iluminacaoRepository.findById(id);
        } else {
            throw new RuntimeException("ID da Iluminação solicitada NÃO encontrado!");
        }
        return iluminacaoOptional.get();
    }

}

