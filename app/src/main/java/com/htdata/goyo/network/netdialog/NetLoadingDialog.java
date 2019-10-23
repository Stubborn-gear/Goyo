package com.htdata.goyo.network.netdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;

/**
 * Created by Administrator on 2019/1/15.
 */

public class NetLoadingDialog extends Dialog {


//    @BindView(R.id.net_loading)
//    ImageView netLoading;
//    @BindView(R.id.net_msg)
//    TextView netMsg;

    private Context mContext ;


    private static NetLoadingDialog netLoadingDialog ;

    private NetLoadingDialog(@NonNull Context context) {
        super(context/*, R.style.loading_dialog*/);
        mContext = context ;
    }

    public static NetLoadingDialog getInstance(Context context ){
        if (netLoadingDialog == null){
            synchronized (NetLoadingDialog.class){
                if (netLoadingDialog == null){
                    netLoadingDialog = new NetLoadingDialog(context);
                }
            }
        }
        return netLoadingDialog ;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_net_loading_dialog);
//        ButterKnife.bind(this);
//        init();
    }

/*
    private void init() {
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        Glide.with(mContext).load(R.drawable.icon_gif2).asGif().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.img_loading_w_0).into(netLoading);
//        initAnimation();
    }

    private void initAnimation() {
        RotateAnimation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.reset();
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        animation.setDuration(550);//设定转一圈的时间
        animation.setRepeatCount(Animation.INFINITE);//设定无限循环
        animation.setRepeatMode(Animation.RESTART);
        animation.start();
        if (animation != null) {
            netLoading.startAnimation(animation);
        }
    }

    public void showDialog() {
        LogUtils.v("=======当前界面========="+ActManager.getAppManager().currentActivity());
        show();
    }

    public void dismissDialog() {
        dismiss();
    }
*/


}
