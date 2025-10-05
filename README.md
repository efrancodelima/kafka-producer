# Propósito

Esse projeto foi desenvolvido para estudo pessoal sobre o Kafka, um sistema de mensageria que permite publicar, armazenar e consumir dados em tempo real.

Esse projeto é composto pelo serviço do Kafka, uma aplicação para produzir mensagens e outra para consumir, conforme os links abaixo:

 - aplicação produtora e serviço kafka: <https://github.com/efrancodelima/kafka-producer>
 - aplicação consumidora: <https://github.com/efrancodelima/kafka-consumer>


## Como executar

Clone o projeto.

Suba o serviço Kafka: `docker compose up --build`

Roda a aplicação kafka-producer: `./mvnw quarkus:dev`

Acesse o Swagger da aplicação e envie algumas mensagens para testar.

<http://localhost:8080/q/swagger-ui/>

Nesse caso, o endpoint irá aguardar a confirmação do Kafka de que a mensagem foi recebida antes de exibir a resposta no Swagger. Foi feito dessa forma porque facilita a visualização do que está acontecendo para fins de estudo, mas a decisão sobre aguardar ou não a confirmação do Kafka depende do cenário onde ele será utilizado. Se confiabilidade é importante, deve-se tomar muito cuidado para evitar erros silenciosos durante a produção de mensagens.

Siga as instruções do projeto kafka-consumer, conforme o link citado no início deste documento.

## Outros comandos úteis

Parar o serviço do Kafka: `docker compose down -v`

Visulizar o log do Kafka: `docker logs kafka`

Retornar a lista de tópicos do Kafka:
```
docker exec -it kafka kafka-topics --bootstrap-server localhost:9092 --list
```

Listar as mensagens recebidas pelo Kafka para o tópico meu-topico:
```
docker exec -it kafka kafka-console-consumer \
--bootstrap-server localhost:9092 \
--topic meu-topico --from-beginning
```
