package br.api.audora.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.api.audora.domain.Log;
import br.api.audora.repository.LogRepository;
import br.api.audora.repository.LogSpecificationsBuilder;
import br.api.audora.util.ResponseMessage;

@Service("logService")
public class LogService {
	
	@Autowired
	private LogRepository logRepository;
	
	public ResponseEntity<?> findById(Long id){
		try {
            return new ResponseEntity<>(logRepository.findOne(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}
	
	public ResponseEntity<?> findAll(){
		try {
            return new ResponseEntity<>(logRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}
	
	public ResponseEntity<?> findByProduto(String produto){
		try {
            return new ResponseEntity<>(logRepository.findByProduto(produto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}
	
	public ResponseEntity<?> findByCliente(String cliente){
		try {
            return new ResponseEntity<>(logRepository.findByCliente(cliente), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}
	
	public ResponseEntity<?> findByCategoria(String categoria){
		try {
            return new ResponseEntity<>(logRepository.findByCategoria(categoria), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}
	
	public ResponseEntity<?> findByIntervaloDataHora(Date dataInicio, Date dataFim){
		try {
            return new ResponseEntity<>(logRepository.findByIntervaloDataHora(dataInicio, dataFim), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}
	
	public ResponseEntity<?> find(String produto, String cliente, String categoria, String dataInicio, String dataFim) {
		
		try {
			Specification<Log> specifications = new LogSpecificationsBuilder()
					.with("produto", "=", produto)
					.with("cliente", "=", cliente)
					.with("categoria", "=", categoria)
					.with("dataHora", "between", dataInicio+":"+dataFim)
					.build();
		
			Iterable<Log> logs = logRepository.findAll(specifications);
			return new ResponseEntity<>(logs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}
	
	public ResponseEntity<?> save(Log log){
		try {
			logRepository.save(log);
            return new ResponseEntity<>(ResponseMessage.getMensagemOk(), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO chave duplicada pode usar status 409(conflict)
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}
	
	public ResponseEntity<?> delete(Log log){
		try {
			logRepository.delete(log);
            return new ResponseEntity<>(ResponseMessage.getMensagemOk(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}
	
	public ResponseEntity<?> delete(Long id){
		try {
			logRepository.delete(id);
            return new ResponseEntity<>(ResponseMessage.getMensagemOk(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMessage.getMensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
	}


}
