package nts.sixblack.hexa.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile files){
        File file = convertMultipartFiletoFile(files);
        String fileName = System.currentTimeMillis()+"";
        s3Client.putObject(new PutObjectRequest(bucketName,fileName, file));
        return fileName;
    }

    private File convertMultipartFiletoFile(MultipartFile file){
        File converFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(converFile)){
            fos.write(file.getBytes());
        } catch (Exception e){

        }
        return converFile;
    }
}
