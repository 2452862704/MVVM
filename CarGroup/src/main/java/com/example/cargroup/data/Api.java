package com.example.cargroup.data;


import com.example.cargroup.data.entity.CarListEntity;
import com.example.cargroup.data.entity.OrderEntity;
import com.example.cargroup.data.entity.OrderListEntity;
import com.example.cargroup.data.entity.PayEntity;
import com.example.cargroup.data.entity.ShipAddressEntity;
import com.example.cargroup.data.entity.SubCarEntity;
import com.example.networkmoudle.entity.BaseEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    //获取购物车列表
    @POST("cart/getList")
    Observable<CarListEntity>requestCarList(@Body RequestBody body);

    //购物车提交订单
    @POST("cart/submit")
    Observable<SubCarEntity>requestSubCar(@Body RequestBody body);

    // 按状态获取订单列表
    @POST("order/getOrderList")
    Observable<OrderListEntity>requestOrderList(@Body RequestBody body);

    //获取订单详情
    @POST("order/getOrderById")
    Observable<OrderEntity>requestGetOrder(@Body RequestBody body);

    //获取支付宝签名
    @POST("pay/getPaySign")
    Observable<PayEntity>requestPaySign(@Body RequestBody body);

    //取消订单
    @POST("order/cancel")
    Observable<BaseEntity>requestCancelOrder(@Body RequestBody body);

    //确认支付状态
    @POST("order/pay")
    Observable<BaseEntity>requestOrderPay(@Body RequestBody body);

    //确认购物车生成的订单
    @POST("order/submitOrder")
    Observable<BaseEntity>requestSubOrder(@Body RequestBody body);

    //确认收货
    @POST("order/confirm")
    Observable<BaseEntity>requestConfirm(@Body RequestBody body);

    //收货地址列表
    @POST("shipAddress/getList")
    Observable<ShipAddressEntity>requestAddressList(@Body RequestBody body);

    //添加收货地址
    @POST("shipAddress/add")
    Observable<BaseEntity>requestAddAddress(@Body RequestBody body);

    //修改收货地址
    @POST("shipAddress/modify")
    Observable<BaseEntity>requestUpdateAddress(@Body RequestBody body);

    //删除收货地址
    @POST("shipAddress/delete")
    Observable<BaseEntity>requestDelAddress(@Body RequestBody body);
}
