package br.com.Iluminacao.config.security;

import br.com.Iluminacao.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("minha.Chave.Secreta")
    private String palavraSecreta;

    // GERAR TOKEN
    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);
            String token = JWT
                    .create()
                    .withIssuer("Iluminacao")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException erro){
            throw new RuntimeException("Não foi possivel criar o Token");
        }
    }


    //VALIDAR TOKEN
    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);
            return JWT
                    .require(algorithm)
                    .withIssuer("Iluminacao")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException erro){
            return "";
        }
    }


    public Instant gerarDataDeExpiracao(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));

    }

}
