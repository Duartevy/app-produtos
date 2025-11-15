package br.com.equipe4.app_produtos.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.equipe4.app_produtos.model.Category;
import br.com.equipe4.app_produtos.repository.CategoryRepository;
import br.com.equipe4.app_produtos.service.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(CategoryDTO dto) {
        Category parent = findParentById(dto.parentId());

        validateNameDuplicity(dto.name(), parent);

        Category newCategory = new Category(dto.name(), parent);
        return categoryRepository.save(newCategory);
    }

    public Optional<Category> update(Long id, CategoryDTO dto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return Optional.empty();
        }

        Category category = optionalCategory.get();
        Category parent = findParentById(dto.parentId());

        if (!category.getName().equals(dto.name()) || category.getParent() != parent) {
            validateNameDuplicity(dto.name(), parent);
        }

        category.setName(dto.name());
        category.setParent(parent);
        return Optional.of(categoryRepository.save(category));
    }

    public boolean delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            return false;
        }

        categoryRepository.deleteById(id);
        return true;
    }

    private Category findParentById(Long parentId) {
        if (parentId == null) {
            return null;
        }

        return categoryRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Categoria 'pai' não encontrada"));
    }

    private void validateNameDuplicity(String name, Category parent) {
        if (categoryRepository.findByNameAndParent(name, parent).isPresent()) {
            throw new RuntimeException("Já existe uma categoria com esse nome nesse nível");
        }
    }

}
