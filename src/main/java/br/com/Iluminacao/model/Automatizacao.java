package br.com.Iluminacao.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Getter
@Setter
@Component
public class Automatizacao {


    public static String verificarStatusIluminacao() {
        LocalTime agora = LocalTime.now();

        // Definir o status da iluminação com base no horário
        if (agora.isAfter(LocalTime.of(17, 0)) || agora.isBefore(LocalTime.of(6, 0))) {
            return "Ligado";
        } else {
            return "Desligado";
        }
    }

}
