package br.com.DiaByteInnovation.domain.repository;

import br.com.DiaByteInnovation.domain.entity.Paciente;
import br.com.DiaByteInnovation.domain.entity.Usuario;
import br.com.DiaByteInnovation.domain.service.UsuarioService;
import br.com.DiaByteInnovation.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PacienteRepository implements Repository<Paciente, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<PacienteRepository> instance = new AtomicReference<>();

    private PacienteRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static PacienteRepository build() {
        instance.compareAndSet( null, new PacienteRepository() );
        return instance.get();
    }

    @Override
    public List<Paciente> findAll() {
        List<Paciente> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        UsuarioService usuarioService = new UsuarioService();
        try {
            String sql = "SELECT *  FROM tb_paciente";

            st = con.createStatement();
            rs = st.executeQuery( sql );
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    long id = rs.getLong( "id_paciente " );
                    String nomeCompleto = rs.getString( "senha" );
                    LocalDate dtNascimento = rs.getDate( "dt_nascimento" ).toLocalDate();
                    float relacaoInsulina = rs.getFloat( "email" );
                    Integer valorMaxGlicemia = rs.getInt( "senha" );
                    Integer valorMinGlicemia = rs.getInt( "senha" );
                    long id_usuario = rs.getLong("id_usuario");
                    Usuario usuario = null;
                    usuario = usuarioService.findById(id_usuario);

                    list.add( new Paciente( id, nomeCompleto,dtNascimento, relacaoInsulina,  valorMaxGlicemia, valorMinGlicemia, usuario) );
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
    public Paciente findById(Long id) {
        Paciente pc = null;
        var sql = "SELECT *  FROM tb_paciente  where id_paciente = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        UsuarioService usuarioService = new UsuarioService();

        try {
            ps = con.prepareStatement( sql );
            ps.setLong( 1, id );
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String nomeCompleto = rs.getString( "nm_completo " );
                    LocalDate dtNascimento = rs.getDate( "dt_nascimento" ).toLocalDate();
                    float relacaoInsulina = rs.getFloat( "relacao_insulina_carboidrato" );
                    Integer valorMaxGlicemia = rs.getInt("max_glicemia ");
                    Integer valorMinGlicemia = rs.getInt("min_glicemia");
                    long id_usuario = rs.getLong("id_usuario");
                    Usuario usuario = null;


                    usuario = usuarioService.findById( id_usuario );

                    pc = new Paciente( id, nomeCompleto, dtNascimento, relacaoInsulina, valorMaxGlicemia, valorMinGlicemia, usuario);
                }
            } else {
                System.out.println( "Dados não encontrados com o id: " + id );
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, ps, con );
        }
        return pc;
    }

    @Override
    public Paciente persiste(Paciente pc) {
        var sql = "INSERT INTO tb_paciente  (id_paciente , nm_completo, dt_nascimento, relacao_insulina_carboidrato, max_glicemia, min_glicemia, id_usuario) VALUES (seq_paciente.nextval,?, ?, ?, ?, ?, ?)";


        Connection con = factory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement( sql, new String[]{"id_paciente"} );

            // seta os valores dos parâmetros
            ps.setString( 1, pc.getNomeCompleto() );
            ps.setDate(2, Date.valueOf(pc.getDtNascimento()));
            ps.setFloat(3, pc.getRelacaoInsulina());
            ps.setInt(4, pc.getValorMaxGlicemia());
            ps.setFloat(5, pc.getValorMinGlicemia());
            ps.setLong(6, pc.getUsuario().getId());


            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong( 1 );
                pc.setId( id );
            }

        } catch (SQLException e) {
            System.err.println( "Não foi possível inserir os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( null, ps, con );
        }
        return pc;
    }
}
