package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// カテゴリー登録画面表示
	@GetMapping("/categories/add")
	public String add() {
		return "/category/addCategory";
	}
	
	@PostMapping("/categories/add")
	public String store(
			@RequestParam(name = "", defaultValue = "") String name,
			Model model) {
		// リクエストパラメータをもとにCategoryクラスをインスタンス化
		Category category = new Category(name);
		// カテゴリーの登録
		categoryRepository.save(category);
		// 画面遷移
		return "redirect:/categories";
	}
	
}
