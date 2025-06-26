package br.com.evolua_fit.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
@Data
public class AlunoDto {

    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Formato de email inválido.")
    private String email;

    @Past(message = "A data de nascimento deve ser no passado.")
    private LocalDate dataNascimento;

    private String objetivo;
}
