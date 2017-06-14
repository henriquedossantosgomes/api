# API Log
Repositório para pequeno projeto api


## Api

* http://localhost:8090/login

### Request
	{"username":"admin", "password":"password"}

* http://localhost:8090/apiaudora/log/save
### Request
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

* http://localhost:8090/apiaudora/log/produto/{produto}
	* Traz resultados filtrados por produto
	
* http://localhost:8090/apiaudora/log/cliente/{cliente}
	* Traz resultados filtrados por cliente
	
* http://localhost:8090/apiaudora/log/categoria/{categoria}
	* Traz resultados filtrados por categoria
	
* http://localhost:8090/apiaudora/log/{id}
	* Traz resultado único pelo id

* http://localhost:8090/apiaudora/log/intervalodata/{datainicial}/{datafinal}
	* Traz resultados filtrados por intervalo de tempo

* http://localhost:8090/apiaudora/log/find?produto={produto}&cliente={cliente}&categoria={categoria}&dataInicio={datainicial}&dataFim={datafinal}
	* Traz resultados de acordo com parametros passados na url - nenhum valor é obrigatório
	
	### Parâmetros
		* produto 
		* cliente 
		* categoria 
		* dataInicio 
		* dataFim 

## Response
### Usuário sem autenticação
{
    "timestamp": 1497437061194,
    "status": 403,
    "error": "Forbidden",
    "message": "Access Denied",
    "path": "/apiaudora/log/findall"
}

### Usuário não autorizado
{
    "timestamp": 1497441772721,
    "status": 401,
    "error": "Unauthorized",
    "message": "Authentication Failed: Bad credentials",
    "path": "/login"
}

### Usuario não ativo
{
    "timestamp": 1497441975034,
    "status": 401,
    "error": "Unauthorized",
    "message": "Authentication Failed: User is disabled",
    "path": "/login"
}
