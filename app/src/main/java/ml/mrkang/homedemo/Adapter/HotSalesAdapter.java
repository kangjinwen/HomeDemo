package ml.mrkang.homedemo.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import ml.mrkang.homedemo.HotsalesBean;
import ml.mrkang.homedemo.R;

/**
 * Created by Administrator on 2017/5/4.
 */

public class HotSalesAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<HotsalesBean> hotSalesList;
    public HotSalesAdapter(Context context, List list){
        this.mInflater = LayoutInflater.from(context);
        this.hotSalesList=list;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder=new ViewHolder();
            convertView = mInflater.inflate(R.layout.hotsales_item, null);
            holder.img1 = (ImageView)convertView.findViewById(R.id.hotsales_img1);
            holder.dec1 = (TextView)convertView.findViewById(R.id.hotsales_dec1);
            holder.img2 = (ImageView)convertView.findViewById(R.id.hotsales_img1);
            holder.dec2 = (TextView)convertView.findViewById(R.id.hotsales_dec1);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
//        holder.img1.setImageResource(hotSalesList.get(position).getImageView());
        holder.img1.setImageResource(hotSalesList.get(position).getImageViewID());
        holder.dec1.setText(hotSalesList.get(position).getDec());
        return convertView;
    }
    public class ViewHolder{
        public ImageView img1;
        public ImageView img2;
        public TextView dec1;
        public TextView dec2;
    }
}
