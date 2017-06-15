package br.api.audora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.api.audora.domain.Log;
import br.api.audora.service.LogService;
import br.api.audora.util.Converter;

@RestController
@RequestMapping(value="/apiaudora")
public class LogRestController {

	@Autowired
	private LogService logService;

	/**
	 * ESSE METODO SALVA UM NOVO LOG CONFORME EXEMPLO.
	 * 
	 * EXEMPLO:
	 * 
	 * 	URL: http://localhost:8090/apiaudora/log/save
	 * 
	 *  REQUEST JSON:
	 *  			{
						"produto":"Defensoria",
						"cliente":"Sergipe",
						"dataHora":"2017-06-13T09:05:00-03:00",
						"categoria":"erro",
						"registro": {
							"reg1":"1 registro",
							"reg2":"2 registro"
						}
	 *  			}
	 *  
	 *  
	 *  RESPONSE JSON:
	 *  			{
	 *  				"status": 200,
	 *  				"mensagem": "Operacao realizada com sucesso"
	 *  			}
	 *  
	 *  
	 * 
	 * */
	@RequestMapping(value = "/log/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody Log log) {
		return logService.save(log);
	}
	
	/**
	 * EXCLUI UM LOG PELO SEU ID CONFORME EXEMPLO ABAIXO;
	 * 
	 * EXEMPLO:
	 * 	
	 * 	URL REQUEST:http://localhost:8090/apiaudora/log/delete/1
	 * 
	 * 	RESPONSE JSON:
	 * 				{
	 * 					"status": 200,
	 *  				"mensagem": "Operacao realizada com sucesso"
	 * 				}
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/log/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return logService.delete(id);
	}

	/**
	 * CONSULTA UM LOG PELO SEU ID CONFORME EXEMPLO
	 * 
	 * EXEMPLO:
	 * 	 URL:http://localhost:8090/apiaudora/log/18
	 * 
	 *   RESPONSE JSON:
	 *				{
				        "id": 18,
				        "produto": "Defensoria",
				        "cliente": "Alagoas",
				        "dataHora": 1497359100000,
				        "categoria": "erro",
				        "registro": {}
				    }	
	 *   
	 *   
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/log/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> find(@PathVariable("id") Long id) {
		return logService.findById(id);
	}

	/**
	 * CONSULTA TODOS OS LOGS CONFORME EXEMPLO
	 * 
	 * EXEMPLO:
	 * 	 URL:http://localhost:8090/apiaudora/log/findall
	 * 
	 *   RESPONSE JSON:
	 *				[
	 *					{
					        "id": 18,
					        "produto": "Defensoria",
					        "cliente": "Alagoas",
					        "dataHora": 1497359100000,
					        "categoria": "erro",
					        "registro": {}
					    },
					    {
					        "id": 19,
					        "produto": "OAB",
					        "cliente": "Sergipe",
					        "dataHora": 1497420000511,
					        "categoria": "permissao",
					        "registro": {
					            "reg1": "1 registro",
					            "reg2": "2 registro"
					        }
					    }
	 *				]   	
	 *   
	 *   
	 * 
	 * @return
	 */
	@RequestMapping(value = "/log/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> findAll() {
		return logService.findAll();
	}
	
	/**
	 * CONSULTA LOGS POR SUA CATEGORIA CONFORME EXEMPLO
	 * 
	 * EXEMPLO:
	 * 	 URL:http://localhost:8090/apiaudora/log/categoria/erro
	 * 
	 *   RESPONSE JSON:
	 *				[
		 *				{
					        "id": 18,
					        "produto": "Defensoria",
					        "cliente": "Alagoas",
					        "dataHora": 1497359100000,
					        "categoria": "erro",
					        "registro": {}
					    }
					 ]	
	 *   
	 *   
	 * @param categoria
	 * @return
	 */
	@RequestMapping(value = "/log/categoria/{categoria}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> findByCategoria(@PathVariable("categoria") String categoria) {
		return logService.findByCategoria(categoria);
	}
	
	/**
	 * CONSULTA LOGS POR SEU CLIENTE CONFORME EXEMPLO
	 * 
	 * EXEMPLO:
	 * 	 URL:http://localhost:8090/apiaudora/log/cliente/Alagoas
	 * 
	 *   RESPONSE JSON:
	 *				[
		 *				{
					        "id": 18,
					        "produto": "Defensoria",
					        "cliente": "Alagoas",
					        "dataHora": 1497359100000,
					        "categoria": "erro",
					        "registro": {}
					    }
					 ]	
	 *   
	 *   
	 * @param cliente
	 * @return
	 */
	@RequestMapping(value = "/log/cliente/{cliente}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> findByCliente(@PathVariable("cliente") String cliente) {
		return logService.findByCliente(cliente);
	}
	
	/**
	 * CONSULTA LOGS POR SEU PRODUTO CONFORME EXEMPLO
	 * 
	 * EXEMPLO:
	 * 	 URL:http://localhost:8090/apiaudora/log/produto/Defensoria
	 * 
	 *   RESPONSE JSON:
	 *				[
		 *				{
					        "id": 18,
					        "produto": "Defensoria",
					        "cliente": "Alagoas",
					        "dataHora": 1497359100000,
					        "categoria": "erro",
					        "registro": {}
					    }
					 ]	
	 *   
	 *   
	 * @param produto
	 * @return
	 */
	@RequestMapping(value = "/log/produto/{produto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> findByProduto(@PathVariable("produto") String produto) {
		return logService.findByProduto(produto);
	}
	
	/**
	 * CONSULTA LOGS POR INTERVALO DE TEMPO CONFORME EXEMPLO
	 * 
	 * EXEMPLO:
	 * 	 URL:http://localhost:8090/apiaudora/log/intervalodata/2017-06-12/2017-06-13
	 * 
	 *   RESPONSE JSON:
	 *				[
		 *				{
					        "id": 18,
					        "produto": "Defensoria",
					        "cliente": "Alagoas",
					        "dataHora": 1497359100000,
					        "categoria": "erro",
					        "registro": {}
					    }
					 ]	
	 *   
	 *   
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	@RequestMapping(value = "/log/intervalodata/{dataInicio}/{dataFim}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> findByCategoria(@PathVariable("dataInicio") String dataInicio, @PathVariable("dataFim") String dataFim) {
		return logService.findByIntervaloDataHora(Converter.converterStringToDate(dataInicio), Converter.converterStringToDate(dataFim));
	}
	
	
	/**
	 * CONSULTA TODOS OS LOGS CONFORME EXEMPLO
	 * 
	 * EXEMPLO:
	 * 	 URL:http://localhost:8090/apiaudora/log/find?cliente=Alagoas&produto=OAB
	 * 	
	 * Parametros aceitos:
	 * 		cliente - pode receber os valores "Alagoas", "Sergipe", etc.
	 * 		produto - pode receber os valores "Defensoria" ou "OAB" 
	 * 		categoria - pode assumir valores como "erro", "permissao", "autenticaçao", etc.
	 * 		dataInicio - para consulta de intervalo (data inicial do intervalo)
	 * 		dataFim - para consulta de intervalo (data final do intervalo)
	 * 
	 *   RESPONSE JSON:
	 *				[
	 *					{
					        "id": 18,
					        "produto": "Defensoria",
					        "cliente": "Alagoas",
					        "dataHora": 1497359100000,
					        "categoria": "erro",
					        "registro": {}
					    },
					    {
					        "id": 19,
					        "produto": "OAB",
					        "cliente": "Sergipe",
					        "dataHora": 1497420000511,
					        "categoria": "permissao",
					        "registro": {
					            "reg1": "1 registro",
					            "reg2": "2 registro"
					        }
					    }
	 *				]   	
	 *   
	 *   
	 * 
	 * @return
	 */
	@RequestMapping(value = "/log/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> find(@RequestParam(value="produto",required = false) String produto, @RequestParam(value="cliente", required = false) String cliente, 
			@RequestParam(value="categoria", required = false) String categoria, @RequestParam(value="dataInicio", required = false) String dataInicio, 
			@RequestParam(value="dataFim", required = false) String dataFim) {
		
		return logService.find(produto, cliente, categoria, dataInicio, dataFim);
	}
	
}
