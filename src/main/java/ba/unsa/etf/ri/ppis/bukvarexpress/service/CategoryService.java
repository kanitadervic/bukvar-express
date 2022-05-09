package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.CategoryEntity;
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

    private Category toModel(CategoryEntity category) {
        return Category
                .builder()
                .name(category.getName())
                .build();
    }
}
