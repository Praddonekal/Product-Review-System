package com.spring.boot.micro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer>{

	@Query("SELECT r FROM Reviews r WHERE r.ProductId = :id")
	List<Reviews> findReviewByProductId(@Param("id")String Id);
}
