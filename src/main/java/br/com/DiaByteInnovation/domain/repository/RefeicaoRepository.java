package br.com.DiaByteInnovation.domain.repository;

import br.com.DiaByteInnovation.domain.entity.Paciente;
import br.com.DiaByteInnovation.domain.entity.Refeicao;

import br.com.DiaByteInnovation.domain.service.PacienteService;

import br.com.DiaByteInnovation.infra.ConnectionFactory;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RefeicaoRepository implements Repository<Refeicao, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<RefeicaoRepository> instance = new AtomicReference<>();

    private RefeicaoRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static RefeicaoRepository build() {
        instance.compareAndSet( null, new RefeicaoRepository() );
        return instance.get();
    }

    @Override
    public List<Refeicao> findAll() {
        List<Refeicao> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        PacienteService pacienteService = new PacienteService();
        try {
            String sql = "SELECT *  FROM tb_refeicao";

            st = con.createStatement();
            rs = st.executeQuery( sql );

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    long id_refeicao = rs.getLong( "id_refeicao" );
                    float totalCarboidrato = rs.getFloat( "qt_total_carbo" );
                    Integer quantidadeInsulina = rs.getInt( "qt_total_insulina" );
                    long id_paciente = rs.getLong("id_paciente");
                    Paciente paciente = pacienteService.findById(id_paciente);

                    list.add( new Refeicao( id_refeicao ,totalCarboidrato, quantidadeInsulina, paciente) );
                }
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, st, con );
        }
        return list;
    }

    @Override
    public Refeicao findById(Long id) {
        Refeicao rf = null;
        var sql = "SELECT *  FROM tb_refeicao  where id_refeicao = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        PacienteService pacienteService = new PacienteService();

        try {
            ps = con.prepareStatement( sql );
            ps.setLong( 1, id );
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    float totalCarboidrato = rs.getFloat( "qt_total_carbo" );
                    Integer quantidadeInsulina = rs.getInt( "qt_total_insulina" );
                    long id_paciente = rs.getLong("id_paciente");

                    Paciente paciente = null;
                    paciente = pacienteService.findById(id_paciente);


                    rf = new Refeicao( id, totalCarboidrato, quantidadeInsulina, paciente);
                }
            } else {
                System.out.println( "Dados não encontrados com o id: " + id );
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, ps, con );
        }
        return rf;
    }

    @Override
    public Refeicao persiste(Refeicao rf) {
        var sql = "INSERT INTO tb_refeicao  (id_refeicao , qt_total_carbo, qt_total_insulina, id_paciente) VALUES (seq_refeicao.nextval,?, ?, ?)";


        Connection con = factory.getConnection();
        PreparedStatement ps = null;


        try {

            ps = con.prepareStatement( sql, new String[]{"id_refeicao"} );

            // seta os valores dos parâmetros
            ps.setFloat( 1, rf.getTotalCarboidrato() );
            ps.setInt(2, rf.getQuantidadeInsulina());
            ps.setLong(3, rf.getPaciente().getId());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong( 1 );
                rf.setId_refeicao( id );
            }

        } catch (SQLException e) {
            System.err.println( "Não foi possível inserir os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( null, ps, con );
        }
        return rf;
    }


    public List<Refeicao> findByPaciente(Long id) {
        List<Refeicao> refeicoes = new ArrayList<>();

        var sql = "SELECT *  FROM tb_refeicao  where id_paciente = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        PacienteService pacienteService = new PacienteService();

        try {
            ps = con.prepareStatement( sql );
            ps.setLong( 1, id );
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    long id_refeicao = rs.getLong( "id_refeicao" );
                    float totalCarboidrato = rs.getFloat( "qt_total_carbo" );
                    Integer quantidadeInsulina = rs.getInt( "qt_total_insulina" );
                    long id_paciente = rs.getLong("id_paciente");

                    Paciente paciente = null;
                    paciente = pacienteService.findById(id_paciente);


                    refeicoes.add(new Refeicao( id_refeicao, totalCarboidrato, quantidadeInsulina, paciente));
                }
            } else {
                System.out.println( "Refeições não encontradas para o paciente de id " + id );
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, ps, con );
        }
        return refeicoes;
    }

}
