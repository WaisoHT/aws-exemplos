package com.supprema.uploadaws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.supprema.utils.S3Helper;

@Controller
@RequestMapping("/upload-aws")
public class MainController {
  
  // The Environment object will be used to read parameters from the 
  // application.properties configuration file
  @Autowired
  private Environment env;
  
  /**
   * Show the index page containing the form for uploading a file.
   */
  @RequestMapping("/")
  public String index() {
    return "index.html";
  }
  
  /**
   * POST /uploadFile -> receive and locally save a file.
   * 
   * @param uploadfile The uploaded file as Multipart file parameter in the 
   * HTTP request. The RequestParam name must be the same of the attribute 
   * "name" in the input tag with type file.
   * 
   * @return An http OK status in case of success, an http 4xx status in case 
   * of errors.
   */
  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> uploadFile(
      @RequestParam("uploadfile") MultipartFile uploadfile) {
    
    try {
      // Get the filename and build the local file path
      String filename = uploadfile.getOriginalFilename();
      
      S3Helper s3 = new S3Helper();
      s3.connect();
      
      
      
      String bucketName = "livroaws-java-demo";
	String dir = "somePath/";
	String contentType = uploadfile.getContentType();
	byte[] bytes = uploadfile.getBytes();
	s3.putFile(bucketName, dir, filename, contentType, bytes);
//      String directory = env.getProperty("/Users/fabianorodriguesmatias/Developer/workspace/poc/microservices/spring-boot-samples/spring-boot-file-upload-with-ajax/src/main/resources");
//      String directory = "/Users/fabianorodriguesmatias/Developer/workspace/poc/microservices/spring-boot-samples/spring-boot-file-upload-with-ajax/src/main/resources";
//      String filepath = Paths.get(directory, filename).toString();
      
      // Save the file locally
//      BufferedOutputStream stream =          new BufferedOutputStream(new FileOutputStream(new File(filepath)));
//      stream.write(uploadfile.getBytes());
//      stream.close();
      
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    return new ResponseEntity<>(HttpStatus.OK);
  } // method uploadFile

} // class MainController

