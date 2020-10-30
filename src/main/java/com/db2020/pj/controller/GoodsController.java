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
//	@PostMapping("/company/{company_seq}/goods")
	@PostMapping("/goods")
	public CommonResult register(@RequestBody Map<String, String> goods) {
		
		goodsService.register(goods);
		
		return new CommonResult(200, "상품등록을 성공적으로 완료했습니다.");
	}
	
	// 상세 상품 등록
//	@PostMapping("/company/{company_seq}/goods/{goods_seq}")
	@PostMapping("/goods/{goods_seq}")
	public CommonResult detail_register(@PathVariable String goods_seq, @RequestBody Map<String, String> goods) {
		goods.put("goods_seq", goods_seq);
		
		goodsService.detail_register(goods);
		
		return new CommonResult(200, "상세 상품등록을 성공적으로 완료했습니다.");
	}
	
	// 상품 조회
	@GetMapping("/goods/{goods_seq}")
	public SingleResult<Goods> selectGoods(@PathVariable int goods_seq) {
		
		Goods goods = goodsService.selectOne(goods_seq);
		
		return responseService.getSingleResult(goods);
	}
	
	// 상품 상세 조회
	@GetMapping("/goods/{goods_seq}/{goods_detail_seq}")
	public SingleResult<GoodsDetail> selectDetailGoods(@PathVariable Map<String, Integer> goods_detail) {
		
		GoodsDetail goodsDetail = goodsService.selectOne(goods_detail);										
		
		return responseService.getSingleResult(goodsDetail);
	}
	
//	// 상품 예약 된 날짜 조회
//	@GetMapping("/goods/{goods_seq}/{goods_detail_seq}/reserve_date")
//	public Map<String, Object> selectReserve_date(@PathVariable Map<String, Object> parameter){
//		
//		Map<String, Object> list = goodsService.reserve_date(parameter);
//		
//		return list;
//	}
	
	
	// 상품 리스트 조회
//	@GetMapping("/company/goods/list")
	@GetMapping("/goods/company/list")
	public ListResult<Goods> selectGoodsList(@RequestBody Map<String, Object> parameter){
		
		List<Goods> goodsList = goodsService.selectList(parameter);
		
		System.out.println(goodsList.toString());
		
		return responseService.getListResult(goodsList);
	}
	
	// 관리자 전체 상품 리스트
//	@GetMapping("/admin/goods/list")
	@GetMapping("/goods/admin/list")
	public ListResult<Goods> adminGoodsList(@RequestBody Map<String, Object> parameter) {
		
		List<Goods> goodsList = goodsService.selectAdminList(parameter);
		
		return responseService.getListResult(goodsList);
	}
	
	// 상품 상세 리스트 조회
	@GetMapping("/goods/{goods_seq}/list")
	public ListResult<GoodsDetail> selectGoodsDetailList(@PathVariable int goods_seq){
		
		List<GoodsDetail> goodsDetailList = goodsService.selectDetailList(goods_seq);
		
		return responseService.getListResult(goodsDetailList);
	}
	
	// 상품 리스트 단일 등록 변경
//	@PutMapping("/admin/goods/{goods_seq}")
	@PutMapping("/goods/{goods_seq}/isView")
	public CommonResult goodsRegister(@PathVariable String goods_seq, @RequestBody Map<String, Object> parameter) {

		System.out.println(goods_seq);
		parameter.put("goods_seq", goods_seq);
		goodsService.goodsIsView(parameter);
		
		return new CommonResult(200, "상품 리스트에 상품을 성공적으로 변경하였습니다."); 
	}
	
	// 상품 리스트 상세 등록 변경
//	@PutMapping("/admin/goods/{goods_seq}/{goods_detail_seq}")
	@PutMapping("/goods/{goods_seq}/{goods_detail_seq}/isView")
	public CommonResult goodsDetailRegister(@PathVariable Map<String, Object> parameter, @RequestBody String goods_detail_view_yn) {
		
		parameter.put("goods_detail_view_yn", goods_detail_view_yn);
		goodsService.goodsDetailIsView(parameter);
		
		return new CommonResult(200, "상품 리스트에 상세 상품을 성공적으로 변경하였습니다."); 
	}
	
	// 상품 정보 수정
	@PutMapping("/goods/{goods_seq}")
	public CommonResult updateGoods(@PathVariable String goods_seq, @RequestBody Map<String, Object> goods) {
		
		goods.put("goods_seq", goods_seq);
		goodsService.update(goods);
		return new CommonResult(200, "상품 수정을 성공적으로 하였습니다.");
	}
	
	// 상품 정보 상세 수정
	@PutMapping("/goods/{goods_seq}/{goods_detail_seq}")
	public CommonResult detail_updateGoods(@PathVariable Map<String, Object> parameter, @RequestBody Map<String, Object> goods) {
		
		goods.put("goods_seq", parameter.get("goods_seq"));
		goods.put("goods_detail_seq", parameter.get("goods_detail_seq"));
		
		System.out.println(parameter.get("goods_seq"));
		System.out.println(parameter.get("goods_detail_seq"));
		goodsService.detail_update(goods);
		return new CommonResult(200, "상세 상품 수정을 성공적으로 하였습니다.");
	}

	@DeleteMapping("/goods/{goods_seq}")
	public CommonResult deleteGoods(@PathVariable String goods_seq) {
		
		goodsService.delete(goods_seq);
		
		return new CommonResult(200, "상품 삭제를 성공적으로 하였습니다.");
	}
		
	@DeleteMapping("/goods/{goods_seq}/{goods_detail_seq}")
	public CommonResult detail_deleteGoods(@PathVariable Map<String, String> parameter) {

		goodsService.detail_delete(parameter);
		
		return new CommonResult(200, "상품 상세 삭제를 성공적으로 하였습니다.");
	}

	@GetMapping("search")
	public ListResult<Goods> searchGoods(@RequestBody HashMap<String, Object> goods){

		List<Goods> goodsList = goodsService.selectSearch(goods);
		return responseService.getListResult(goodsList);
	}

	@GetMapping("search/{category}")
	public ListResult<Goods> searchCategoryGoods(@RequestBody HashMap<String, Object> goods,
											@PathVariable String category){
		goods.put("category",category);
		List<Goods> goodsList = goodsService.selectCategorySearch(goods);
		return responseService.getListResult(goodsList);
	}

}
