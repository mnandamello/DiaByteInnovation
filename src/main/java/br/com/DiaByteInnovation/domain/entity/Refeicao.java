package br.com.DiaByteInnovation.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Refeicao {

    private Long id;

    private Integer quantidadeInsulina;

    private float totalCarboidrato;

    /* METODOS
    * CREATE (POST)
    * findAll (GET) //trazer o historico de refeições
    * */

    //no front pegariamos o JSON que retorna com o findById e ai acessamos as informações que queremos para fazer a conta.

}
