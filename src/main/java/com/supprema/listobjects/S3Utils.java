package com.supprema.listobjects;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Utils {

	private AmazonS3Client s3;

	public S3Utils() throws IOException {
		this.connect();
	}
	
	public void connect() throws IOException {
	    try {
	    	Properties prop = new Properties();
	    	
	    	String resourceName = "application.properties"; // could also be a constant
	    	InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
	    	prop.load(resourceStream);
//	    	String profileName = prop.getProperty("supprema.aws.profilename");
	    	String secretaccesskey = prop.getProperty("supprema.aws.secretaccesskey");
	    	String accesskey = prop.getProperty("supprema.aws.accesskey");
	    	
	    	BasicAWSCredentials credentials = new BasicAWSCredentials(accesskey.trim(), secretaccesskey.trim());
	    	Region r = Region.getRegion(Regions.SA_EAST_1);
	    	
	    	setS3(new AmazonS3Client(credentials));
	    	getS3().setRegion(r);
	    } catch (Exception e) {
	        throw new AmazonClientException(
	                "Cannot load the credentials from the credential profiles file. " +
	                "Please make sure that your credentials file is at the correct " +
	                "location (/Users/fabianorodriguesmatias/.aws/credentials), and is in valid format.",
	                e);
	    }
	}

	public void listBuckets() {
		System.out.println("Listando buckets");
		List<Bucket> buckets = getS3().listBuckets();
		if (!buckets.isEmpty()) {
			for (Bucket bucket : buckets) {
				System.out.println(" - " + bucket.getName());
			}
		} else {
			System.out.println("Não tem nenhum bucket");
		}
		System.out.println();
	}

	public List<ArquivoAWS> listObjects(String bucketName, String diretorio, String nome) {
		List<ArquivoAWS> arquivos = new ArrayList<>();
		System.out.println("Listing objects: " + bucketName);
		ListObjectsRequest requisicao = new ListObjectsRequest()
										.withBucketName(bucketName);
		ObjectListing objectListing = getS3().listObjects(requisicao);
		List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
		boolean achouArquivos = !objectSummaries.isEmpty();
		if(achouArquivos) {
			for (S3ObjectSummary object : objectSummaries) {
				ArquivoAWS arquivo = new ArquivoAWS();
				arquivo.setNome(object.getKey());
				String tamanho = MetricasBits.getTamanhoEmFormatoLegivel(object.getSize());
				arquivo.setTamanho(tamanho);
				arquivo.setUrl(getFileURL(bucketName, object.getKey()));
				arquivo.setTipo(ArquivoTipo.getPorNomeDoArquivo(object.getKey()));
				arquivos.add(arquivo);
			}
		} else {
			System.out.println("Não tem nenhum objeto no bucket " + bucketName);
		}
		System.out.println();
		return arquivos;
	}

	public void createBucket(String bucketName) {
		getS3().createBucket(bucketName);
	}

	public void deleteBucket(String bucketName) {
		getS3().deleteBucket(bucketName);
	}

	public void deleteObject(String bucketName, String key) {
		getS3().deleteObject(bucketName, key);
	}

	public void putFile(String bucketName, String dir, String fileName, String contentType, byte[] bytes) throws IOException {
		String path = dir + "/" + fileName;

		// Salva o objeto no S3
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		metadata.setContentLength(bytes.length);
		getS3().putObject(bucketName, path, new ByteArrayInputStream(bytes), metadata);

		// Deixa público
		getS3().setObjectAcl(bucketName, path, CannedAccessControlList.PublicRead);
	}

	public String getFile(String bucketName, String key) throws IOException {
		StringBuffer sb = new StringBuffer();
		S3Object object = getS3().getObject(new GetObjectRequest(bucketName, key));
		// Metadata (content-type, content-length, permissões, expiração)
		// ObjectMetadata objectMetadata = object.getObjectMetadata();
		S3ObjectInputStream input = object.getObjectContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;
			sb.append(line);
		}
		return sb.toString();
	}


	public String getFileURL(String bucketName, String file) {
		String url = "https://s3-sa-east-1.amazonaws.com/" + bucketName + "/" + file;
		return url;
	}
	
	public String getFileURL(String bucketName, String dir, String file) {
		String url = "https://s3-sa-east-1.amazonaws.com/" + bucketName + "/" + dir + "/" + file;
		return url;
	}

	public AmazonS3Client getS3() {
		return s3;
	}

	public void setS3(AmazonS3Client s3) {
		this.s3 = s3;
	}
}
