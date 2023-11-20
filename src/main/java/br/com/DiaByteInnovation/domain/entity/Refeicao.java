package br.com.DiaByteInnovation.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Refeicao {

    private Long id;

    private float totalCarboidrato;

    private Integer quantidadeInsulina; //ver se é int mesmo

    private Paciente paciente;

    /* METODOS
    * persiste (POST)
    * findAll (GET) //trazer o historico de refeições
    * findById //MAS O id vai ter q ser o do paciente para pegar as refeiçã
    * */

    //no front pegariamos o JSON que retorna com o findById e ai acessamos as informações que queremos para fazer a conta.

}
