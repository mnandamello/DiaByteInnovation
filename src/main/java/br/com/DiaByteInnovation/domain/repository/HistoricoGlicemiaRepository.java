package br.com.DiaByteInnovation.domain.repository;

import br.com.DiaByteInnovation.domain.entity.HistoricoGlicemia;
import br.com.DiaByteInnovation.domain.entity.Paciente;
import br.com.DiaByteInnovation.domain.service.PacienteService;
import br.com.DiaByteInnovation.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class HistoricoGlicemiaRepository implements Repository<HistoricoGlicemia, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<HistoricoGlicemiaRepository> instance = new AtomicReference<>();

    private HistoricoGlicemiaRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static HistoricoGlicemiaRepository build() {
        instance.compareAndSet( null, new HistoricoGlicemiaRepository() );
        return instance.get();
    }

    @Override
    public List<HistoricoGlicemia> findAll() {
        List<HistoricoGlicemia> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        PacienteService pacienteService = new PacienteService();
        try {
            String sql = "SELECT *  FROM tb_historico_glicemia";

            st = con.createStatement();
            rs = st.executeQuery( sql );
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    long id_glicemia = rs.getLong( "id_historico_glicemia" );
                    Integer valorDestro = rs.getInt( "vl_destro" );
                    Paciente paciente = null;
                    long id_paciente = rs.getLong("id_paciente");
                    paciente = pacienteService.findById(id_paciente);

                    list.add( new HistoricoGlicemia( id_glicemia, valorDestro, paciente) );
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
    public HistoricoGlicemia findById(Long id) {
        HistoricoGlicemia hc = null;
        var sql = "SELECT *  FROM tb_historico_glicemia  where id_historico_glicemia = ?";
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
                    Integer valorDestro = rs.getInt( "vl_destro" );
                    long id_paciente = rs.getLong("id_paciente");

                    Paciente paciente = null;
                    paciente = pacienteService.findById(id_paciente);

                    hc = new HistoricoGlicemia( id, valorDestro, paciente);
                }
            } else {
                System.out.println( "Dados não encontrados com o id: " + id );
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, ps, con );
        }
        return hc;
    }

    @Override
    public HistoricoGlicemia persiste(HistoricoGlicemia hc) {
        var sql = "INSERT INTO tb_historico_glicemia  (id_historico_glicemia , vl_destro, id_paciente) VALUES (seq_historico_glicemia.nextval,?, ?)";


        Connection con = factory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement( sql, new String[]{"id_historico_glicemia"} );

            // seta os valores dos parâmetros
            ps.setInt( 1, hc.getValorDestro() );
            ps.setLong(2, hc.getPaciente().getId());


            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong( 1 );
                hc.setId_glicemia( id );
            }

        } catch (SQLException e) {
            System.err.println( "Não foi possível inserir os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( null, ps, con );
        }
        return hc;
    }


}
