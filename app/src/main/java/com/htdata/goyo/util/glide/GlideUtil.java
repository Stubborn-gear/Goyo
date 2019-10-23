package com.htdata.goyo.util.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

/**
 * cuibo
 * 2018/4/23 10:11
 */

public class GlideUtil {

    //placeholder 设置资源加载过程中的占位Drawable。
    public static void loadImage(Context context, String url, ImageView iv, int defaluteImg) {
        //原生 API
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaluteImg).into(iv);
    }

    // 加载网络图片（GIF）
    public static void loadGifImage(Context context, String url, ImageView iv, int defaluteImg) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                //.placeholder(R.mipmap.ic_launcher_round)
                .error(android.R.drawable.stat_notify_error)
                .priority(Priority.HIGH)
                //.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);


        Glide.with(context).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                ((GifDrawable) resource).setLoopCount(1);
                return false;
            }
        }).apply(options).into(iv);
//        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaluteImg).into(iv);
    }

    // 加载网络图片（圆形）
    public static void loadCircleImage(final Context context, String url, final ImageView iv, int defaluteImg) {
//        Glide.with(context).load(url).placeholder(defaluteImg).transform(new GlideRoundTransform(context)).into(iv);

//        Glide.with(context).load(url).asBitmap().placeholder(defaluteImg).centerCrop().into(new BitmapImageViewTarget(iv) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                iv.setImageDrawable(circularBitmapDrawable);
//            }
//        });
    }

    //加载网络图片（圆角）
    public static void loadRoundCornerImage(Context context, String url, ImageView iv, int defaluteImg, int jiaodu) {
        Glide.with(context).load(url).placeholder(defaluteImg).transform(new GlideRoundTransform(context, jiaodu)).into(iv);
    }

    /**
     * 加载本地  图片地址
     */
    public static void loadImage(Context context, final File file, final ImageView imageView) {
        Glide.with(context)
                .load(file)
                .into(imageView);
    }

    /**
     * 加载本地 drawable
     */
    public static void loadDrawable(Context context, final int resourceId, final ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .into(imageView);
    }

    /**
     * 本地 drawable（圆形）
     */
    public static void loadCircleDrawable(final Context context, int resourceId, final ImageView iv) {
//        Glide.with(context).load(resourceId).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                iv.setImageDrawable(circularBitmapDrawable);
//            }
//        });
    }

    /**
     * 加载本地 drawable（圆角）
     */
    public static void loadRoundCornerDrawable(Context context, int resourceId, ImageView iv, int jiaodu) {
        Glide.with(context).load(resourceId).transform(new GlideRoundTransform(context, jiaodu)).into(iv);
    }

    /**
     * 本地 drawable (圆角)
     */
    public static void loadDrawableFillet(Context context, int resourceId, ImageView imageView, int jiaodu) {
        Glide.with(context).load(resourceId).placeholder(0).transform(new GlideRoundTransform(context, jiaodu)).into(imageView);
    }

    /**
     * 本地 带边框的圆形
     *
     * @param context
     * @param resourceId
     * @param imageView
     * @param defaluteImg
     */
    public static void loadDrawableWithBorder(Context context, int resourceId, ImageView imageView, int defaluteImg) {
        GlideCircleTransformWithBorder transformWithBorder = new GlideCircleTransformWithBorder
                (context, 4, Color.parseColor("#ccFFFFFF"));
        Glide.with(context).load(resourceId).placeholder(defaluteImg).centerCrop().transform(transformWithBorder).into(imageView);
    }



    private <T> String getll(T  s){

        return s.toString() ;
    }

}
