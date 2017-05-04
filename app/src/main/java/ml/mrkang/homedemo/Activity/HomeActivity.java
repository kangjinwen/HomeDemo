package ml.mrkang.homedemo.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import ml.mrkang.homedemo.Fragment.ClassifyFragment;
import ml.mrkang.homedemo.Fragment.HomeFragment;
import ml.mrkang.homedemo.R;

public class HomeActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // 去除标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        Fragment home = new HomeFragment();
        Fragment classicfy=new ClassifyFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_frag, home).commit();//加载主页
        getSupportFragmentManager().beginTransaction().add(R.id.main_frag, classicfy).commit();//加载分类


    }
    }

