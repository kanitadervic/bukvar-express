package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.Category;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getAll();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category newCategory) {
        Category category = categoryService.addCategory(newCategory);

        return ResponseEntity.ok(category);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category newCategory) {
        if (newCategory.getId() == null) {
            return null;
        }
        Category category = categoryService.getCategoryById(newCategory.getId());
        if (category == null) {
            return null;
        }

        Category updatedCategory = categoryService.updateCategory(newCategory);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCategory(@RequestParam Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            return null;
        }
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok("Successfully deleted a category!");
    }
}
