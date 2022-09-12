package nts.sixblack.hexa.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadFile(MultipartFile files) {
        File file = convertMultipartFiletoFile(files);
        try {
            Map r = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                    "resource_type","auto"
            ));
            String url = (String) r.get("secure_url");
            return url;
        } catch (Exception e) {
            return "null";
        }
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
