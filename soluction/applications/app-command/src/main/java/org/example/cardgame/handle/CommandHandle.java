package org.example.cardgame.handle;

import org.example.cardgame.domain.command.CrearJuegoCommand;
import org.example.cardgame.usecase.usecase.CrearJuegoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CommandHandle {

    @Bean
    public RouterFunction<ServerResponse> crearJuego(CrearJuegoUseCase usecase){
        return route(
                POST("/crearjuego").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.apply(request.bodyToMono(CrearJuegoCommand.class))
                        .then(ServerResponse.ok().build())
        );

    }

}
