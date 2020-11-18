package com.db2020.pj.controller;

import com.amazonaws.services.apigateway.model.Model;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.GoodsImageService;
import com.db2020.pj.service.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class ImageController {
    private S3Service s3Service;


    @Autowired
    private GoodsImageService goodsImageService;


    @PostMapping("/upload/image")
    public String ImageUpload(@RequestParam MultipartFile image_path) throws IOException {


        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString();
        String result = s3Service.upload(image_path, newFileName);
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/goods/{goods_detail_seq}/image", consumes = "multipart/form-data")
    public Response InsertgoodsImage(@PathVariable String goods_detail_seq, @RequestParam MultipartFile[] image_path) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        for (MultipartFile file : image_path) {
            String uuid = UUID.randomUUID().toString();
            String result = s3Service.upload(file, uuid);
            map.put("image_path", result);
            map.put("goods_detail_seq", goods_detail_seq);
        }
        return new Response("200", "상품 이미지 추가 성공 하였습니다.", null);
    }

    @ResponseBody
    @GetMapping("/goods/{goods_detail_seq}/image")
    public Response SelectGoodsDetailImage(@PathVariable Integer goods_detail_seq) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        map.put("goods_detail_seq",goods_detail_seq);
        return new Response("200", "상품별 이미지 조회를 성공하였습니다.", goodsImageService.selectGoodsDetailImage(map));
    }
    @ResponseBody
    @DeleteMapping("/goods/image/{image_seq}")
    public Response deleteGoodsDetailImage(@PathVariable Integer image_seq) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("image_seq",image_seq);
        HashMap<String, Object> result = goodsImageService.selectImage(map);
        s3Service.deleteS3File(result.get("image_path").toString());
        goodsImageService.deleteGoodsImage(result);
        return new Response("200", "상품 이미지 삭제를 성공하였습니다.", null);
    }

}