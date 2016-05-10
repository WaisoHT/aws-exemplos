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
			//String dir = "publico";
			
//			s3.getFile(bucketName, key);
			
			/*for (ArquivoAWS arquivoAWS : arquivos) {
				System.out.println("nome: "+arquivoAWS.getNome() + " - tamanho: "+ arquivoAWS.getTamanho());
				System.out.println("URL de Download: "+s3.getFileURL(bucketName, dir, arquivoAWS.getNome()));
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}

		return arquivos;
	}
	
}
