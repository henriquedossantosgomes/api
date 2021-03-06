# API Log
Repositório para pequeno projeto de uma api
Projeto Utiliza banco de dados PostgreSQL e conecta a database audoraapi_db
* Tecnologias Utilizadas:
	* Spring Boot
	* Spring Security
	* Hibernate
	* JWT (Json Web Token)
	* PostgreSQL
	


## Api
* Porta configurada 8090 (pode ser alterada editando o arquivo application.properties)

* http://localhost:8090/login

##### Request
	{"username":"admin", "password":"password"}
##### Response
	* No header se encontra o token de autenticação que deve ser enviado nas demais requisições ao sistema
	
	Exemplo:
	Authorization →Token eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5ODI5NzIwNX0.tjwWfxjKNB6Zo-VGKohgDB1OFcDHn_KE873UBfW52YqFCpSFRnUyZJFILQbpfAhF0VzG-zW4d6N3Mfp_woxgrg

* http://localhost:8090/apiaudora/log/save
##### Request
	{
	"produto":"produto",
	"cliente":"cliente",
	"dataHora":"2017-06-15T23:59:00-03:00",
	"categoria":"categoria",
	"registro": {
			"reg1":"1 registro",
			"reg2":"2 registro"
		}
	}

* http://localhost:8090/apiaudora/log/findall
	* Traz todos os resultados
##### Response
	[
		{
		"id": 1,
		"produto":"produto",
		"cliente":"cliente",
		"dataHora":"2017-06-15T23:59:00-03:00",
		"categoria":"categoria",
		"registro": {
				"reg1":"1 registro",
				"reg2":"2 registro"
			}
		},
		{
		"id": 2,
		"produto":"produto2",
		"cliente":"cliente2",
		"dataHora":"2017-06-15T23:59:00-03:00",
		"categoria":"categoria2",
		"registro": {
				"reg1":"3 registro",
				"reg2":"4 registro"
			}
		}
	]

* http://localhost:8090/apiaudora/log/produto/{produto}
	* Traz resultados filtrados por produto
##### Response
	[
		{
		"id": 1,
		"produto":"produto",
		"cliente":"cliente",
		"dataHora":"2017-06-15T23:59:00-03:00",
		"categoria":"categoria",
		"registro": {
				"reg1":"1 registro",
				"reg2":"2 registro"
			}
		}
	]
	
* http://localhost:8090/apiaudora/log/cliente/{cliente}
	* Traz resultados filtrados por cliente
##### Response
	[
		{
		"id": 1,
		"produto":"produto",
		"cliente":"cliente",
		"dataHora":"2017-06-15T23:59:00-03:00",
		"categoria":"categoria",
		"registro": {
				"reg1":"1 registro",
				"reg2":"2 registro"
			}
		}
	]

* http://localhost:8090/apiaudora/log/categoria/{categoria}
	* Traz resultados filtrados por categoria
##### Response
	[
		{
		"id": 1,
		"produto":"produto",
		"cliente":"cliente",
		"dataHora":"2017-06-15T23:59:00-03:00",
		"categoria":"categoria",
		"registro": {
				"reg1":"1 registro",
				"reg2":"2 registro"
			}
		}
	]
	
* http://localhost:8090/apiaudora/log/{id}
	* Traz resultado único pelo id
##### Response
	{
	"id": 1,
	"produto":"produto",
	"cliente":"cliente",
	"dataHora":"2017-06-15T23:59:00-03:00",
	"categoria":"categoria",
	"registro": {
			"reg1":"1 registro",
			"reg2":"2 registro"
		}
	}
	
* http://localhost:8090/apiaudora/log/intervalodata/{datainicial}/{datafinal}
	* Traz resultados filtrados por intervalo de tempo
##### Response
	[
		{
		"id": 1,
		"produto":"produto",
		"cliente":"cliente",
		"dataHora":"2017-06-15T23:59:00-03:00",
		"categoria":"categoria",
		"registro": {
				"reg1":"1 registro",
				"reg2":"2 registro"
			}
		}
	]
* http://localhost:8090/apiaudora/log/find?produto={produto}&cliente={cliente}&categoria={categoria}&dataInicio={datainicial}&dataFim={datafinal}
	* Traz resultados de acordo com parametros passados na url - nenhum valor é obrigatório
	
	### Parâmetros
		* produto 
		* cliente 
		* categoria 
		* dataInicio 
		* dataFim 
##### Response
	[
		{
		"id": 1,
		"produto":"produto",
		"cliente":"cliente",
		"dataHora":"2017-06-15T23:59:00-03:00",
		"categoria":"categoria",
		"registro": {
				"reg1":"1 registro",
				"reg2":"2 registro"
			}
		}
	]


## Response Gerais

#### Retorno OK
	{
	"status": 200,
	"mensagem": "Operacao realizada com sucesso"
	}


#### Retorno Exceção
	{
		"status": 400,
		"mensagem": "Falha na operação"
	}


#### Retornos de erro por autenticação

##### Usuário sem autenticação
	{
	    "timestamp": 1497437061194,
	    "status": 403,
	    "error": "Forbidden",
	    "message": "Access Denied",
	    "path": "/apiaudora/log/findall"
	}

##### Usuário não autorizado
	{
	    "timestamp": 1497441772721,
	    "status": 401,
	    "error": "Unauthorized",
	    "message": "Authentication Failed: Bad credentials",
	    "path": "/login"
	}

##### Usuario não ativo
	{
	    "timestamp": 1497441975034,
	    "status": 401,
	    "error": "Unauthorized",
	    "message": "Authentication Failed: User is disabled",
	    "path": "/login"
	}

##### Token expirado 
	{
	    "timestamp": 1497483472882,
	    "status": 500,
	    "error": "Internal Server Error",
	    "exception": "io.jsonwebtoken.ExpiredJwtException",
	    "message": "JWT expired at 2017-06-14T19:45:04Z. Current time: 2017-06-14T20:37:52Z, a difference of 3168606 milliseconds.  Allowed clock skew: 0 milliseconds.",
	    "path": "/apiaudora/log/find"
	}
