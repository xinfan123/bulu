package com.xinfan.blueblue.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.SharePreferenceUtil;

public class ViewpagerActivity extends BaseActivity {
	private ViewPager mViewPager;
	private PagerTitleStrip mPagerTitleStrip;
	private ImageView mPageImg;
	private int currIndex = 0;
	private ImageView mPage0;
	private ImageView mPage1;
	private ImageView mPage2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pagertitle);

		mPageImg = (ImageView) findViewById(R.id.page_now);

		mPage0 = (ImageView) findViewById(R.id.page0);
		mPage1 = (ImageView) findViewById(R.id.page1);
		mPage2 = (ImageView) findViewById(R.id.page2);

		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.view1, null);
		View view2 = mLi.inflate(R.layout.view2, null);
		View view3 = mLi.inflate(R.layout.view3, null);

		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);

		final ArrayList<String> titles = new ArrayList<String>();
		titles.add("��");
		titles.add("��");
		titles.add("��");

		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return titles.get(position);
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mViewPager.setAdapter(mPagerAdapter);
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
				if (currIndex == arg0 + 1) {
					animation = new TranslateAnimation(20 * (arg0 + 1), 20 * arg0, 0, 0);
				} /*
				 * else if (currIndex == 2) { animation = new
				 * TranslateAnimation(20*2, 0, 0, 0); }
				 */
				break;
			case 1:
				mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
				if (currIndex == arg0 - 1) {
					animation = new TranslateAnimation(20 * (arg0 - 1), 20 * arg0, 0, 0);

				} else if (currIndex == arg0 + 1) {
					animation = new TranslateAnimation(20 * (arg0 + 1), 20 * arg0, 0, 0);
				}
				break;
			case 2:
				mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
				if (currIndex == arg0 - 1) {
					animation = new TranslateAnimation(20 * (arg0 - 1), 20 * arg0, 0, 0);
				} /*
				 * else if (currIndex == arg0+1) { animation = new
				 * TranslateAnimation(20*(arg0+1), 20*arg0, 0, 0); }
				 */
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			mPageImg.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	public void startbutton(View v) {
		
		SharePreferenceUtil util = new SharePreferenceUtil(ViewpagerActivity.this, Constants.USER_INFO);
		util.setIsFirst(false);

		Intent intent = new Intent(ViewpagerActivity.this, WelcomeActivity.class);
		startActivity(intent);
		ViewpagerActivity.this.finish();
		
	}

}
