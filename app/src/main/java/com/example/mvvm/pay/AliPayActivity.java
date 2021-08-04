package com.example.mvvm.pay;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.LogUtils;
import com.example.cargroup.data.OrderAction;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.Map;

@Route(path = "/app/alipayactivity")
public class AliPayActivity extends AppCompatActivity {

    private PayHandler handler;
    private String alipayStr;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alipayStr =  getIntent().getExtras().getString("alipayStr");
        LogUtils.e("AliPayActivity:"+alipayStr);
        handler = new PayHandler(this);
        payV2();
    }

    public void payV2(){
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(AliPayActivity.this);
                Map<String, String> result = alipay.payV2(alipayStr, true);
                LogUtils.e("msp:"+result.toString());
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public static class PayHandler extends Handler{

        private WeakReference<AliPayActivity>weakReference;

        public PayHandler(AliPayActivity activity){
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                //支付回调
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 * 返回码	含义
                 * 9000	订单支付成功
                 * 8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                 * 4000	订单支付失败
                 * 5000	重复请求
                 * 6001	用户中途取消
                 * 6002	网络连接出错
                 * 6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    LogUtils.e("9000->支付成功");
                    EventBus.getDefault().post(new OrderAction("finishConfirm"));
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                }
                weakReference.get().finish();
            }
        }
    }

}
