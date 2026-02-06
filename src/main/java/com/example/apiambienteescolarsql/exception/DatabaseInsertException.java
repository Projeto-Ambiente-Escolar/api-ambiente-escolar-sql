package com.example.apiambienteescolarsql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DatabaseInsertException extends RuntimeException {
  public DatabaseInsertException(String message) {
    super(message);
  }
}
