package br.com.efrancodelima.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/mensagem")
@Tag(
    name = "API da aplicação",
    description = "API contendo endpoints para interagir com o Kafka na qualidade de produtor."
)
public interface KafkaProducerContract {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(
        summary = "Enviar mensagem",
        description = "Produz uma mensagem para o tópico 'meu-topico'."
    )
    @APIResponse(
        responseCode = "200",
        description = "Sucesso",
        content = @Content(
            mediaType = "text/plain",
            schema = @Schema(type = SchemaType.STRING)
        )
    )
    public Uni<Response> enviar(String mensagem);
}
