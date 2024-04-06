package com.fpt.mtatmilkteaapi.seed;

import com.fpt.mtatmilkteaapi.entity.SizeOption;
import com.fpt.mtatmilkteaapi.repository.IProductRepository;
import com.fpt.mtatmilkteaapi.repository.ISizeOptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Arrays;

@Component
public class SeedSizeOptionsTable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeedSizeOptionsTable.class);

    private static IProductRepository productRepository;
    private static ISizeOptionRepository sizeOptionRepository;

    public SeedSizeOptionsTable(IProductRepository productRepository, ISizeOptionRepository sizeOptionRepository) {
        this.productRepository = productRepository;
        this.sizeOptionRepository = sizeOptionRepository;
    }

    public static void insertData() throws ParseException {
        long count = sizeOptionRepository.count();
        if(count == 0) {

            // Insert SizeOptions
            SizeOption sizeOption1 = new SizeOption(1 , "Normal size", 0);
            SizeOption sizeOption2 = new SizeOption(2 , "Medium size", 20000);
            SizeOption sizeOption3 = new SizeOption(3 , "Big size ice", 25000);
            SizeOption sizeOption4 = new SizeOption(4 , "Small size", 10000);

            sizeOptionRepository.saveAll(Arrays.asList(
                    sizeOption1, sizeOption2, sizeOption3, sizeOption4
            ));

            LOGGER.info("SizeOptions Table Seeded.");
        } else {
            LOGGER.trace("SizeOptions Seeding Not Required.");
        }
    }

}
