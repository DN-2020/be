//package com.db2020.pj.controller;
//
//import com.amazonaws.services.apigateway.model.Model;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.db2020.pj.entity.Test;
//import com.db2020.pj.model.Response;
//import com.db2020.pj.service.GoodsImageService;
//import com.db2020.pj.service.S3Service;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/v1")
//public class ImageController {
//    private S3Service s3Service;
//
//    @Autowired
//    private GoodsImageService goodsImageService;
//
//    @GetMapping("/file")
//    public String file(Model model) {
//    	
//    	return "file";
//    }
//   
//
//    @PostMapping("/api/upload/multi")
//    public @ResponseBody String uploadFile(Test test, @RequestBody MultipartFile[] files) {
//    	 
//    	ObjectMetadata omd = new ObjectMetadata();
//		
//    	System.out.println(test.getFileName());
//    	System.out.println(test.getSeq());
//		for(int i=0; i<files.length; i++) {
//			omd.setContentType(files[i].getContentType());
//			omd.setContentLength(files[i].getSize());
//			omd.setHeader("filename",files[i].getOriginalFilename());
//			System.out.println("filename" + files[i].getOriginalFilename());
//		}
//		return "success";
//    }
//    
//    @ResponseBody
//    @PostMapping("/goods/image")
//    public Response InsertgoodsImage(@RequestBody List<HashMap<String, Object>> map) throws IOException {
//
//        for(HashMap<String, Object> listData : map){
//            listData.put("imagePath", s3Service.upload((MultipartFile)listData.get("imagePath")));
//            goodsImageService.insertGoodsImage(listData);
//        }
//
//        return new Response("200", "프로모션조회 결과에 성공하였습니다.", null);
//    }
//    
//    @ResponseBody
//    @GetMapping("/goods/{goodsSeq}/image")
//    public Response SelectgoodsImage(@PathVariable Integer goodsSeq) throws IOException {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("goodsSeq",goodsSeq);
//
//        return new Response("200", "프로모션조회 결과에 성공하였습니다.", goodsImageService.selectGoodsImage(map));
//    }
//    @ResponseBody
//    @GetMapping("/goods/{goodsSeq}/{goodsDetailSeq}/image")
//    public Response SelectGoodsDetailImage(@PathVariable Integer goodsSeq, @PathVariable Integer goodsDetailSeq) throws IOException {
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("goodsSeq",goodsSeq);
//        map.put("goodsDetailSeq",goodsDetailSeq);
//        return new Response("200", "프로모션조회 결과에 성공하였습니다.", goodsImageService.selectGoodsDetailImage(map));
//    }
//    @ResponseBody
//    @PutMapping("/goods/{goodsSeq}/{goodsDetailSeq}/image")
//    public Response UpdateGoodsDetailImage(@PathVariable Integer goodsSeq, @PathVariable Integer goodsDetailSeq, @RequestBody HashMap<String, Object> map) throws IOException {
//
//        map.put("goodsSeq",goodsSeq);
//        map.put("goodsDetailSeq",goodsDetailSeq);
//        goodsImageService.updateGoodsImage(map);
//        return new Response("200", "프로모션조회 결과에 성공하였습니다.", null);
//    }
//    @ResponseBody
//    @DeleteMapping("/goods/{goodsSeq}/{goodsDetailSeq}/image")
//    public Response deleteGoodsDetailImage(@PathVariable Integer goodsSeq, @PathVariable Integer goodsDetailSeq,@RequestBody HashMap<String, Object> map) throws IOException {
//
//        map.put("goodsSeq",goodsSeq);
//        map.put("goodsDetailSeq",goodsDetailSeq);
//        goodsImageService.deleteGoodsImage(map);
//        return new Response("200", "프로모션조회 결과에 성공하였습니다.", null);
//    }
//
//}