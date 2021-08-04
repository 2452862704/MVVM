package com.example.homegroup.home.viewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.viewpager.widget.ViewPager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.homegroup.R;
import com.example.homegroup.home.adapter.HomeGoodsAdapter;
import com.example.homegroup.home.adapter.HomeMenuAdapter;
import com.example.homegroup.home.data.entity.HomeBannerEntity;
import com.example.homegroup.home.data.entity.HomeGoodsEntity;
import com.example.homegroup.home.data.entity.HomeMenuEntity;
import com.example.homegroup.home.model.HomeModel;
import com.example.mvvmcommon.field.UIField;
import com.example.mvvmcommon.mvvm.viewmodel.BaseViewModel;
import com.example.networkmoudle.entity.BaseEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel<HomeModel> implements
        OnItemClickListener, ViewPager.PageTransformer {

    private int pageNo = 1;
    public BaseQuickAdapter adapter = new HomeGoodsAdapter();
    private int[]menuIcons = {R.drawable.ic_zhibo,R.drawable.ic_diannao,
            R.drawable.ic_shouji,R.drawable.ic_pad,R.drawable.ic_jiaju,
            R.drawable.ic_jiadian,R.drawable.ic_yundong,R.drawable.ic_xiuxian};
    private String[] menuNames = {"直播","电脑","手机","Pad",
            "家居","家电","秒杀","休闲"};
    private String[] menuPathes = {"","","","",
            "","","/homegroup/seckillesactivity",""};
    public List<HomeMenuEntity>menues = new ArrayList<>();
    private String[]homeBanneres={"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=259364631,2307942273&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3765821666,4235344830&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3336810809,2729597475&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg"};
    public List<HomeBannerEntity>homeBannerEntities = new ArrayList<>();
    public List<String>txtBanneres = new ArrayList<>();
    public List<String>hotImges = new ArrayList<>();
    public List<HomeBannerEntity>topEntities = new ArrayList<>();
    private final static float MAXSCALLER = 1f;//最大缩放倍数
    private final static float MINSCALLER = 0.7f;//最小缩放倍数
    public ObservableField<Boolean>refresh = new ObservableField<>(false);
    public ObservableField<Boolean>load = new ObservableField<>(false);

    @Override
    protected void initData() {
        //创建menu数据源
        createMenu();
        //创建banner数据源
        createImgBanner();
        //创建txt banner 数据源
        createTxtBanner();
        //创建热门商品数据源
        createHotImgs();
        //创建首页话题数据源
        createTops();
        Map<String,Object>map = new HashMap<>();
        map.put("categoryId","14");
        map.put("pageNo",pageNo);
        m.requestHomeGoods(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    private void createMenu(){
        for (int i = 0;i < menuIcons.length;i ++){
            menues.add(new HomeMenuEntity(menuIcons[i],menuNames[i],menuPathes[i]));
        }
    }

    private void createImgBanner(){
        for (int i = 0;i < homeBanneres.length;i++){
            homeBannerEntities.add(new HomeBannerEntity(homeBanneres[i],"https://www.baidu.com/"));
        }
    }

    private void createTxtBanner(){
        for (int i = 0;i < 5;i ++){
            txtBanneres.add("item:"+i);
        }
    }

    private void createHotImgs(){
        hotImges.add("https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg");
        hotImges.add("https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg");
        hotImges.add("https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg");
        hotImges.add("https://img11.360buyimg.com/n7/jfs/t4447/301/1238553109/193354/13c7e995/58db19a7N25101fe4.jpg");
        hotImges.add("https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg");
        hotImges.add("https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg");
        hotImges.add("https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg");
    }

    private void createTops(){
        topEntities.add(new HomeBannerEntity("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2069038579,1558413647&fm=26&gp=0.jpg","https://www.baidu.com/"));
        topEntities.add(new HomeBannerEntity("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1975162372,3010774958&fm=26&gp=0.jpg","https://www.baidu.com/"));
        topEntities.add(new HomeBannerEntity("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1034894772,889898861&fm=26&gp=0.jpg","https://www.baidu.com/"));
        topEntities.add(new HomeBannerEntity("http://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png","https://www.baidu.com/"));
        topEntities.add(new HomeBannerEntity("http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg","https://www.baidu.com/"));
    }

    @Override
    protected HomeModel createModel() {
        return new HomeModel();
    }

    @Override
    protected void sucessFull(BaseEntity entity) {
        refresh.set(false);
        if (entity instanceof HomeGoodsEntity){
            HomeGoodsEntity homeGoodsEntity = (HomeGoodsEntity) entity;
            if (homeGoodsEntity.getData()==null){
                showMsg("当前最后一页");
                return;
            }
            if (homeGoodsEntity.getData().size()==0){
                showMsg("当前最后一页");
                return;
            }
            adapter.setNewInstance(homeGoodsEntity.getData());
        }
    }

    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        if (adapter instanceof HomeMenuAdapter){
            HomeMenuAdapter homeMenuAdapter = (HomeMenuAdapter) adapter;
            String path = homeMenuAdapter.getItem(position).pagePath;
            Map<String,Object>map= new HashMap<>();
            map.put(UIField.ACTIONTYPE, UIField.AROUTERSTART);
            map.put(UIField.ACTIONROUTERKEY,path);
            getUiChangeLiveData().getStartActivity().setValue(map);
        }else if (adapter instanceof HomeGoodsAdapter){
            //点击商品列表->商品详情
            HomeGoodsAdapter homeGoodsAdapter = (HomeGoodsAdapter) adapter;
            Bundle bundle = new Bundle();
            bundle.putString("img",homeGoodsAdapter.getItem(position).getGoods_default_icon());
            bundle.putString("title",homeGoodsAdapter.getItem(position).getGoods_desc());
            bundle.putString("dec",homeGoodsAdapter.getItem(position).getGoods_default_sku());
            bundle.putInt("id",homeGoodsAdapter.getItem(position).getId());
            bundle.putString("price",homeGoodsAdapter.getItem(position).getGoods_default_price());
//            bundle.putSerializable("entity",homeGoodsAdapter.getItem(position));
            Map<String,Object>map = new HashMap<>();
            map.put(UIField.ACTIONTYPE,UIField.AROUTERSTART);
            map.put(UIField.ACTIONROUTERKEY,"/homegroup/homevalueactivity");
            map.put(UIField.VALUESKEY,bundle);
            getUiChangeLiveData().getStartActivity().setValue(map);
        }
    }

    public void onLoadMore() {
        load.set(true);
        pageNo += 1;
        Map<String,Object>map = new HashMap<>();
        map.put("categoryId","14");
        map.put("pageNo",pageNo);
        m.requestHomeGoods(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        compositeDisposable.add(d);
                        getUiChangeLiveData().setShowDialog(UIField.SHOWDIALOG+
                                System.currentTimeMillis());
                    }

                    @Override
                    public void onNext(@NotNull BaseEntity entity) {
                        if (entity instanceof HomeGoodsEntity){
                            HomeGoodsEntity homeGoodsEntity = (HomeGoodsEntity) entity;
                            if (homeGoodsEntity.getData()==null){
                                showMsg("当前最后一页");
                                return;
                            }
                            if (homeGoodsEntity.getData().size()==0){
                                showMsg("当前最后一页");
                                return;
                            }
                            adapter.addData(homeGoodsEntity.getData());
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        showMsg(""+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getUiChangeLiveData().setHideDialog(UIField.SHOWDIALOG+
                                System.currentTimeMillis());
                        load.set(false);
                    }
                });
    }

    public void onRefresh() {
        refresh.set(true);
        pageNo = 1;
        Map<String,Object>map = new HashMap<>();
        map.put("categoryId","14");
        map.put("pageNo",pageNo);
        m.requestHomeGoods(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    //page->发生移动的页
    //position->索引
    @Override
    public void transformPage(@NonNull @NotNull View page, float position) {
        if (position<0){
            //上一页
            float scall = (1+position);//当前缩放倍数
            if (scall < MINSCALLER)
                scall = MINSCALLER;
            //设置上一页高度进行缩放
            page.setScaleY(scall);
        }else if (position>=0&&position<1){
            //当前页
            if (position == 0)
                page.setScaleY(MAXSCALLER);
            else{
                float scall = (1-position)+MINSCALLER;
                if (scall > MAXSCALLER)
                    scall = MAXSCALLER;
                page.setScaleY(scall);
            }
        }else {
            //下一页
//            float scall = MAXSCALLER - (position - 1);
//            if (scall < MINSCALLER)
//                scall = MINSCALLER;
//            if (scall == MAXSCALLER)
//                scall = MINSCALLER;
//            Log.e("XSX","scall:"+scall);
            //设置上一页高度进行缩放
            page.setScaleY(MINSCALLER);
        }
    }
}
