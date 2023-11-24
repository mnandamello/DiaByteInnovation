package br.com.DiaByteInnovation.domain.resource;

import br.com.DiaByteInnovation.domain.entity.HistoricoGlicemia;
import br.com.DiaByteInnovation.domain.service.HistoricoGlicemiaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/historico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricoGlicemiaResource implements Resource<HistoricoGlicemia, Long>{

    @Context
    UriInfo uriInfo;

    HistoricoGlicemiaService service = new HistoricoGlicemiaService();

    @GET
    @Override
    public Response findAll() {
        List<HistoricoGlicemia> all = service.findAll();
        return Response.ok( all ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        HistoricoGlicemia historicoGlicemia = service.findById( id );

        if (Objects.isNull( historicoGlicemia )) throw new NotFoundException( "Não temos artista cadastrado com o id: " + id );

        return Response.ok( historicoGlicemia ).build();
    }

    @POST
    @Override
    public Response persiste(HistoricoGlicemia historicoGlicemia) {
        historicoGlicemia.setIdGlicemia( null );
        HistoricoGlicemia hc = service.persiste( historicoGlicemia );

        if (Objects.isNull( hc.getIdGlicemia() ))
            return Response.notModified( "Não foi possível persistir: " + hc).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( hc.getIdGlicemia() ) ).build();

        return Response.created( uri ).entity( hc).build();

    }
}
