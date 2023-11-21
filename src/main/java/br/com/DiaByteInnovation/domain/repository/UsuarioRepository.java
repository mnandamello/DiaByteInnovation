package br.com.DiaByteInnovation.domain.repository;

import br.com.DiaByteInnovation.infra.ConnectionFactory;
import br.com.DiaByteInnovation.domain.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class UsuarioRepository implements Repository<Usuario, Long>{


    private ConnectionFactory factory;

    private static final AtomicReference<UsuarioRepository> instance = new AtomicReference<>();

    private UsuarioRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static UsuarioRepository build() {
        instance.compareAndSet( null, new UsuarioRepository() );
        return instance.get();
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        try {
            String sql = "SELECT *  FROM tb_usuario";

            st = con.createStatement();
            rs = st.executeQuery( sql );
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    long id = rs.getLong( "id_usuario" );
                    String email = rs.getString( "email" );
                    String senha = rs.getString( "senha" );

                    list.add( new Usuario( id, email, senha ) );
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
    public Usuario findById(Long id) {
        Usuario user = null;
        var sql = "SELECT *  FROM tb_usuario  where id_usuario = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement( sql );
            ps.setLong( 1, id );
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String email = rs.getString( "email" );
                    String senha = rs.getString( "senha" );

                    user = new Usuario( id, email, senha );
                }
            } else {
                System.out.println( "Dados não encontrados com o id: " + id );
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, ps, con );
        }
        return user;
    }

    @Override
    public Usuario persiste(Usuario us) {
        System.out.println("entrou 1");
        var sql = "INSERT INTO tb_usuario  (id_usuario, email, senha) VALUES (seq_usuario.nextval,?, ? )";


        Connection con = factory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement( sql, new String[]{"id_usuario"} );

            // seta os valores dos parâmetros
            ps.setString( 1, us.getEmail() );
            ps.setString( 2, us.getSenha() );


            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong( 1 );
                us.setId_usuario( id );
            }

        } catch (SQLException e) {
            System.err.println( "Não foi possível inserir os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( null, ps, con );
        }
        return us;
    }


}
