package com.db2020.pj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db2020.pj.entity.Promotion;
import com.db2020.pj.entity.PromotionGoodsDTO;
import com.db2020.pj.model.CommonResult;
import com.db2020.pj.model.Response;
import com.db2020.pj.service.PromotionService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/v1")
public class PromotionController {

	@Autowired
	PromotionService promotionService;

	@GetMapping(value = "/promotion")
	public Response promotionList(HttpServletRequest req, HttpServletResponse res) {
		if (promotionService.promotionList().isEmpty()) {
			return new Response("400", "해당 목록이 비어있습니다.", null);
		} else {
			return new Response("200", "프로모션조회 결과에 성공하였습니다.", promotionService.promotionList());
		}
	}

	@PostMapping(value = "/promotion")
	public CommonResult promotionInsert(HttpServletRequest req, HttpServletResponse res,
			@RequestBody Promotion promotion) {

		promotionService.promotionInsert(promotion);
		CommonResult result = new CommonResult();
		result.setCode(200);
		result.setMsg("프로모션등록 결과에 성공하였습니다.");

		return result;
	}

	@RequestMapping(value = "/promotion/{promotion_seq}", method = RequestMethod.PUT)
	public CommonResult promotionUpdate(@PathVariable Integer promotion_seq, HttpServletRequest req,
			HttpServletResponse res, @RequestBody Promotion promotion) {

		promotion.setPromotion_seq(promotion_seq);
		promotionService.promotionUpdate(promotion);

		CommonResult result = new CommonResult();
		result.setCode(200);
		result.setMsg("프로모션수정 결과에 성공하였습니다.");

		return result;
	}

	@RequestMapping(value = "/promotion/{promotion_seq}", method = RequestMethod.DELETE)
	public CommonResult promotionDelete(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("promotion_seq") int promotion_seq) {

		promotionService.promotionDelete(promotion_seq);
		CommonResult result = new CommonResult();
		result.setCode(200);
		result.setMsg("프로모션삭제 결과에 성공하였습니다.");

		return result;
	}

	@GetMapping(value = "/promotion/{promotion_seq}")
	public Response promotionGoodsList(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("promotion_seq") int promotion_seq) {

		PromotionGoodsDTO dto = new PromotionGoodsDTO();
		if (promotionService.promotionDetail(promotion_seq) == null) {
			return new Response("400", "해당 목록이 비어있습니다.", null);
		} else {
			dto = promotionService.promotionDetail(promotion_seq);
			if(promotionService.promotionGoodsList(promotion_seq).isEmpty()) {
				dto.setGoods_detail(null);
			}else {
			dto.setGoods_detail(promotionService.promotionGoodsList(promotion_seq));
			}
			return new Response("200", "프로모션상세조회 결과에 성공하였습니다.", dto);
		}
	}

}
