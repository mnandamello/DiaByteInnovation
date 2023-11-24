package br.com.DiaByteInnovation.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario  {

    private Long idUsuario;

    private String email;

    private String senha;

    private Paciente paciente;

}
