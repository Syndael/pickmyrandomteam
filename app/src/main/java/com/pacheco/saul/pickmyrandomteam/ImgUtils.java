package com.pacheco.saul.pickmyrandomteam;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImgUtils {



    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(res, resId, options);
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
