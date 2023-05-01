package com.example.extendcomponent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.LocaleListCompat;

public class EditTextWithClear extends AppCompatEditText {
    Drawable mClearButtonImage;

    private void init() {
        mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);


        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String codeLanguage = String.valueOf(LocaleListCompat.getAdjustedDefault());

                if (codeLanguage != "ar_EG"){
                    ShowClearButton();
                }
                else {
                    ShowArabicClearButton();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String codeLanguage = String.valueOf(LocaleListCompat.getAdjustedDefault());

                if (getCompoundDrawables()[2] != null) {
                    float clearButtonStart = (getWidth()- getPaddingEnd()- mClearButtonImage.getIntrinsicWidth());

                    boolean isClearButtonClicked = false;

                    if (motionEvent.getX() > clearButtonStart) {
                        isClearButtonClicked = true;
                    }

                    if (isClearButtonClicked) {
                        if (motionEvent
                                .getAction() == MotionEvent.ACTION_DOWN) {
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
                            if (codeLanguage!= "ar_EG"){
                                ShowClearButton();
                            }

                            else {
                                ShowArabicClearButton();
                            }

                        }

                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);
                            getText().clear();
                            HideClearButton();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                    return false;
                }
        });

    }


    //untuk extend komponen, perlu generate constructor juga
    public EditTextWithClear(@NonNull Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //menampilkan clear button di sebelah kanan
    private  void ShowClearButton(){
        setCompoundDrawablesWithIntrinsicBounds(null, null, mClearButtonImage, null);
    }

    private void ShowArabicClearButton(){
        setCompoundDrawablesWithIntrinsicBounds(mClearButtonImage, null, null,null);
    }

    private  void HideClearButton(){
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }



}
