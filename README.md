# gerenciamento-pedidos-springframework
Aplicação para gerenciar pedidos

Para rodar a aplicação use o comando do maven no diretório raiz do projeto: mvn spring-boot:run e execute teste api rest na url: http://localhost:8080/swagger-ui/index.html

Se for executar no Docker, primeiro construa o executavel .jar na pasta target.

mvn clean package

depois use o comando do dockercompose: docker-compose -f docker-compose.yml up

Talvez o baixe dependicias, quando executado na primeira vez.

quando for para a aplicação, rode o comando: docker-compose -f docker-compose.yml down


