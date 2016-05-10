package com.supprema.uploadaws;
import java.io.File;
import java.io.IOException;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.supprema.utils.S3Helper;

public class TesteS3 {

	public static void main(String[] args) throws IOException {
		S3Helper help = new S3Helper();

		// Conecta no S3
		help.connect();

		// Lista todos os buckets
//		help.listBuckets();
		
		// Cria um novo bucket
		System.out.println("Criando o bucket livroaws-java-demo");
//		help.createBucket("livroaws-java-demo");
		
		// Lista todos os buckets novamente. Vai aparecer o novo bucket criado.
//		help.listBuckets();
		
		// Envia um arquivo texto para o bucket
		// Salva em [livroaws-java-demo]/Teste/nome.txt
//		byte[] bytes = new String("Fabiano Rodrigues Matias").getBytes();
        File arquivo = new File("/Users/fabianorodriguesmatias/sprites.png");
		String key = "somePath/someKey.jpg";
		PutObjectRequest putObjectRequest = new PutObjectRequest("livroaws-java-demo",  key, arquivo);
        help.getS3().putObject(putObjectRequest);
        		
//		s3.putFile("livroaws-java-demo", "Teste", "nome.txt", "plain/text", bytes);
		
		// Lista os objetos do bucket novo. Vai exibir o nome.txt
		help.listObjects("livroaws-java-demo");
		
		// Faz download do arquivo nome.txt e imprime o conteudo
		String s = help.getFile("livroaws-java-demo", "Teste/nome.txt");
		System.out.println("Imprimindo arquivo Teste/nome.txt");
		System.out.println(s);
		
		// Deleta o arquivo nome.txt do bucket
//		System.out.println("\nExcluindo objeto Teste/nome.txt");
//		s3.deleteObject("livroaws-java-demo", "Teste/nome.txt");
		
		// Deleta o novo bucket
//		System.out.println("\nExcluindo bucket");
//		s3.deleteBucket("livroaws-java-demo");
		
		// Lista todos os buckets novamente.
//		help.listBuckets();
	}
}
