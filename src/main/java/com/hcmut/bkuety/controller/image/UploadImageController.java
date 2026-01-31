package com.hcmut.bkuety.controller.image;

import com.hcmut.bkuety.service.cloudinary.CloudinaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class UploadImageController
{
    private final CloudinaryService cloudinaryService;
    private Integer getUserId(){
        return 1;
    }
    public UploadImageController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }
    @PostMapping("/upload/product")
    public ResponseEntity<?> uploadProductImage(@RequestParam("file") MultipartFile file,
                                         @RequestParam("productId") String fileName) throws IOException {
        return ResponseEntity.ok(cloudinaryService.uploadFile(file, "product",fileName));
    }
    @PostMapping("/upload/sku")
    public ResponseEntity<?> uploadSKUImage(@RequestParam("file") MultipartFile file,
                                                @RequestParam("skuId") String fileName) throws IOException {
        return ResponseEntity.ok(cloudinaryService.uploadFile(file, "sku",fileName));
    }
    @PostMapping("/upload/user")
    public ResponseEntity<?> uploadUserAvatar(@RequestParam("file") MultipartFile file,
                                         @RequestParam("productId") String fileName) throws IOException {
        return ResponseEntity.ok(cloudinaryService.uploadFile(file, "avatar",fileName));
    }



}
