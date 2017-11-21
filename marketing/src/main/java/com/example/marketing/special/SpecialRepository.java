package com.example.marketing.special;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpecialRepository extends MongoRepository<Special, String> {

	List<Special> findByItemIdAndBatchSizeLessThanEqual(long itemId, int batchSize);

	List<Special> findByItemId(long itemId);

}
