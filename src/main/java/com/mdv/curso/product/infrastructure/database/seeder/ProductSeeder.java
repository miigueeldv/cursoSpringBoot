package com.mdv.curso.product.infrastructure.database.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import com.mdv.curso.product.infrastructure.database.repository.QueryProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ProductSeeder implements CommandLineRunner {

    private final QueryProductRepository repository;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {

        if (repository.count() == 0) {
            ObjectMapper objectMapper = new ObjectMapper();

            Resource resource = resourceLoader.getResource("classpath:data.json");

            List<ProductEntity> newProducts = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
            repository.saveAll(newProducts);
        }
    }
}
