package ml.mrkang.homedemo.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.loror.lororUtil.convert.DpPxUtil;
import com.loror.lororUtil.view.Find;
import com.loror.lororUtil.view.ViewUtil;
import com.loror.lororUtil.view.banner.BannerViewPager;
import com.loror.lororUtil.view.banner.PointView;
import com.loror.lororUtil.view.banner.ViewPagerBannerAdapter;

import ml.mrkang.homedemo.R;

public class HomeFragment extends Fragment {
    @Find(R.id.main_banner)
    private BannerViewPager banner;
    @Find(R.id.main_point)
    private PointView point;

    private int[] res = {R.mipmap.banner, R.mipmap.banner1};//banner要显示的图片

    class Adapter extends ViewPagerBannerAdapter {
        public Adapter(Context context) {
            super(context);
        }

        @Override
        public void onItemClick(int index) {
            //点击该页时回调
        }

        @Override
        public int getItemCount() {
            return res.length;
        }

        @Override
        public void onViewSwitched(View view, int position) {
            //切换到该页时回调，可以在这里加载图片
            ImageView imageView = (ImageView) view.findViewById(R.id.item_img);
            imageView.setImageResource(res[position]);
        }

        @Override
        public View getView(LayoutInflater inflater, int index) {
            View view = inflater.inflate(R.layout.banner_item, null);
            if (index == 0) {
                ImageView imageView = (ImageView) view.findViewById(R.id.item_img);
                imageView.setImageResource(res[index]);
            }//加载首页
            return view;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     */
    protected void initView(View parent) {
        ViewUtil.find(this, parent);
        banner.setAnimationSpeed(400);//设置动画时长，单位毫秒
        banner.setChangePeroid(1500);//设置页面切换间隔
        if (res.length > 1) {
            banner.startScrol();//开始滚动
        }
        banner.setAdapter(new Adapter(getContext()));
        point.setForeColor(Color.WHITE);//设置选中点颜色
        point.setCount(res.length);//设置总共几个点
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DpPxUtil.Dp2Px(getContext(), 10 * res.length), DpPxUtil.Dp2Px(getContext(), 8));
        point.setLayoutParams(params);
        banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                point.setIndex(position % res.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (banner != null && res.length > 1) {
            banner.startScrol();//恢复滚动
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null) {
            banner.pauseScrol();//暂停滚动
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (banner != null) {
            banner.stopScrol();//停止滚动
        }
    }
}
