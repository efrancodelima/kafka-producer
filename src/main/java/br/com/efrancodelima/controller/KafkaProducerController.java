package br.com.efrancodelima.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class KafkaProducerController implements KafkaProducerContract {

    @Inject
    @Channel("meu-topico")
    private Emitter<String> emitter;

    @Override
    public Uni<Response> enviar(String mensagem) {
        
        String msg = adicionarDataHora(mensagem);

        return Uni.createFrom().completionStage(() -> emitter.send(msg))
            .map(ignored -> getSuccessResponse())
            .onFailure().recoverWithItem(erro -> getFailResponse(erro));
    }

    private String adicionarDataHora(String mensagem) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHora = LocalDateTime.now().format(formatter);
        return dataHora + " - " + mensagem;
    }

    private Response getSuccessResponse() {
        return Response.ok(
            "Mensagem enviada com sucesso!", MediaType.TEXT_PLAIN
        ).build();
    }

    private Response getFailResponse(Throwable error) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("Erro ao enviar mensagem: " + error.getMessage())
            .build();
    }
}