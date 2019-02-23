package net.alexblass.chess.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import net.alexblass.chess.base.R;

public class DisplaySizeUtil {
    public static int convertDpToPx(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int convertPxToDp(Context context, float px) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) px / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int getScreenShortSide(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int screenHeight = metrics.heightPixels - (getActionBarHeight(context) + getStatusBarHeight(context));
        int screenWidth = metrics.widthPixels;
        return (screenHeight < screenWidth) ? screenHeight : screenWidth;
    }

    private static int getActionBarHeight(Context context) {
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        TypedArray a = context.obtainStyledAttributes(new TypedValue().data, textSizeAttr);
        int height = a.getDimensionPixelSize(0, 0);
        a.recycle();
        return height;
    }

    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
