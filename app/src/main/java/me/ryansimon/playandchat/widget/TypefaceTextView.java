package me.ryansimon.playandchat.widget;

import me.ryansimon.playandchat.R;
import me.ryansimon.playandchat.util.TypefaceUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Ryan Simon
 *  
 * Use custom fonts with a TextView; defaults to OpenSans Regular
 */
public class TypefaceTextView extends TextView {

    public TypefaceTextView(Context context) {
        this(context, null, R.attr.typefacedTextViewStyle);
    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.typefacedTextViewStyle);
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (!isInEditMode()) {
            final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TypefacedView, defStyle, R.style.TypefaceTextViewStyle);
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

    public void setOpenSansSemiBoldTypeface() { setCustomTypeface("OpenSans-Semibold"); }

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
