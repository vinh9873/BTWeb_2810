package vn.ute.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import vn.ute.entity.CategoryEntity;
import vn.ute.repository.CategoryRepository;
import vn.ute.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Optional<CategoryEntity> findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public Page<CategoryEntity> findByNameContaining(String name, Pageable pageable) {
		return categoryRepository.findByNameContaining(name, pageable);
	}


	@Override
	public <S extends CategoryEntity> S save(S entity) {
		if(entity.getCategoryId() == null) {
			return categoryRepository.save(entity);
		} else {
			Optional<CategoryEntity> opt = findById(entity.getCategoryId());
			if(opt.isPresent()) {
				if(StringUtils.isEmpty(entity.getName())) {
					entity.setName(opt.get().getName());
				} else {
					// Lấy lại images cũ.
					entity.setName(entity.getName());
				}
			}
			return categoryRepository.save(entity);
		}
	}

	@Override
	public List<CategoryEntity> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}

	@Override
	public Page<CategoryEntity> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public List<CategoryEntity> findAllById(Iterable<Long> ids) {
		return categoryRepository.findAllById(ids);
	}

	@Override
	public Optional<CategoryEntity> findById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void delete(CategoryEntity entity) {
		categoryRepository.delete(entity);
		
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
		
	}

	@Override
	public List<CategoryEntity> findByNameContaining(String name) {
		return categoryRepository.findByNameContaining(name);
	}

	@Override
	public List<CategoryEntity> findAll() {
		return categoryRepository.findAll();
	}

}
