package br.com.DiaByteInnovation.domain.repository;

import br.com.DiaByteInnovation.domain.entity.Alimento;
import br.com.DiaByteInnovation.domain.entity.Usuario;
import br.com.DiaByteInnovation.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AlimentoRepository implements Repository<Alimento, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<AlimentoRepository> instance = new AtomicReference<>();

    private AlimentoRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static AlimentoRepository build() {
        instance.compareAndSet( null, new AlimentoRepository() );
        return instance.get();
    }

    @Override
    public List<Alimento> findAll() {
        List<Alimento> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        try {
            String sql = "SELECT *  FROM tb_alimento";

            st = con.createStatement();
            rs = st.executeQuery( sql );
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    long id = rs.getLong( "id_alimento" );
                    String nome = rs.getString( "tipo_alimento" );
                    float quantCarboGrama = rs.getFloat( "qt_carbo_grama" );

                    list.add( new Alimento( id, nome, quantCarboGrama ) );
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
    public Alimento findById(Long id) {
        Alimento alimento = null;
        var sql = "SELECT *  FROM tb_alimento  where id_alimento = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement( sql );
            ps.setLong( 1, id );
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String nome = rs.getString( "tipo_alimento" );
                    float quantCarboGrama = rs.getFloat( "qt_carbo_grama" );

                    alimento = new Alimento( id, nome, quantCarboGrama );
                }
            } else {
                System.out.println( "Dados não encontrados com o id: " + id );
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, ps, con );
        }
        return alimento;
    }

    @Override
    public Alimento persiste(Alimento al) {
        var sql = "INSERT INTO tb_alimento  (id_alimento, tipo_alimento, qt_carbo_grama) VALUES (seq_alimento.nextval,?, ? )";


        Connection con = factory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement( sql, new String[]{"id_alimento"} );

            // seta os valores dos parâmetros
            ps.setString( 1, al.getNome() );
            ps.setFloat( 2, al.getQuantCarboGrama() );


            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong( 1 );
                al.setId( id );
            }

        } catch (SQLException e) {
            System.err.println( "Não foi possível inserir os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( null, ps, con );
        }
        return al;
    }
}
