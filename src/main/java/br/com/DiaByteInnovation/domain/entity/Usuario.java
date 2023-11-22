package br.com.DiaByteInnovation.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario  {

    private Long id_usuario;

    private String email;

    private String senha;

    private Paciente paciente;

    /* METODOS
    * persiste (POST) - OK
    * findById - OK
    * */

}
