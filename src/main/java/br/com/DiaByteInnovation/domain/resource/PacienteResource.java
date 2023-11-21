package br.com.DiaByteInnovation.domain.resource;

import br.com.DiaByteInnovation.domain.entity.Paciente;
import br.com.DiaByteInnovation.domain.service.PacienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/paciente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource implements Resource<Paciente, Long>{

    @Context
    UriInfo uriInfo;

    PacienteService service = new PacienteService();

    @GET
    @Override
    public Response findAll() {
        List<Paciente> all = service.findAll();
        return Response.ok( all ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Paciente paciente = service.findById( id );

        if (Objects.isNull( paciente )) throw new NotFoundException( "Não temos artista cadastrado com o id: " + id );

        return Response.ok( paciente ).build();
    }

    @POST
    @Override
    public Response persiste(Paciente paciente) {
        paciente.setId_paciente( null );
        Paciente pc = service.persiste( paciente );

        if (Objects.isNull( pc.getId_paciente() ))
            return Response.notModified( "Não foi possível persistir: " + pc).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( pc.getId_paciente() ) ).build();

        return Response.created( uri ).entity( pc).build();

    }


}
