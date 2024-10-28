package vn.ute.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.ute.entity.CategoryEntity;
import vn.ute.models.CategoryModel;
import vn.ute.service.ICategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
 
	@Autowired
	ICategoryService categoryService;
	
	
	@GetMapping("add")
	public String add(Model model) {
		
		CategoryModel cateModel = new CategoryModel();
		cateModel.setIsEdit(false);
		//Chuyển dữ liệu từ model vào biến category để đưa lên view
		model.addAttribute("category",cateModel);
		return "admin/categories/addOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("category") CategoryModel cateModel, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/categories/addOrEdit");
		}
		CategoryEntity entity = new CategoryEntity();
		//Copy từ Model sang Entity
		BeanUtils.copyProperties(cateModel, entity);
		// Gọi hàm save trong service
		categoryService.save(entity);
		// Đưa thông báo về cho biến message
		String message = "";
		if(cateModel.getIsEdit() == true) {
			message = "Category is Edited!";
		} else {
			message = "Category is saved!";
		}
		model.addAttribute("message",message);
		//Redirect về URL controller
		return new ModelAndView("forward:/admin/categories/searchpaginated", model);
	}
	
	@RequestMapping("")
	public String list(ModelMap model) {
		List<CategoryEntity> list = categoryService.findAll();
		model.addAttribute("categories",list);
		return "admin/categories/list";
	}
	 @GetMapping("edit/{categoryId}")
	 public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {
		 Optional<CategoryEntity> optCategory = categoryService.findById(categoryId);
			CategoryModel cateModel =  new CategoryModel();
			// Kiểm tra sự tồn tại của category
			if(optCategory.isPresent()) {
				CategoryEntity entity = optCategory.get();
				// Copy từ entity sang cateModel
				BeanUtils.copyProperties(entity, cateModel);
				cateModel.setIsEdit(true);
				// Đẩy dữ liệu ra view
				model.addAttribute("category", cateModel);
				return new ModelAndView("admin/categories/addOrEdit",model);
			}
			model.addAttribute("message","Category is not existed!");
			return new ModelAndView("forward:/admin/categories",model);
	 }
	 
	 @GetMapping("delete/{id}")
		public ModelAndView delete(ModelMap model, @PathVariable("id") Long categoryId) {
			categoryService.deleteById(categoryId);
			model.addAttribute("message", "Category is deleted!");
			return new ModelAndView("forward:/admin/categories", model);
		}
	 
	 @GetMapping("search")
		public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
			List<CategoryEntity> list = null;
			if(StringUtils.hasText(name)) {
				list = categoryService.findByNameContaining(name);
			} else {
				list = categoryService.findAll();
			}
			model.addAttribute("categories", list);
			return "admin/categories/search";
		}
}
