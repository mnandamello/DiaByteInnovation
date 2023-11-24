package br.com.DiaByteInnovation.domain.resource;

import br.com.DiaByteInnovation.domain.entity.Usuario;
import br.com.DiaByteInnovation.domain.service.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource implements Resource<Usuario, Long>{

    @Context
    UriInfo uriInfo;

    UsuarioService service = new UsuarioService();

    @GET
    @Override
    public Response findAll() {
        List<Usuario> all = service.findAll();
        return Response.ok( all ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Usuario usuario = service.findById( id );
        if (Objects.isNull( usuario )) throw new NotFoundException( "Não temos usuario cadastrado com o id: " + id );

        return Response.ok( usuario ).build();
    }

    @Path("/login")
    @POST
    public Response login (Usuario usuario) {

        Usuario usuarioAutenticado = service.login(usuario);
        if (Objects.isNull( usuario )) throw new NotFoundException( "Email ou senha incorreta");

        return Response.ok( usuarioAutenticado ).build();
    }


    @POST
    @Override
    public Response persiste(Usuario usuario) {
        usuario.setIdUsuario( null );
        Usuario user = service.persiste( usuario );

        if (Objects.isNull( user.getIdUsuario() ))
            return Response.notModified( "Não foi possível persistir: " + user).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( user.getIdUsuario() ) ).build();

        return Response.created( uri ).entity( user).build();

    }

}
