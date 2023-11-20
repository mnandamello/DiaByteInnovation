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

    /* METODOS
    * persiste (POST) - OK
    * findById - OK
    * */

}
