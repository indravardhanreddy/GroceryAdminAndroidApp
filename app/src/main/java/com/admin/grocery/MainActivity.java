package com.admin.grocery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
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
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CompoundButton;
import android.graphics.Typeface;

public class MainActivity extends AppCompatActivity {
	
	
	private String phone = "";
	private String codeSend = "";
	private String code = "";
	private String codeSent = "";
	private String url = "";
	
	private LinearLayout linear1;
	private LinearLayout linear11;
	private LinearLayout linear8;
	private LinearLayout linear5;
	private LinearLayout linear9;
	private TextView textview4;
	private ImageView imageview1;
	private TextView textview3;
	private Switch switch1;
	private LinearLayout line_login_login;
	private LinearLayout line_forgot_forgot;
	private LinearLayout line_register;
	private EditText edittext1;
	private EditText edittext2;
	private Button button3;
	private LinearLayout linear3;
	private TextView textview5;
	private ImageView imageview2;
	private TextView textview11;
	private TextView textview6;
	private TextView textview8;
	private EditText edittext3;
	private Button button1;
	private TextView textview10;
	private EditText edittext5;
	private EditText edittext6;
	private EditText edittext7;
	private Button button2;
	private TextView textview12;
	
	private Intent i = new Intent();
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private SharedPreferences ref;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		textview4 = (TextView) findViewById(R.id.textview4);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview3 = (TextView) findViewById(R.id.textview3);
		switch1 = (Switch) findViewById(R.id.switch1);
		line_login_login = (LinearLayout) findViewById(R.id.line_login_login);
		line_forgot_forgot = (LinearLayout) findViewById(R.id.line_forgot_forgot);
		line_register = (LinearLayout) findViewById(R.id.line_register);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		button3 = (Button) findViewById(R.id.button3);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview11 = (TextView) findViewById(R.id.textview11);
		textview6 = (TextView) findViewById(R.id.textview6);
		textview8 = (TextView) findViewById(R.id.textview8);
		edittext3 = (EditText) findViewById(R.id.edittext3);
		button1 = (Button) findViewById(R.id.button1);
		textview10 = (TextView) findViewById(R.id.textview10);
		edittext5 = (EditText) findViewById(R.id.edittext5);
		edittext6 = (EditText) findViewById(R.id.edittext6);
		edittext7 = (EditText) findViewById(R.id.edittext7);
		button2 = (Button) findViewById(R.id.button2);
		textview12 = (TextView) findViewById(R.id.textview12);
		auth = FirebaseAuth.getInstance();
		ref = getSharedPreferences("ref", Activity.MODE_PRIVATE);
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2)  {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					ref.edit().putString("Lang", "తెలుగు").commit();
					switch1.setChecked(true);
					switch1.setText("తెలుగు");
					textview4.setText("ఆన్‌లైన్ కిరాణా దుకాణం");
					textview3.setText("కస్టమర్ లాగిన్");
					button3.setText("ప్రవేశించండి");
					textview5.setText("పాస్‌వర్డ్ మర్చిపోయారా?");
					textview11.setText("మీకు ఖాతా లేదా?");
					textview6.setText("నమోదు");
					textview8.setText("పాస్వర్డ్ మర్చిపోయారా");
					button1.setText("రహస్యపదాన్ని మార్చుకోండి");
					textview10.setText("కొనసాగించడానికి నమోదు చేయండి");
					button2.setText("నమోదు");
					textview12.setText("ప్రవేశించండి");
					edittext1.setHint("మీ ఇమెయిల్‌ను నమోదు చేయండి");
					edittext2.setHint("మీ పాస్వర్డ్ ని నమోదుచేయండి");
				}
				else {
					ref.edit().putString("Lang", "English").commit();
					switch1.setChecked(false);
					switch1.setText("English");
					textview4.setText("Online Grocery Store");
					textview3.setText("Customer login ");
					button3.setText("Login");
					textview5.setText("Forgot password?");
					textview11.setText("don't you have an account?");
					textview6.setText("Register");
					textview8.setText("Forgot password");
					button1.setText("Reset Password");
					textview10.setText("Register in to continue");
					button2.setText("Register");
					textview12.setText("Login");
					edittext1.setHint("Enter your email ");
					edittext2.setHint("Enter your password ");
				}
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if ((edittext1.getText().toString().length() > 0) && (edittext2.getText().toString().length() > 0)) {
					auth.signInWithEmailAndPassword(edittext1.getText().toString(), edittext2.getText().toString()).addOnCompleteListener(MainActivity.this, _auth_sign_in_listener);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Enter your correct email and password ");
				}
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				line_forgot_forgot.setVisibility(View.VISIBLE);
				line_login_login.setVisibility(View.GONE);
				line_register.setVisibility(View.GONE);
			}
		});
		
		textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				line_register.setVisibility(View.VISIBLE);
				line_login_login.setVisibility(View.GONE);
				line_forgot_forgot.setVisibility(View.GONE);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext3.getText().toString().length() > 0) {
					auth.sendPasswordResetEmail(edittext3.getText().toString()).addOnCompleteListener(_auth_reset_password_listener);
					edittext3.setText("");
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Enter your email id");
				}
				line_forgot_forgot.setVisibility(View.GONE);
				line_login_login.setVisibility(View.VISIBLE);
				line_register.setVisibility(View.GONE);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if ((edittext5.getText().toString().length() > 0) && (edittext6.getText().toString().length() > 0)) {
					auth.createUserWithEmailAndPassword(edittext5.getText().toString(), edittext6.getText().toString()).addOnCompleteListener(MainActivity.this, _auth_create_user_listener);
					edittext5.setText("");
					edittext6.setText("");
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Enter your correct email and password ");
				}
				line_forgot_forgot.setVisibility(View.GONE);
				line_login_login.setVisibility(View.VISIBLE);
				line_register.setVisibility(View.GONE);
			}
		});
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					edittext5.setText("");
					edittext6.setText("");
					_emailVerification();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					edittext1.setText("");
					edittext2.setText("");
					FirebaseAuth auth = FirebaseAuth.getInstance(); com.google.firebase.auth.FirebaseUser user = auth.getCurrentUser();
					if (user.isEmailVerified()) {
						i.setClass(getApplicationContext(), AdminDashboardActivity.class);
						i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(i);
					} else {
						SketchwareUtil.showMessage(getApplicationContext(), "Please verify your email ");
					} 
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				if (_success) {
					SketchwareUtil.showMessage(getApplicationContext(), "Password link send to your account ");
				}
				else {
					
				}
			}
		};
	}
	private void initializeLogic() {
		setTitle("Welcome to Online Grocery Store ");
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			edittext1.setText("");
			edittext2.setText("");
			FirebaseAuth auth = FirebaseAuth.getInstance(); com.google.firebase.auth.FirebaseUser user = auth.getCurrentUser();
			if (user.isEmailVerified()) {
				i.setClass(getApplicationContext(), AdminDashboardActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(i);
			} else {
				SketchwareUtil.showMessage(getApplicationContext(), "Please verify your email ");
			} 
		}
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
	protected void onPostCreate(Bundle _savedInstanceState) {
		super.onPostCreate(_savedInstanceState);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fff_tusj_more_block_20190913005821.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fff_tusj_more_block_20190913005821.ttf"), 1);
		button3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fff_tusj_more_block_20190913005821.ttf"), 1);
		_round(button3, 10, "#009688");
		_round(button1, 10, "#E57373");
		_round(button2, 10, "#E57373");
		_buttonStyle(button3);
		_buttonStyle(button1);
		_buttonStyle(button2);
	}
	private void _buttonStyle (final View _vieww) {
		android.graphics.drawable.GradientDrawable GAJJDBD = new android.graphics.drawable.GradientDrawable();GAJJDBD.setColor(Color.argb(255,109,178,255));
		GAJJDBD.setCornerRadius(10);
		_vieww.setBackground(GAJJDBD);
	}
	
	
	private void _round (final View _view, final double _num, final String _color) {
		try {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			
			
			float f = (float) _num;
			
			
			gd.setCornerRadius(f);
			_view.setBackground(gd);
			
		} catch(Exception e){ Log.e("Error: ", e.toString()); String error_code = e.toString();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
							builder.setTitle("Error")
								.setMessage(error_code)
								.setCancelable(false)
								.setNegativeButton("ОК", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
												
										}
								});
							AlertDialog alert = builder.create();
							alert.show();
		}
	}
	
	
	private void _emailVerification () {
		FirebaseAuth auth = FirebaseAuth.getInstance();
		
		com.google.firebase.auth.FirebaseUser user = 
		auth.getCurrentUser();
		
		
		user.sendEmailVerification().addOnCompleteListener
		(new 
		OnCompleteListener<Void>()
		{ @Override
			public void onComplete(Task task)
			{
				
				if ((task.isSuccessful())) {
					SketchwareUtil.showMessage(getApplicationContext(), "Please verify your email address ");
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Please verify your email address ");
				}
				
			}});
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
