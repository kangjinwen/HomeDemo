package ml.mrkang.homedemo.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import ml.mrkang.homedemo.R;
import ml.mrkang.homedemo.View.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity  {
    private int[] images = { R.drawable.pho1, R.drawable.pho2, R.drawable.pho3,
            R.drawable.pho4};
    private String[] titles={"1","2","3","4"};
    List<Integer> phos=new ArrayList<>();
    List<String> tits=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTranslucentStatus(this);

        for(int res:images){
            phos.add(res);
        }
        tits.add("1");
        tits.add("2");
        tits.add("3");
        tits.add("4");
        setContentView(R.layout.activity_home);
        Banner banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(phos);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(tits);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //点击监听，索引从0开始
        banner.setOnBannerListener(new OnBannerListener() {
            Intent intent=new Intent();
            @Override
            public void OnBannerClick(int position) {
                switch (position){
                    case 0:
                        intent.setClass(HomeActivity.this, Banner1.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(HomeActivity.this, Banner2.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
    public static void setTranslucentStatus(Activity activity){
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
