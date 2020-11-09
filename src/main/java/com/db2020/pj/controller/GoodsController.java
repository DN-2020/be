package com.db2020.pj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db2020.pj.entity.Goods;
import com.db2020.pj.entity.GoodsDetail;
import com.db2020.pj.model.CommonResult;
import com.db2020.pj.model.ListResult;
import com.db2020.pj.model.Response;
import com.db2020.pj.model.SingleResult;
import com.db2020.pj.service.GoodsService;
import com.db2020.pj.service.ResponseService;

@RestController
@RequestMapping(value = "/v1")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ResponseService responseService;

    // 상품등록
    @PostMapping("/goods")
    public Response register(@RequestBody Map<String, String> goods) {

        goodsService.register(goods);
        
        return new Response("200", "상품등록을 성공적으로 완료했습니다.", null);
    }

    // 상세 상품 등록
    @PostMapping("/goods/{goods_seq}")
    public Response detail_register(@PathVariable String goods_seq, @RequestBody Map<String, String> goods) {
        goods.put("goods_seq", goods_seq);

        goodsService.detail_register(goods);

        return new Response("200", "상세 상품등록을 성공적으로 완료했습니다.", null);
    }

    // 상품 조회
    @GetMapping("/goods/{goods_seq}")
    public Response selectGoods(@PathVariable int goods_seq) {

        Goods goods = goodsService.selectOne(goods_seq);

        return new Response("200", "상품 조회를 성공적으로 하였습니다.", goods);
    }

    // 상품 상세 조회
    @GetMapping("/goods/{goods_seq}/{goods_detail_seq}")
    public Response selectDetailGoods(@PathVariable Map<String, Integer> goods_detail) {

        GoodsDetail goodsDetail = goodsService.selectOne(goods_detail);

        return new Response("200", "상품 상세 조회를 성공적으로 하였습니다.", goodsDetail);
    }

	// 상품 예약 된 날짜 조회
	@GetMapping("/goods/{goods_seq}/{goods_detail_seq}/reserve_date")
	public Response selectReserve_date(@PathVariable Map<String, Object> parameter){
		
		List<HashMap<String, Object>> data = goodsService.reserve_date(parameter);
		
		if(data.isEmpty()) {
			data = null;
		}
		return new Response("200", "상품 예약 된 날짜 조회를 성공하였습니다.", data);
	}


    // 상품 리스트 조회
    @GetMapping("/goods/company/list")
    public Response selectGoodsList(@RequestParam Map<String, Object> parameter) {

        List<Goods> goodsList = goodsService.selectList(parameter);

        return new Response("200", "상품 회사별 리스트를 성공적으로 조회하였습니다.", goodsList);
    }

    // 관리자 전체 상품 리스트
    @GetMapping("/goods/admin/list")
    public Response adminGoodsList(@RequestParam Map<String, Object> parameter) {

        List<Goods> goodsList = goodsService.selectAdminList(parameter);

        return new Response("200", "관리자용 상품 리스트를 성공적으로 조회하였습니다.", goodsList);
      
    }

    // 상품 상세 리스트 조회
    @GetMapping("/goods/{goods_seq}/list")
    public Response selectGoodsDetailList(@PathVariable int goods_seq) {

        List<GoodsDetail> goodsDetailList = goodsService.selectDetailList(goods_seq);

        if(goodsDetailList.isEmpty()) {
        	goodsDetailList = null;
        }
        return new Response("200", "상품 상세 리스트를 성공적으로 조회하였습니다.", goodsDetailList);
    }

    // 상품 리스트 단일 등록 변경
    @PutMapping("/goods/{goods_seq}/isView")
    public Response goodsRegister(@PathVariable String goods_seq, @RequestBody Map<String, Object> parameter) {

        parameter.put("goods_seq", goods_seq);
        goodsService.goodsIsView(parameter);

        return new Response("200", "상품의 등록상태를 성공적으로 변경하였습니다.", null);
    }

    // 상품 리스트 상세 등록 변경
    @PutMapping("/goods/{goods_seq}/{goods_detail_seq}/isView")
    public Response goodsDetailRegister(@PathVariable Map<String, Object> parameter, @RequestBody Map<String, Object> goods_detail_view_yn) {

//        parameter.put("goods_detail_view_yn", goods_detail_view_yn);
    	goods_detail_view_yn.get("goods_detail_view_yn");
    	String param = (String)goods_detail_view_yn.get("goods_detail_view_yn");
    	System.out.println(param);
        goodsService.goodsDetailIsView(parameter, param);

        return new Response("200", "상품 상세의 등록상태를 성공적으로 변경하였습니다.", null);
    }

    // 상품 정보 수정
    @PutMapping("/goods/{goods_seq}")
    public Response updateGoods(@PathVariable String goods_seq, @RequestBody Map<String, Object> goods) {

        goods.put("goods_seq", goods_seq);
        goodsService.update(goods);
        
        return new Response("200", "상품 수정을 성공적으로 하였습니다.", null);
    }

    // 상품 정보 상세 수정
    @PutMapping("/goods/{goods_seq}/{goods_detail_seq}")
    public Response detail_updateGoods(@PathVariable Map<String, Object> parameter, @RequestBody Map<String, Object> goods) {

        goods.put("goods_seq", parameter.get("goods_seq"));
        goods.put("goods_detail_seq", parameter.get("goods_detail_seq"));

        System.out.println(parameter.get("goods_seq"));
        System.out.println(parameter.get("goods_detail_seq"));
        goodsService.detail_update(goods);
        return new Response("200", "상세 상품 수정을 성공적으로 하였습니다.", null);
    }

    @DeleteMapping("/goods/{goods_seq}")
    public Response deleteGoods(@PathVariable String goods_seq) {

        goodsService.delete(goods_seq);

        return new Response("200", "상품 삭제를 성공적으로 하였습니다.", null);
    }

    @DeleteMapping("/goods/{goods_seq}/{goods_detail_seq}")
    public Response detail_deleteGoods(@PathVariable Map<String, String> parameter) {

        goodsService.detail_delete(parameter);

        return new Response("200", "상세 상품 삭제를 성공적으로 하였습니다.", null);
    }

    @GetMapping("/search")
    public Response searchGoods(@RequestBody HashMap<String, Object> goods) {

        List<Goods> goodsList = goodsService.selectSearch(goods);
        
        if(goodsList.isEmpty()) {
        	goodsList = null;
        }
        return new Response("200", "상품 검색을 성공적으로 하였습니다.", goodsList);
    }

    @GetMapping("/search/{category}")
    public Response searchCategoryGoods(@RequestBody HashMap<String, Object> goods, @PathVariable String category) {
        goods.put("category", category);
        List<Goods> goodsList = goodsService.selectCategorySearch(goods);
        if(goodsList.isEmpty()) {
        	goodsList = null;
        }
        return new Response("200", "카테고리 상품 검색을 성공적으로 하였습니다.", goodsList); 
    }
}
