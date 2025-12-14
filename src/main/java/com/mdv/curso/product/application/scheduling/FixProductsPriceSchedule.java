package com.mdv.curso.product.application.scheduling;

import com.mdv.curso.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixProductsPriceSchedule {

    private final ProductRepository productRepository;

    @Scheduled(fixedRate = 10000)
    public void fixProductsPriceSchedule() {
        log.info("Fixing products price schedule");

        productRepository.findAll().forEach(product ->{
                if (product.getPrice() != null){
                    product.setPrice(product.getPrice() * 1.1);
                    productRepository.upsert(product);
                }
        });

        log.info("Finished fixing products price schedule");
    }

}
