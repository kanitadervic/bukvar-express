package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.CategoryEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Book;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Category;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        return categoryEntities.stream().map(this::toModel).toList();
    }

    public Category getCategoryById(Long categoryId) {
        return toModel(categoryRepository.getById(categoryId));
    }

    public Category addCategory(Category category) {
        CategoryEntity categoryEntity = toEntity(category);

        return toModel(categoryRepository.save(categoryEntity));
    }

    public Category updateCategory(Category category) {
        CategoryEntity categoryEntity = toEntity(category);

        return toModel(categoryRepository.save(categoryEntity));
    }

    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    private Category toModel(CategoryEntity category) {
        return Category
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private CategoryEntity toEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
