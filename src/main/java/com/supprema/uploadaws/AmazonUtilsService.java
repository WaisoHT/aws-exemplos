package com.supprema.uploadaws;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.supprema.listobjects.ArquivoAWS;
import com.supprema.listobjects.S3Utils;

@Component
public class AmazonUtilsService {

	private static final String bucketName = "teste-publico";

	public List<ArquivoAWS> listaArquivosPorNome(String diretorio, String nome) {
		List<ArquivoAWS> arquivos = null;
		S3Utils s3;
		try {
			s3 = new S3Utils();
			// Lista todos os objetos do bucket
			arquivos = s3.listObjects(bucketName, diretorio, nome);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arquivos;
	}
	
	public void upload (String diretorio, String nome, String tipoDoConteudo, byte[] conteudo) throws IOException {
		S3Utils s3 = new S3Utils();
		s3.putFile(bucketName, diretorio, nome, tipoDoConteudo, conteudo);
	}
	
	public void excluir (String nome) throws IOException {
		S3Utils s3 = new S3Utils();
		s3.deleteObject(bucketName, nome);
	}

}
