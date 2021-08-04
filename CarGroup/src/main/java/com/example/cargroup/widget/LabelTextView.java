package com.example.cargroup.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cargroup.R;

public class LabelTextView extends FrameLayout {

    private CharSequence mLabelText= null;
    private CharSequence mContentText= null;
    private TextView mLabelTv,mContentTv;
    public LabelTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelText);
        mLabelText = typedArray.getText(R.styleable.LabelText_labelText);
        mContentText = typedArray.getText(R.styleable.LabelText_contentText);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.layout_label_textview, this);
        mLabelTv = view.findViewById(R.id.mLabelTv);
        mContentTv = view.findViewById(R.id.mContentTv);
        mLabelTv.setText(mLabelText);
        mContentTv.setText(mContentText);
    }

    public void setContentText(String mContentText) {
        this.mContentText = mContentText;
        mContentTv.setText(this.mContentText);
    }
}
