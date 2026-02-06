package com.example.apiambienteescolarsql.dto.exceptiondto;

import java.util.Map;

public class BadRequestDTO {
    int status;
    String error;
    String messagem;
    Map<String, String>errors;

    public BadRequestDTO() {
    }

    public BadRequestDTO(int status, String error, String messagem, Map<String, String> errors) {
        this.status = status;
        this.error = error;
        this.messagem = messagem;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
