package me.ryansimon.playandchat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

import me.ryansimon.playandchat.R;
import me.ryansimon.playandchat.util.TypefaceUtil;

/**
 * @author Ryan Simon
 *
 * Use custom fonts with an EditText; defaults to OpenSans Regular
 */
public class TypefaceEditText extends EditText {
    public TypefaceEditText(Context context) {
        this(context, null, R.attr.typefacedEditTextViewStyle);
    }

    public TypefaceEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.typefacedEditTextViewStyle);
    }

    public TypefaceEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (!isInEditMode()) {
            final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TypefacedView, defStyle, R.style.TypefaceEditTextStyle);
            if (array != null) {
                final String typeface = array.getString(R.styleable.TypefacedView_typeface);
                array.recycle();
                if (null != typeface && typeface.length() > 0) {
                    setCustomTypeface(typeface);
                    return;
                }
            }
            setOpenSansRegularTypeface();
        }
    }

    public void setOpenSansLightTypeface() {
        setCustomTypeface("OpenSans-Light");
    }

    public void setOpenSansRegularTypeface() {
        setCustomTypeface("OpenSans-Regular");
    }

    public void setOpenSansBoldTypeface() {
        setCustomTypeface("OpenSans-Bold");
    }

    public void setOpenSansExtraBoldTypeface() {
        setCustomTypeface("OpenSans-ExtraBold");
    }

    public void setOpenSansMediumTypeface() {
        setCustomTypeface("OpenSans-SemiBold");
    }

    public void setCustomTypeface(String typeface) {
        TypefaceUtil.setTypeface(typeface + ".ttf", this);
    }
}

