
package com.spring.boot.micro;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service("reviewsService")
public class ReviewsService {
	@Autowired
	private ReviewsRepository reviewsRepository;
	
	public Reviews getItem(int id) {
		return this.reviewsRepository.getOne(id);
	}
	
	public List<Reviews> getItemByProductId(String id) {
		return this.reviewsRepository.findReviewByProductId(id);
	}
	
	public List<Reviews> getAll() {
		return this.reviewsRepository.findAll();
	}

	public void updateItem(int id, Reviews item) {
	   item.setId(id);
	   this.reviewsRepository.save(item);
	}

	public void createItem(Reviews item) {
		this.reviewsRepository.save(item);
	}

	public void deleteItem(int id) {
		this.reviewsRepository.deleteById(id);
	}
}
