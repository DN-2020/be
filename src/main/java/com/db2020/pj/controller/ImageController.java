package com.db2020.pj.controller;

import com.amazonaws.services.apigateway.model.Model;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.GoodsImageService;
import com.db2020.pj.service.S3Service;
import com.sun.org.apache.xpath.internal.operations.Mult;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class ImageController {
    private S3Service s3Service;


    @Autowired
    private GoodsImageService goodsImageService;

    @GetMapping("/file")
    public String file(Model model) {

        return "file";
    }

    @PostMapping("/upload/image")
    public String ImageUpload(@RequestParam MultipartFile image_path) throws IOException {

        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString();
        String result = s3Service.upload(image_path, newFileName);
        return result;
    }
    @ResponseBody
    @PostMapping(value = "/goodsdetail/{t_goods_detail_seq}/image", consumes = "multipart/form-data")
    public Response InsertgoodsImage(@PathVariable String t_goods_detail_seq, @RequestParam MultipartFile[] image_path) throws IOException {

        HashMap<String ,Object> map = new HashMap<>();
        for(MultipartFile file : image_path){
            String result =  s3Service.upload(file,"goods/detail/"+t_goods_detail_seq+"/");
            map.put("image_path", file);
            map.put("t_goods_detail_seq", t_goods_detail_seq);
            goodsImageService.insertGoodsImage(map);
        }
        return new Response("200", "이미지 추가 성공하였습니다.", null);
    }

    @ResponseBody
    @GetMapping("/goods/{t_goods_seq}/image")
    public Response SelectgoodsImage(@PathVariable Integer t_goods_seq) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("t_goods_seq",t_goods_seq);

        return new Response("200", "상품별 이미지 조회 성공하였습니다", goodsImageService.selectGoodsImage(map));
    }
    @ResponseBody
    @GetMapping("/goods/{t_goods_seq}/{t_goods_detail_seq}/image")
    public Response SelectGoodsDetailImage(@PathVariable Integer t_goods_seq, @PathVariable Integer t_goods_detail_seq) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        map.put("t_goods_seq",t_goods_seq);
        map.put("t_goods_detail_seq",t_goods_detail_seq);
        return new Response("200", "상세 상품별 조회를 성공하였습니다.", goodsImageService.selectGoodsDetailImage(map));
    }
    @ResponseBody
    @PutMapping("/goods/{t_goods_seq}/{t_goods_detail_seq}/image")
    public Response UpdateGoodsDetailImage(@RequestBody HashMap<String, Object> map) throws IOException {

        goodsImageService.updateGoodsImage(map);
        return new Response("200", "메인 이미지 변경을 성공하였습니다.", null);
    }
    @ResponseBody
    @DeleteMapping("/goods/{t_goods_seq}/{t_goods_detail_seq}/image")
    public Response deleteGoodsDetailImage(@PathVariable Integer t_goods_detail_seq, @PathVariable Integer goodsDetailSeq,@RequestBody HashMap<String, Object> map) throws IOException {


        HashMap<String, Object> result = goodsImageService.selectImage(map);
        s3Service.deleteS3File(result.get("image_path").toString());
        goodsImageService.deleteGoodsImage(map);
        return new Response("200", "이미지 삭제를 성공하였습니다.", null);
    }

}