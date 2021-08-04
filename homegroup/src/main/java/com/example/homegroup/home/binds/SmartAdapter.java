package com.example.homegroup.home.binds;

import androidx.databinding.BindingAdapter;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

public class SmartAdapter {

    @BindingAdapter(value = {"refreshListener","loadMoreListener"},requireAll = false)
    public static void SmartBind(SmartRefreshLayout smartRefreshLayout,
                                 OnRefreshListener refreshListener,
                                 OnLoadMoreListener loadMoreListener){
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(smartRefreshLayout.getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(smartRefreshLayout.getContext()));
        smartRefreshLayout.setOnRefreshListener(refreshListener);
        smartRefreshLayout.setOnLoadMoreListener(loadMoreListener);
    }

    @BindingAdapter(value = {"refresh","load"},requireAll = false)
    public static void SmartCtrBind(SmartRefreshLayout smartRefreshLayout,
                                    boolean refresh,
                                    boolean load){
        if (refresh)
            smartRefreshLayout.finishRefresh();
        if (load)
            smartRefreshLayout.finishLoadMore();
    }

}
