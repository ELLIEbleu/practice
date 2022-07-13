package com.mock;


import com.google.common.collect.Lists;

import java.util.List;

public class Shop {
    private List<Seller> sellers = Lists.newArrayList();

    public Shop(Seller seller) {
        sellers.add(seller);
    }

    public Goods buyBread() {
        return new Goods();
    }
}
