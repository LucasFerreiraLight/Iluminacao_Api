package br.com.Iluminacao.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro){
            Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fields = erro.getBindingResult().getFieldErrors();

        for (FieldError field : fields){
            errorMap.put(field.getField(), field.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> manusearIntegridadeDosDados(MethodArgumentNotValidException erro){
        Map<String, String> mapaErro = new HashMap<>();
        mapaErro.put("erro", "Usuário já cadastrado!");
        return mapaErro;
    }


}
