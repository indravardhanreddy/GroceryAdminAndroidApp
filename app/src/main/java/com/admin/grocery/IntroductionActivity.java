package com.admin.grocery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.graphics.Typeface;

public class IntroductionActivity extends AppCompatActivity {
	
	
	private Toolbar _toolbar;
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView imageview4;
	private TextView textview1;
	private LinearLayout linearall;
	private LinearLayout linear5;
	private LinearLayout tabslinear;
	private LinearLayout linearalltabs;
	private LinearLayout linear6;
	private LinearLayout base;
	private LinearLayout trash;
	private LinearLayout layout1;
	private LinearLayout layout2;
	private LinearLayout layout3;
	private ImageView imageview1;
	private TextView textview2;
	private TextView textview3;
	private ImageView imageview2;
	private TextView textview4;
	private TextView textview5;
	private ImageView imageview3;
	private TextView textview6;
	private TextView textview7;
	private ImageView dot1;
	private ImageView dot2;
	private ImageView dot3;
	private TextView textviewskip;
	private LinearLayout linear7;
	private LinearLayout linearnext;
	private TextView textviewnext;
	
	private SharedPreferences ref;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.introduction);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview1 = (TextView) findViewById(R.id.textview1);
		linearall = (LinearLayout) findViewById(R.id.linearall);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		tabslinear = (LinearLayout) findViewById(R.id.tabslinear);
		linearalltabs = (LinearLayout) findViewById(R.id.linearalltabs);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		base = (LinearLayout) findViewById(R.id.base);
		trash = (LinearLayout) findViewById(R.id.trash);
		layout1 = (LinearLayout) findViewById(R.id.layout1);
		layout2 = (LinearLayout) findViewById(R.id.layout2);
		layout3 = (LinearLayout) findViewById(R.id.layout3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		textview6 = (TextView) findViewById(R.id.textview6);
		textview7 = (TextView) findViewById(R.id.textview7);
		dot1 = (ImageView) findViewById(R.id.dot1);
		dot2 = (ImageView) findViewById(R.id.dot2);
		dot3 = (ImageView) findViewById(R.id.dot3);
		textviewskip = (TextView) findViewById(R.id.textviewskip);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linearnext = (LinearLayout) findViewById(R.id.linearnext);
		textviewnext = (TextView) findViewById(R.id.textviewnext);
		ref = getSharedPreferences("ref", Activity.MODE_PRIVATE);
		
		textviewskip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		linearnext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (viewPager.getCurrentItem() == 0) {
					viewPager.setCurrentItem(1);
				}
				else {
					if (viewPager.getCurrentItem() == 1) {
						viewPager.setCurrentItem(2);
					}
					else {
						if (viewPager.getCurrentItem() == 2) {
							SketchwareUtil.showMessage(getApplicationContext(), "Started!");
							finish();
						}
						else {
							
						}
					}
				}
			}
		});
	}
	private void initializeLogic() {
		_AdvancedCorners(linearall, "#FFFFFF", "#FFFFFF", 50, 50, 0, 0);
		_AdvancedCorners(linear5, "#FFFFFF", "#FFFFFF", 0, 0, 50, 50);
		android.graphics.drawable.GradientDrawable it = new
		android.graphics.drawable.GradientDrawable();
		it.setColor(Color.parseColor("#FFFFFF"));
		it.setCornerRadius(660);
		linearnext.setBackground(it);
		_setImageViewRipple(dot1, "#434EE8", "#434EE8");
		_setImageViewRipple(dot2, "#EEEEEE", "#EEEEEE");
		_setImageViewRipple(dot3, "#EEEEEE", "#EEEEEE");
		_rippleRoundStroke(textviewskip, "#FFFFFF", "#BDBDBD", 660, 0, "#00000000");
		_rippleRoundStroke(linearnext, "#FFFFFF", "#BDBDBD", 660, 0, "#00000000");
		_viewPager();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	@Override
	protected void onPostCreate(Bundle _savedInstanceState) {
		super.onPostCreate(_savedInstanceState);
		if (!ref.getString("Lang", "").equals("English")) {
			textview1.setText("ఆన్‌లైన్ కిరాణా దుకాణం");
			textview2.setText("ఇంటి వద్దే ఉండి సురక్షితంగా ఉండండి");
			textview3.setText("బయటికి రాకుండా ఉండండి. ఉత్పత్తులను ఆన్‌లైన్‌లో ఆర్డర్ చేయండి. ");
			textview4.setText("ఉత్తమ ధరలకు తాజా ఉత్పత్తులు");
			textview5.setText("ఉత్పత్తులను సరసమైన ధరలకు కొనండి");
			textview6.setText("మేము ఉచితంగా పంపిణీ చేస్తాము. మీ షాపింగ్ ఆనందించండి");
			textview7.setText("కోవిడ్ -19 లాక్ డౌన్ సమయంలో మీ మద్దతుకు ధన్యవాదాలు");
			textviewskip.setText("దాటవేయబడింది");
			textviewnext.setText("తరువాత");
		}
		else {
			textview1.setText("Online Grocery Store");
			textview2.setText("Stay Home and Stay Safe");
			textview3.setText("Avoid coming outside. Order the products online.");
			textview4.setText("Fresh products at best prices ");
			textview5.setText("Buy products at reasonable price ");
			textview6.setText("We deliver free. Enjoy your shopping");
			textview7.setText("Thanks for your support during covid-19 lock down");
			textviewskip.setText("Skip");
			textviewnext.setText("Next");
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fff_tusj_more_block_20190913005821.ttf"), 1);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fff_tusj_more_block_20190913005821.ttf"), 1);
			textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fff_tusj_more_block_20190913005821.ttf"), 1);
			textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fff_tusj_more_block_20190913005821.ttf"), 1);
			textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular.ttf"), 0);
			textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular.ttf"), 0);
			textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular.ttf"), 0);
			textviewnext.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 1);
			textviewskip.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 0);
		}
	}
	private void _AdvancedCorners (final View _view, final String _color1, final String _color2, final double _tl, final double _tr, final double _bl, final double _br) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setStroke(2, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_tl,(int)_tl,(int)_tr,(int)_tr,(int)_bl,(int)_bl,(int)_br,(int)_br});
		
		_view.setBackground(gd);
	}
	
	
	private void _viewPager () {
		viewPager = new androidx.viewpager.widget.ViewPager(this);
		
		viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		MyPagerAdapter adapter = new MyPagerAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
		base.addView(viewPager);
		
		viewPager.addOnPageChangeListener(new androidx.viewpager.widget.ViewPager.OnPageChangeListener() {
			public void onPageSelected(int position) {
				
				if (viewPager.getCurrentItem() == 0) {
					_setImageViewRipple(dot1, "#434EE8", "#434EE8");
					_setImageViewRipple(dot2, "#EEEEEE", "#EEEEEE");
					_setImageViewRipple(dot3, "#EEEEEE", "#EEEEEE");
				}
				else {
					if (viewPager.getCurrentItem() == 1) {
						_setImageViewRipple(dot2, "#434EE8", "#434EE8");
						_setImageViewRipple(dot1, "#EEEEEE", "#EEEEEE");
						_setImageViewRipple(dot3, "#EEEEEE", "#EEEEEE");
					}
					else {
						if (viewPager.getCurrentItem() == 2) {
							_setImageViewRipple(dot3, "#434EE8", "#434EE8");
							_setImageViewRipple(dot2, "#EEEEEE", "#EEEEEE");
							_setImageViewRipple(dot1, "#EEEEEE", "#EEEEEE");
							textviewnext.setText("Start now");
						}
						else {
							
						}
					}
				}
			}
			@Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				
			}
			@Override public void onPageScrollStateChanged(int state) {
				
			}
		});
		
		
		
		tabLayout = new com.google.android.material.tabs.TabLayout(this);
		tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
	}
	
	private class MyPagerAdapter extends androidx.viewpager.widget.PagerAdapter {
		public int getCount() {
			return 3;
		}
		
		@Override public Object instantiateItem(ViewGroup collection, int position) {
			
			LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflater.inflate(R.layout.empty, null);
			
			LinearLayout container = (LinearLayout) v.findViewById(R.id.linear1);
			
			if (position == 0) {
				ViewGroup parent = (ViewGroup) layout1.getParent();
				if (parent != null) {
					parent.removeView(layout1);
				}container.addView(layout1);
				
			} else if (position == 1) {
				ViewGroup parent = (ViewGroup) layout2.getParent();
				if (parent != null) {
					parent.removeView(layout2);
				}
				container.addView(layout2);
				
			} else if (position == 2) {
				ViewGroup parent = (ViewGroup) layout3.getParent();
				if (parent != null) {
					parent.removeView(layout3);
				}
				container.addView(layout3);
			}
			collection.addView(v, 0);
			return v;
		}
		@Override public void destroyItem(ViewGroup collection, int position, Object view) {
			collection.removeView((View) view);
			trash.addView((View) view);
		}
		@Override public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);}
		@Override public Parcelable saveState() {
			return null;
		}
	}
	androidx.viewpager.widget.ViewPager viewPager;
	com.google.android.material.tabs.TabLayout tabLayout;
	private void foo() {
	}
	
	
	private void _setImageViewRipple (final ImageView _imageview, final String _color1, final String _color2) {
		_imageview.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_color1), Color.parseColor(_color2)}));
	}
	
	
	private void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
		_view.setElevation(5);
	}
	
	
	private void _clickAnimation (final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
