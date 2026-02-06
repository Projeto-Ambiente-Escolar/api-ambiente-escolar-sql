package com.example.apiambienteescolarsql.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotasRequest {
    @NotNull
    private Long aluno;

    @Size(max = 100, message = "A observação deve ter no máximo 50 caracteres")
    private String observacao;

    @NotNull
    private Long professor;

    @NotNull
    private Long nota1;

    @NotNull
    private Long nota2;
}
