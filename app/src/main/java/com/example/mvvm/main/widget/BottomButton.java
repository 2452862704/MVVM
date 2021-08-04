package com.example.mvvm.main.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.example.loadbitmap.LoadImage;
import com.example.mvvm.R;
import com.example.mvvm.main.data.Api;

public class BottomButton extends RelativeLayout implements View.OnClickListener {

    private int nomalImg = 0;//默认未选中图片id
    private int selImg = 0;//选中图片id
    private String nomalURLImg = "";//默认未选中图片id
    private String selURLImg = "";//选中图片id
    private int nomalColor = 0;//默认文字颜色
    private int selColor = 0;//选中文字颜色
    private String content;//设置底部显示文字
    private boolean showPoint = false;//是否显示小红点标志
    private boolean selFlag = false;//当前控件是否被选中标志
    private LinearLayout linearLayout;
    private ImageView img;
    private TextView contentTv;
    private TextView numTv;
    private SelectListener listener;

    public BottomButton setListener(SelectListener listener) {
        this.listener = listener;
        return this;
    }

    public BottomButton setNomalURLImg(String nomalURLImg) {
        if (nomalURLImg == null)
            return this;
        this.nomalURLImg = nomalURLImg;
        if (!selFlag)
            LoadImage.loadImg(Api.IMAGEFILE+this.nomalURLImg,img);
        return this;
    }

    public BottomButton setSelURLImg(String selURLImg) {
        if (selURLImg == null)
            return this;
        this.selURLImg = selURLImg;
        if (selFlag)
            LoadImage.loadImg(Api.IMAGEFILE+ this.selURLImg,img);
        return this;
    }

    public BottomButton setContent(String content) {
        this.content = content;
        if (this.content.isEmpty())
            return this;
        contentTv.setVisibility(VISIBLE);
        contentTv.setText(content);
        return this;
    }

    public BottomButton setNomalImg(int nomalImg) {
        this.nomalImg = nomalImg;
        if (!selFlag)
            img.setImageResource(this.nomalImg);
        return this;
    }

    public BottomButton setSelImg(int selImg) {
        this.selImg = selImg;
        if (selFlag)
            img.setImageResource(this.selImg);
        return this;
    }

    public BottomButton setNomalColor(int nomalColor) {
        this.nomalColor = nomalColor;
        if (!selFlag)
            contentTv.setTextColor(this.nomalColor);
        return this;
    }

    public BottomButton setSelColor(int selColor) {
        this.selColor = selColor;
        if (selFlag)
            contentTv.setTextColor(this.selColor);
        return this;
    }

    public BottomButton setShowPoint(boolean showPoint) {
        this.showPoint = showPoint;
        if (this.showPoint)
            numTv.setVisibility(VISIBLE);
        else
            numTv.setVisibility(GONE);
        return this;
    }

    public BottomButton(Context context) {
        super(context);
        selFlag = false;
        init();
    }

    public BottomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.bottom_arrs);
//        typedArray.getResourceId()
        selFlag = typedArray.getBoolean(R.styleable.bottom_arrs_selflag,false);
        typedArray.recycle();//释放TypedArray对象->内存泄漏
        init();
    }

    public BottomButton setSelFlag(boolean selFlag) {
        this.selFlag = selFlag;
        return this;
    }

    private void init(){
        setPadding(SizeUtils.dp2px(10),
                SizeUtils.dp2px(10),
                SizeUtils.dp2px(10),
                SizeUtils.dp2px(10));
        linearLayout = new LinearLayout(getContext());
        img = new ImageView(getContext());
        contentTv = new TextView(getContext());
        numTv = new TextView(getContext());
        //先组合LinearLayout 下的ImageView 以及TextView
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams imgLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        img.setLayoutParams(imgLp);
        LinearLayout.LayoutParams contentLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        contentTv.setLayoutParams(contentLp);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(img);
        linearLayout.addView(contentTv);
        contentTv.setVisibility(GONE);
        LayoutParams linearLp = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        linearLp.addRule(RelativeLayout.CENTER_IN_PARENT);
        linearLayout.setLayoutParams(linearLp);
        addView(linearLayout);
        LayoutParams numLp = new  LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        numLp.rightMargin = ConvertUtils.dp2px(15);
        numLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        numTv.setLayoutParams(numLp);
        addView(numTv);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        selFlag = true;
        refreshSelect();
        if (listener!=null)
            listener.onSelect(getId());
    }

    public void clearSelect(){
        selFlag = false;
        img.setImageResource(nomalImg);
        contentTv.setTextColor(nomalColor);
    }

    public void selectCheck(){
        selFlag = true;
        img.setImageResource(selImg);
        contentTv.setTextColor(selImg);
    }
    private void refreshSelect(){
        if (selFlag){
            img.setImageResource(selImg);
            setSelURLImg(selURLImg);
            contentTv.setTextColor(selColor);
            numTv.setVisibility(GONE);
        }
    }

    //选中回调接口
    public interface SelectListener{
        void onSelect(int id);
    }

}
