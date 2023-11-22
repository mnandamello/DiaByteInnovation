package br.com.DiaByteInnovation.domain.resource;

import br.com.DiaByteInnovation.domain.entity.Refeicao;
import br.com.DiaByteInnovation.domain.service.RefeicaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/refeicao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RefeicaoResource  implements Resource<Refeicao, Long>{

    @Context
    UriInfo uriInfo;

    RefeicaoService service = new RefeicaoService();

    @GET
    @Override
    public Response findAll() {
        List<Refeicao> all = service.findAll();
        return Response.ok( all ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Refeicao refeicao = service.findById( id );

        if (Objects.isNull( refeicao )) throw new NotFoundException( "Não temos uam refeição com o id: " + id );

        return Response.ok( refeicao ).build();
    }

    @Path("/historico/{id}")
    @GET
    public Response findByPaciente(@PathParam("id")Long id) {
        List<Refeicao> all = service.findByPaciente(id);
        return Response.ok( all ).build();
    }

    @POST
    @Override
    public Response persiste(Refeicao refeicao) {
        refeicao.setId_refeicao( null );
        Refeicao rf = service.persiste( refeicao );

        if (Objects.isNull( rf.getId_refeicao() ))
            return Response.notModified( "Não foi possível persistir: " + rf).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( rf.getId_refeicao() ) ).build();

        return Response.created( uri ).entity( rf).build();

    }
}
