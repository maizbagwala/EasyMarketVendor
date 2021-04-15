package com.dexterapps.easymarketvendor.welcomeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.dexterapps.easymarketvendor.register.RegisterActivity;
import com.dexterapps.easymarketvendor.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);
        tabIndicator = findViewById(R.id.tab_indicator);
        tvSkip = findViewById(R.id.tv_skip);
        final List<ScreenItem> mList = new ArrayList<>();
        boolean fresh_food = mList.add(new ScreenItem("Fresh Fruits", "Sweets and pie will make you cry, Fruits and Vegetables give you the edge", R.drawable.welcome_screen_1));
        mList.add(new ScreenItem("Fresh Vegetables", "Have fruits & vegetables if you want to lead a fruitful life", R.drawable.welcome_screen_2));
        mList.add(new ScreenItem("Fast Delivery", "Extraordinary Service.", R.drawable.welcome_screen_1));
        // setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);
        tabIndicator.setupWithViewPager(screenPager);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}