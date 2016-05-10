package com.supprema.uploadaws;
import java.io.IOException;

import com.supprema.utils.S3Helper;

public class TesteS3ListBuckets {

	public static void main(String[] args) throws IOException {
		S3Helper s3 = new S3Helper();

		// Conecta no S3
		s3.connect();

		// Lista todos os buckets
//		s3.listBuckets();
	}
}