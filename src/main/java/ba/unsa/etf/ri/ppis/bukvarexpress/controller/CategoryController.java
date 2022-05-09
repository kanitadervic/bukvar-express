package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.Category;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
