package br.com.DiaByteInnovation.domain.resource;

import br.com.DiaByteInnovation.domain.entity.Alimento;
import br.com.DiaByteInnovation.domain.service.AlimentoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/alimento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlimentoResource implements Resource<Alimento, Long>{

    @Context
    UriInfo uriInfo;

    AlimentoService service = new AlimentoService();

    @GET
    @Override
    public Response findAll() {
        List<Alimento> all = service.findAll();
        return Response.ok( all ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Alimento alimento = service.findById( id );

        if (Objects.isNull( alimento )) return Response.status( 404 ).build();

        return Response.ok( alimento ).build();
    }

    @POST
    @Override
    public Response persiste(Alimento alimento) {
        alimento.setId( null );
        Alimento al  = service.persiste( alimento);

        if (Objects.isNull( alimento.getId() ))
            return Response.notModified( "Não foi possível persistir: " + al).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( al.getId() ) ).build();

        return Response.created( uri ).entity( al).build();

    }
}
