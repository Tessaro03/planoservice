package com.denteconvenio.planoservice.infra.validation;


public class ValidacaoException extends RuntimeException{

    public ValidacaoException(String mensagem){
        super(mensagem);
    }
}