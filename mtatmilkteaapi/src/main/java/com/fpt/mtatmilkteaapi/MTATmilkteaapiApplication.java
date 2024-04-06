package com.fpt.mtatmilkteaapi;

import com.fpt.mtatmilkteaapi.seed.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.text.ParseException;

@SpringBootApplication
public class MTATmilkteaapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MTATmilkteaapiApplication.class, args);
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) throws ParseException {

        SeedRolesTable.insertData();
        SeedUsersTable.insertData();
        SeedMemberVipsTable.insertData();

        SeedCategoriesTable.insertData();
        SeedSizeOptionsTable.insertData();
        SeedAddOptionsTable.insertData();
        SeedProductsTable.insertData();
        SeedSaleOffsTable.insertData();

        SeedOrdersTable.insertData();
        SeedOrderDetailsTable.insertData();
        SeedWishlistsTable.insertData();
        SeedCodesTable.insertData();
        SeedVouchersTable.insertData();
        SeedSpinnersTable.insertData();

        SeedWheelHistory.insertData();

//        SeedGroupMemberTable.insertData();
//        SeedGroupOrderDetailsTable.insertData();
    }
}
