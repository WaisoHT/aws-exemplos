package com.supprema.listobjects;
import java.io.IOException;
import java.util.List;

public class TesteS3ListObjects {

	public static void main(String[] args) throws IOException {
		S3Utils s3 = new S3Utils();

		// Conecta no S3
		s3.connect();
		
		// Lista todos os objetos do bucket
		String bucketName = "teste-publico";
		List<ArquivoAWS> arquivos = s3.listObjects(bucketName, "", "");
		String dir = "publico";
		
//		s3.getFile(bucketName, key);
		
		for (ArquivoAWS arquivoAWS : arquivos) {
			System.out.println("nome: "+arquivoAWS.getNome() + " - tamanho: "+ arquivoAWS.getTamanho());
			System.out.println("URL de Download: "+s3.getFileURL(bucketName, dir, arquivoAWS.getNome()));
		}
	}
}