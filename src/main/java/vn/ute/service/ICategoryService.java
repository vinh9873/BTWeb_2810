package vn.ute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import vn.ute.entity.CategoryEntity;

public interface ICategoryService {

	void deleteById(Long id);
	long count();
	Optional<CategoryEntity> findById(Long id);
	List<CategoryEntity> findAllById(Iterable<Long> ids);
	Page<CategoryEntity> findAll(Pageable pageable);
	List<CategoryEntity> findAll(Sort sort);
	List<CategoryEntity> findAll();
	<S extends CategoryEntity> S save(S entity);
	Page<CategoryEntity> findByNameContaining(String name, Pageable pageable);
	Optional<CategoryEntity> findByName(String name); 
	void delete(CategoryEntity entity);
	void deleteAll();
	List<CategoryEntity> findByNameContaining(String name);

}
