package com.mdv.curso.review.infrastructure.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import com.mdv.curso.review.infrastructure.QueryReviewRepository;
import com.mdv.curso.review.infrastructure.ReviewEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ReviewsSeeder implements CommandLineRunner {

    private final QueryReviewRepository repository;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {

        if (repository.count() == 0) {
            ObjectMapper objectMapper = new ObjectMapper();

            Resource resource = resourceLoader.getResource("classpath:reviews.json");

            List<ReviewEntity> newReviews = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
            repository.saveAll(newReviews);
        }
    }
}
