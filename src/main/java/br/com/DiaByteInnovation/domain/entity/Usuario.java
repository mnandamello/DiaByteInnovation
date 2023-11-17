package br.com.DiaByteInnovation.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario  {

    private Long id;

    private String email;

    private String senha;

    private Paciente paciente; //como que vamos chamar o paciente aqui sendo que só vamos pegar o paciente depois que fizermos o login e o cadastro?

    /* METODOS
    * CREATE (POST)
    * FindByEmail (GET)
    * FindBySenha (GET)
    * */

}
