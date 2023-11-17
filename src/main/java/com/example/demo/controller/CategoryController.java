package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// カテゴリー登録処理
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
	
	// カテゴリー更新画面表示
	@GetMapping("/categories/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		// 更新対象のCategoryクラスのインスタンスを取得
		Category category = categoryRepository.findById(id).get();
		// スコープに 取得したカテゴリインスタンスを登録
		model.addAttribute("category", category);
		// 画面遷移
		return "category/editCategory";
	}
	
	// カテゴリー更新処理
	@PostMapping("/categories/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "name", defaultValue = "") String name,
			Model model) {
		// パスパラメータとリクエストパラメータをもとにCtegoryクラスをインスタンス化
		Category category = new Category(id, name);
		// 更新処理を実行
		categoryRepository.save(category);
		// 画面遷移
		return "redirect:/categories";
	}
	
	// カテゴリー削除処理
	@PostMapping("/categories/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id, 
			Model model) {
		// 削除処理を実行
		categoryRepository.deleteById(id);
		// 画面遷移
		return "redirect:/categories";
	}
}
