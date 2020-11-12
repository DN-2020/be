package com.db2020.pj.controller;

import com.amazonaws.services.apigateway.model.Model;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.GoodsImageService;
import com.db2020.pj.service.S3Service;
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


    @ResponseBody
    @PostMapping(value = "/goodsdetail/{goodsDetail_seq}/image", consumes = "multipart/form-data")
    public Response InsertgoodsImage(@PathVariable String goodsDetail_seq, @RequestParam MultipartFile[] files) throws IOException {

        HashMap<String ,Object> map = new HashMap<>();
        for(MultipartFile file : files){
            String result =  s3Service.upload(file,"goods/detail/"+goodsDetail_seq+"/");
            map.put("imagePath", result);
            map.put("goodsDetailSeq", goodsDetail_seq);
            goodsImageService.insertGoodsImage(map);
        }
        return new Response("200", "이미지 추가 성공하였습니다.", null);
    }

    @ResponseBody
    @GetMapping("/goods/{goodsSeq}/image")
    public Response SelectgoodsImage(@PathVariable Integer goodsSeq) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsSeq",goodsSeq);

        return new Response("200", "상품별 이미지 조회 성공하였습니다", goodsImageService.selectGoodsImage(map));
    }
    @ResponseBody
    @GetMapping("/goods/{goodsSeq}/{goodsDetailSeq}/image")
    public Response SelectGoodsDetailImage(@PathVariable Integer goodsSeq, @PathVariable Integer goodsDetailSeq) throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsSeq",goodsSeq);
        map.put("goodsDetailSeq",goodsDetailSeq);
        return new Response("200", "상세 상품별 조회를 성공하였습니다.", goodsImageService.selectGoodsDetailImage(map));
    }
    @ResponseBody
    @PutMapping("/goods/{goodsSeq}/{goodsDetailSeq}/image")
    public Response UpdateGoodsDetailImage(@PathVariable Integer goodsSeq, @PathVariable Integer goodsDetailSeq, @RequestBody HashMap<String, Object> map) throws IOException {

        map.put("goodsSeq",goodsSeq);
        map.put("goodsDetailSeq",goodsDetailSeq);
        goodsImageService.updateGoodsImage(map);
        return new Response("200", "메인 이미지 변경을 성공하였습니다.", null);
    }
    @ResponseBody
    @DeleteMapping("/goods/{goodsSeq}/{goodsDetailSeq}/image")
    public Response deleteGoodsDetailImage(@PathVariable Integer goodsSeq, @PathVariable Integer goodsDetailSeq,@RequestBody HashMap<String, Object> map) throws IOException {

        map.put("goodsSeq",goodsSeq);
        map.put("goodsDetailSeq",goodsDetailSeq);

        HashMap<String, Object> result = goodsImageService.selectImage(map);
        s3Service.deleteS3File(result.get("image_path").toString());
        goodsImageService.deleteGoodsImage(map);
        return new Response("200", "이미지 삭제를 성공하였습니다.", null);
    }

}