package com.example.apiambienteescolarsql.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRequest {
    @NotNull
    private Long turma;

    @NotNull
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotNull
    @Size(max = 50, message = "O e-mail deve ter no máximo 50 caracteres")
    private String email;

    @NotNull
    @Size(max = 50, message = "A senha deve ter no máximo 50 caracteres")
    private String senha;

    @Size(max = 1, message = "O status deve ser apenas um caractere, (0, 1 ou 2)")
    @NotNull
    private String status;

    private String foto;
}
