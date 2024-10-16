package br.com.Iluminacao.controller;

import br.com.Iluminacao.dto.IluminacaoCadastroDto;
import br.com.Iluminacao.dto.IluminacaoExibicaoDto;
import br.com.Iluminacao.model.Iluminacao;
import br.com.Iluminacao.services.IluminacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IluminacaoController {

    @Autowired
    private IluminacaoService iluminacaoService;

    //POST
    //save (CRIAR ILUMINACAO)
    @PostMapping("iluminacao/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public IluminacaoExibicaoDto gravar(@RequestBody IluminacaoCadastroDto iluminacaoCadastroDto) {
        return iluminacaoService.salvar(iluminacaoCadastroDto);
    }

    //GET
    //buscar todas as Iluminarias instaladas
    @GetMapping("iluminacao/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<IluminacaoExibicaoDto> listarTodasAsIluminariasInstaladas() {
        return iluminacaoService.listarTodasAsIluminarias();
    }

    // DELETE
    //Deletar Iluminacao
    @DeleteMapping("iluminacao/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Por padrão já é esse, mas você pode deixar explicito igual está ai
    public void excluir(@PathVariable Long id) {
        iluminacaoService.excluir(id);

    }

    // Update
    // Atualizar uma Iluminacao
    @PutMapping("iluminacao/update")
    @ResponseStatus(HttpStatus.OK)
    public Iluminacao atualizar(@RequestBody Iluminacao iluminacao) {
        return iluminacaoService.atualizar(iluminacao);
    }

    // Desligar Iluminacao pelo ID
    @PutMapping("/desligar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Iluminacao> desligarIluminacaoPorId(@PathVariable Long id) {
        Iluminacao iluminacaoDesligada = iluminacaoService.desligarIluminacaoPorId(id);
         return ResponseEntity.ok(iluminacaoDesligada);
    }


    @GetMapping("iluminacao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Iluminacao iluminacao(@PathVariable Long id) {
        return iluminacaoService.findById(id);
    }

}
