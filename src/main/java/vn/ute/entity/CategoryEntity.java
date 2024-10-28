package vn.ute.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class CategoryEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	@Column(name = "category_name", length = 200, columnDefinition = "nvarchar(200) not null")
    private String name;
	
//	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//	private Set<ProductEntity> products;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	
}