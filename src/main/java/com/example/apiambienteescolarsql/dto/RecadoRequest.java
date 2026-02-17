package com.example.apiambienteescolarsql.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecadoRequest {
    @NotNull
    private Long professor;

    @NotNull
    private Long aluno;

    @NotNull
    @Size(max = 500, message = "A mensagem deve ter no máximo 500 caracteres")
    private String mensagem;
}
