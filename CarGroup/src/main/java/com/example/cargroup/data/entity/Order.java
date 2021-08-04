package com.example.cargroup.data.entity;

import java.util.List;

public class Order {
    public Integer id;
    public Integer payType;
    public ShipAddress shipAddress;
    public String totalPrice;
    public Integer orderStatus;
    public List<OrderGoods> orderGoodsList;
}
