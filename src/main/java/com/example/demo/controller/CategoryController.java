package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	// カテゴリー一覧表示
	@GetMapping("/categories")
	public String index(Model model) {
		// カテゴリーテーブルのすべてのカテゴリーを取得
		List<Category> list = categoryRepository.findAll();
		// スコープに取得したカテゴリーリストを登録
		model.addAttribute("categoryList", list);
		// 画面遷移
		return "category/categories.html";
	}
	
}
