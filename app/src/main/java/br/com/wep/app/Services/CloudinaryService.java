package br.com.wep.app.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryService {

    private Cloudinary configCloudinary() {

        Map config = new HashMap();
        config.put("cloud_name", "dxblalpv2");
        config.put("api_key", "533837714717359");
        config.put("api_secret", "bLMSuhK0Oy8_tOrlRcpGmM9IXCI");
        Cloudinary cloudinary = new Cloudinary(config);

        return cloudinary;

    }

    public Map uploadImage(String file) throws IOException {

        Cloudinary cloudinary = this.configCloudinary();

        return cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

    }
}
