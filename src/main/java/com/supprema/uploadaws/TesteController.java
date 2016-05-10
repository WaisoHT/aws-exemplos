package com.supprema.uploadaws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supprema.listobjects.ArquivoAWS;

@Controller
public class TesteController {
	
	@Autowired
	private AmazonUtilsService amazonService;
	
	 @RequestMapping(value = "/listarArquivos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	  @ResponseBody
	  public ResponseEntity<?> listarArquivos(@RequestParam(name="diretorio") String diretorio, @RequestParam(name="nome") String nome) {
		 List<ArquivoAWS> retorno = amazonService.listaArquivosPorNome(diretorio, nome);
	    return new ResponseEntity<>(retorno, HttpStatus.OK);
	  }

}