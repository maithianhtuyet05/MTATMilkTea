package com.fpt.mtatmilkteaapi.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.cloudinary.*;

@Service
public class CloudinaryService {
    // Email: huypham22716@gmail.com
    // Password: Milktea@2021
    private Cloudinary cloudinary;
    private String cloudName = "fpt-aptech-mtat";
    private String apiKey = "448453731621348";
    private String apiSecret = "gxAskpQXkxL7wX50c_y_tL-KYPc";
    private Map<String, String> valuesMap;

    public CloudinaryService() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "fpt-aptech-hhtl",
                "api_key", "448453731621348",
                "api_secret", "gxAskpQXkxL7wX50c_y_tL-KYPc"));
    }

    public Map upload(MultipartFile multipartFile, Map<String, String> options) throws IOException {
        File file = convert(multipartFile);

        Map result = cloudinary.uploader().upload(file, options);
        file.delete();
        return result;
    }

    public Map delete(String id, Map<String, String> options) throws IOException {
        Map result = null;
        try {
            result = cloudinary.uploader().destroy(id, options);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
