# simeo_dna
Repositório de projeto para identificar se um código dna é de simeo ou humano  
Mantenedor: Leandro Vilson Battisti  
e-mail: asengardeons@hotmail.com  

## Tecnologias utilizadas
- Spring 2.4
- H2
- Maven
- CI/CD com Githubs Actions + Heroku


##Endpoint fornecidos pela aplicação

### /simian

Recebe um post com a seguinte estrutura

Metodo: POST  
Payload:
```json 
{
"dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
```


### /stats

Recebe um get e retorna a seguinte estrutura
```json
{"count_mutant_dna": 40, "count_human_dna": 100: "ratio": 0.4}
```

## Servidores para testes em ambiente nuvem
ATENÇÃO: Os dois ambiente por rodarem no Heroku precisam ser "aquecidos" para primeiro uso. O tempo para a aplicação subir para primeiro uso é de cerca de 30 segundos.

Servidor DEV(Heroku): https://simiean-dna-dev.herokuapp.com/

Servidor MAIN(Heroku): https://simian-dna-asengardeon.herokuapp.com/


## Compilando e executando a aplicação localmente
Para uso local pode ser utilizado comando maven de execução da aplicação para criar o JAR
```shell
mvnw clean package
```

e em seguida utilizar o comando para subir a aplicação:

```shell
java -jar target\simeos-0.0.1-SNAPSHOT.jar
```

## Executando Via IDE
Após abrir o projeto em sua IDE de preferencia, instale as dependências do POM.xml e  execute a classe "SimeosApplication"
