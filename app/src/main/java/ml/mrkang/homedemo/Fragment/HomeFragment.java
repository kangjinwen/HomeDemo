package ml.mrkang.homedemo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loror.lororUtil.convert.DpPxUtil;
import com.loror.lororUtil.view.Find;
import com.loror.lororUtil.view.ViewUtil;
import com.loror.lororUtil.view.banner.BannerViewPager;
import com.loror.lororUtil.view.banner.PointView;
import com.loror.lororUtil.view.banner.ViewPagerBannerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import ml.mrkang.homedemo.Activity.Banner1;
import ml.mrkang.homedemo.Activity.Banner2;
import ml.mrkang.homedemo.Activity.HomeActivity;
import ml.mrkang.homedemo.Adapter.GalleryAdapter;
import ml.mrkang.homedemo.Adapter.HotSalesAdapter;
import ml.mrkang.homedemo.HotsalesBean;
import ml.mrkang.homedemo.R;
import ml.mrkang.homedemo.View.GlideImageLoader;


public class HomeFragment extends Fragment {
    @Find(R.id.banner)
    private BannerViewPager banner;
    @Find(R.id.main_point)
    private PointView point;
    private Intent intent;
    private int[] res = {R.mipmap.banner, R.mipmap.banner1};//banner要显示的图片
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private List<HotsalesBean> mhotsalesBeen;
    private HotSalesAdapter mhotSalesAdapter;
    PullToRefreshListView pullToRefreshView;
    //    private HeaderAndFooterAdapter headerAndFooterAdapter;
    class Adapter extends ViewPagerBannerAdapter {
        public Adapter(Context context) {
            super(context);
        }

        @Override
        public void onItemClick(int index) {
            //点击该页时回调
            intent = new Intent();
            switch (index) {
                case 0:
                    intent.setClass(getActivity(), Banner1.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent.setClass(getActivity(), Banner2.class);
                    startActivity(intent);
                    break;
            }
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
//        mQuickAdapter.addHeaderView(getView());
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //实例化header
        View headerlayout=LayoutInflater.from(getContext()).inflate(R.layout.headerlayout,null);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //实例化刷新组件
        pullToRefreshView = (PullToRefreshListView)view.findViewById(R.id.main_refresh);

        //上下拉都刷新
        pullToRefreshView.setMode(PullToRefreshBase.Mode.BOTH);
        //添加header
        pullToRefreshView.getRefreshableView().addHeaderView(headerlayout);
        initData();
        initView(view);
        //得到国际进口馆控件
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview_horizontal);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(getContext(), mDatas);
        mhotSalesAdapter=new HotSalesAdapter(getContext(),mhotsalesBeen);
        mRecyclerView.setAdapter(mAdapter);
        pullToRefreshView.setOnPullEventListener(new PullToRefreshBase.OnPullEventListener<ListView>() {
            @Override
            public void onPullEvent(PullToRefreshBase<ListView> refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {
                mhotSalesAdapter=new HotSalesAdapter(getContext(),mhotsalesBeen);
            }
        });
        pullToRefreshView.setAdapter(mhotSalesAdapter);
        //国际进口馆点击跳转功能
        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position+"", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        return view;
    }

    protected void initData() {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable._chile,
                R.drawable._australia, R.drawable._france, R.drawable._spain, R.drawable._australia));
        mhotsalesBeen=new ArrayList<>();
        mhotsalesBeen.add(new HotsalesBean("非常好的红酒",R.drawable._chile));
        mhotsalesBeen.add(new HotsalesBean("非常坏的红酒",R.drawable._france));
        mhotsalesBeen.add(new HotsalesBean("非常好的红酒",R.drawable._spain));
        mhotsalesBeen.add(new HotsalesBean("非常好的红酒",R.drawable._spain));
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
