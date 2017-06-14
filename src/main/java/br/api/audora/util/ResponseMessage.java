package br.api.audora.util;

import org.springframework.http.HttpStatus;

public class ResponseMessage {
	
	private static final String MENSAGEM_OK = "Operação realizada com sucesso";
	private static final String MENSAGEM_ERRO = "Falha na operação";
	
	private String status;
	private String mensagem;
	
	public ResponseMessage(String status, String mensagem) {
		super();
		this.status = status;
		this.mensagem = mensagem;
	}
	
	public static ResponseMessage getMensagemOk(){
		return new ResponseMessage(HttpStatus.OK.toString(), MENSAGEM_OK);
	}
	
	public static ResponseMessage getMensagemErro(){
		return new ResponseMessage(HttpStatus.BAD_REQUEST.toString(), MENSAGEM_ERRO);
	}
	
	public static ResponseMessage getMensagemErro(String erro){
		return new ResponseMessage(HttpStatus.BAD_REQUEST.toString(), erro);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
