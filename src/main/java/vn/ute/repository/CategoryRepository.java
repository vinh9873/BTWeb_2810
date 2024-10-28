package vn.ute.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ute.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	
	Optional<CategoryEntity> findByName (String name);
     //Tìm kiếm nội dung theo tên
	 List<CategoryEntity> findByNameContaining(String name);
	 
	 //Tìm kiếm và phân trang
	 Page<CategoryEntity> findByNameContaining(String name, Pageable pageable);
	
	
}
