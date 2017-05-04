package ml.mrkang.homedemo;

import android.widget.ImageView;

/**
 * Created by Administrator on 2017/5/4.
 */

public class HotsalesBean {
    String dec;
    ImageView imageView;

    public HotsalesBean(String dec, ImageView imageView, int imageViewID) {
        this.dec = dec;
        this.imageView = imageView;
        this.imageViewID = imageViewID;
    }

    public HotsalesBean(String dec, int imageViewID) {
        this.dec = dec;
        this.imageViewID = imageViewID;
    }

    public void setDec(String dec) {

        this.dec = dec;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setImageViewID(int imageViewID) {
        this.imageViewID = imageViewID;
    }

    int imageViewID;

    public int getImageViewID() {
        return imageViewID;
    }

    public String getDec() {
        return dec;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
