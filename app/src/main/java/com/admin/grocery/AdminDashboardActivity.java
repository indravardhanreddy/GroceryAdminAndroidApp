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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.graphics.Typeface;
import com.bumptech.glide.Glide;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class AdminDashboardActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String url = "";
	private double i = 0;
	private HashMap<String, Object> mapv = new HashMap<>();
	private String uname = "";
	private String mob = "";
	private double click = 0;
	private boolean flag = false;
	private double keypos = 0;
	private HashMap<String, Object> UpdatifyMap = new HashMap<>();
	
	private ArrayList<String> str = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> adminlist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	
	private LinearLayout linear2;
	private LinearLayout viewpager;
	private LinearLayout linear9;
	private LinearLayout forelayout;
	private LinearLayout backlayout;
	private LinearLayout image_background;
	private LinearLayout linear5;
	private LinearLayout linear8;
	private ImageView user_profile_url;
	private TextView username;
	private TextView mobile_number;
	private LinearLayout swipe_dot_left;
	private LinearLayout swipe_dot_right;
	private LinearLayout linear13;
	private LinearLayout linear29;
	private LinearLayout linear33;
	private LinearLayout add_new_product;
	private LinearLayout change_price;
	private LinearLayout confirm_orders;
	private ImageView imageview4;
	private TextView textview1;
	private ImageView imageview6;
	private TextView textview3;
	private ImageView imageview5;
	private TextView textview2;
	private LinearLayout mail;
	private LinearLayout share;
	private LinearLayout orders_trans;
	private ImageView imageview8;
	private TextView textview5;
	private ImageView imageview9;
	private TextView textview6;
	private ImageView imageview7;
	private TextView textview4;
	private ImageView imageview2;
	private ImageView imageview3;
	
	private FirebaseAuth fa;
	private OnCompleteListener<Void> fa_updateEmailListener;
	private OnCompleteListener<Void> fa_updatePasswordListener;
	private OnCompleteListener<Void> fa_emailVerificationSentListener;
	private OnCompleteListener<Void> fa_deleteUserListener;
	private OnCompleteListener<Void> fa_updateProfileListener;
	private OnCompleteListener<AuthResult> fa_phoneAuthListener;
	private OnCompleteListener<AuthResult> fa_googleSignInListener;
	private OnCompleteListener<AuthResult> _fa_create_user_listener;
	private OnCompleteListener<AuthResult> _fa_sign_in_listener;
	private OnCompleteListener<Void> _fa_reset_password_listener;
	private AlertDialog.Builder d;
	private Intent in = new Intent();
	private Intent chat = new Intent();
	private Intent prod = new Intent();
	private Intent pricechange = new Intent();
	private Intent conforder = new Intent();
	private DatabaseReference admin_details = _firebase.getReference("admin_details");
	private ChildEventListener _admin_details_child_listener;
	private Intent intro = new Intent();
	private Intent cust = new Intent();
	private SharedPreferences ref;
	private AlertDialog.Builder upload_dailog;
	private SharedPreferences UCSP;
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private Intent offers = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.admin_dashboard);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		viewpager = (LinearLayout) findViewById(R.id.viewpager);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		forelayout = (LinearLayout) findViewById(R.id.forelayout);
		backlayout = (LinearLayout) findViewById(R.id.backlayout);
		image_background = (LinearLayout) findViewById(R.id.image_background);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		user_profile_url = (ImageView) findViewById(R.id.user_profile_url);
		username = (TextView) findViewById(R.id.username);
		mobile_number = (TextView) findViewById(R.id.mobile_number);
		swipe_dot_left = (LinearLayout) findViewById(R.id.swipe_dot_left);
		swipe_dot_right = (LinearLayout) findViewById(R.id.swipe_dot_right);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear29 = (LinearLayout) findViewById(R.id.linear29);
		linear33 = (LinearLayout) findViewById(R.id.linear33);
		add_new_product = (LinearLayout) findViewById(R.id.add_new_product);
		change_price = (LinearLayout) findViewById(R.id.change_price);
		confirm_orders = (LinearLayout) findViewById(R.id.confirm_orders);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		textview2 = (TextView) findViewById(R.id.textview2);
		mail = (LinearLayout) findViewById(R.id.mail);
		share = (LinearLayout) findViewById(R.id.share);
		orders_trans = (LinearLayout) findViewById(R.id.orders_trans);
		imageview8 = (ImageView) findViewById(R.id.imageview8);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageview9 = (ImageView) findViewById(R.id.imageview9);
		textview6 = (TextView) findViewById(R.id.textview6);
		imageview7 = (ImageView) findViewById(R.id.imageview7);
		textview4 = (TextView) findViewById(R.id.textview4);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		fa = FirebaseAuth.getInstance();
		d = new AlertDialog.Builder(this);
		ref = getSharedPreferences("ref", Activity.MODE_PRIVATE);
		upload_dailog = new AlertDialog.Builder(this);
		UCSP = getSharedPreferences("UCSP", Activity.MODE_PRIVATE);
		net = new RequestNetwork(this);
		
		user_profile_url.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intro.setClass(getApplicationContext(), IntroductionActivity.class);
				intro.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intro);
			}
		});
		
		add_new_product.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		change_price.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		confirm_orders.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				prod.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				prod.setClass(getApplicationContext(), ProdviewActivity.class);
				prod.putExtra("POS", "NEW");
				startActivity(prod);
			}
		});
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!ref.getString("Lang", "").equals("English")) {
					_TabTarget(imageview4, "క్రొత్త ఉత్పత్తులను జోడించండి", "మీకు నచ్చిన అదనపు ఉత్పత్తులను మీరు జోడించవచ్చు.");
				}
				else {
					_TabTarget(imageview4, "Add New Products ", "You can add additional Products of your choice. ");
				}
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pricechange.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				pricechange.setClass(getApplicationContext(), ChangeitempriceActivity.class);
				startActivity(pricechange);
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!ref.getString("Lang", "").equals("English")) {
					_TabTarget(imageview6, "ఉత్పత్తి ధరలను మార్చండి", "ఇప్పుడు మీరు రోజువారీ ఉత్పత్తి ధరను మార్చవచ్చు.");
				}
				else {
					_TabTarget(imageview6, "Change Product Prices ", "Now you can change the product price on day to day basis. ");
				}
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				conforder.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				conforder.setClass(getApplicationContext(), AdminOrdersActivity.class);
				startActivity(conforder);
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!ref.getString("Lang", "").equals("English")) {
					_TabTarget(imageview5, "ఉత్పత్తిని నిర్ధారించండి", "మీరు కస్టమర్ యొక్క క్రమాన్ని చూడవచ్చు మరియు ఉత్పత్తులు అందుబాటులో ఉంటే ఆర్డర్‌ను నిర్ధారించవచ్చు.");
				}
				else {
					_TabTarget(imageview5, "Confirm the Orders ", "You can view the order of the customer and confirm the order if products are available. ");
				}
			}
		});
		
		mail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		orders_trans.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
					chat.setClass(getApplicationContext(), ChatActivity.class);
					chat.putExtra("Name", "Admin");
					startActivity(chat);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Please login ");
				}
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_TabTarget(imageview8, "Chat with shop manager ", "If you have any questions you can message ");
			}
		});
		
		imageview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				upload_dailog.setTitle("Upload to Google Drive ");
				upload_dailog.setMessage("What you would like to upload? ");
				upload_dailog.setPositiveButton("User App", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						_ShareApp("com.user.grocery");
						SketchwareUtil.showMessage(getApplicationContext(), "Upload user app to Google Drive successful. ");
					}
				});
				upload_dailog.setNeutralButton("Admin App", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						_ShareApp("com.admin.grocery");
						SketchwareUtil.showMessage(getApplicationContext(), "Upload admin app to Google Drive successful. ");
					}
				});
				upload_dailog.create().show();
			}
		});
		
		textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!ref.getString("Lang", "").equals("English")) {
					_TabTarget(imageview9, "Google డిస్క్‌లోకి అప్‌లోడ్ చేయండి", "ఇప్పుడు మీరు మీ అనువర్తనాన్ని Google డిస్క్‌లోకి అప్‌లోడ్ చేయవచ్చు మరియు అప్‌డేటిఫై ఉపయోగించి అనువర్తనాన్ని నవీకరించడానికి నోటిఫికేషన్ పంపవచ్చు");
				}
				else {
					_TabTarget(imageview9, "Upload to Google Drive ", "Now you can upload your app to Google Drive and send a notification to update app using updatify");
				}
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				offers.setClass(getApplicationContext(), OffersActivity.class);
				startActivity(offers);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cust.setClass(getApplicationContext(), AdminprofileActivity.class);
				cust.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(cust);
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("User Signout from App? ");
				d.setMessage("Would you like to signout? ");
				d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						FirebaseAuth.getInstance().signOut();
					}
				});
				d.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
				finish();
			}
		});
		
		_admin_details_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				admin_details.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						adminlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								adminlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						str.clear();
						for (DataSnapshot dshot:
						_dataSnapshot.getChildren()) {
							str.add(dshot.getKey()) ;
						}
						if (str.size() > 0) {
							i = 0;
							for(int _repeat17 = 0; _repeat17 < (int)(str.size()); _repeat17++) {
								if (str.get((int)(i)).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
									flag = true;
									keypos = i;
								}
								i++;
							}
						}
						else {
							
						}
						if (flag) {
							username.setText(adminlist.get((int)keypos).get("Shopname").toString());
							mobile_number.setText(adminlist.get((int)keypos).get("Usermob").toString());
							uname = adminlist.get((int)keypos).get("Shopname").toString();
							mob = adminlist.get((int)keypos).get("Usermob").toString();
						}
						else {
							
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				admin_details.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						adminlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								adminlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						str.clear();
						for (DataSnapshot dshot:
						_dataSnapshot.getChildren()) {
							str.add(dshot.getKey()) ;
						}
						if (str.size() > 0) {
							i = 0;
							for(int _repeat17 = 0; _repeat17 < (int)(str.size()); _repeat17++) {
								if (str.get((int)(i)).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
									flag = true;
									keypos = i;
								}
								i++;
							}
						}
						else {
							
						}
						if (flag) {
							username.setText(adminlist.get((int)keypos).get("Shopname").toString());
							mobile_number.setText(adminlist.get((int)keypos).get("Usermob").toString());
							uname = adminlist.get((int)keypos).get("Shopname").toString();
							mob = adminlist.get((int)keypos).get("Usermob").toString();
						}
						else {
							
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				admin_details.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						adminlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								adminlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						str.clear();
						for (DataSnapshot dshot:
						_dataSnapshot.getChildren()) {
							str.add(dshot.getKey()) ;
						}
						if (str.size() > 0) {
							i = 0;
							for(int _repeat17 = 0; _repeat17 < (int)(str.size()); _repeat17++) {
								if (str.get((int)(i)).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
									flag = true;
									keypos = i;
								}
								i++;
							}
						}
						else {
							
						}
						if (flag) {
							username.setText(adminlist.get((int)keypos).get("Shopname").toString());
							mobile_number.setText(adminlist.get((int)keypos).get("Usermob").toString());
							uname = adminlist.get((int)keypos).get("Shopname").toString();
							mob = adminlist.get((int)keypos).get("Usermob").toString();
						}
						else {
							
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		admin_details.addChildEventListener(_admin_details_child_listener);
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				_UpdatifyComponent(_response);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				SketchwareUtil.showMessage(getApplicationContext(), _message);
			}
		};
		
		fa_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		fa_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_fa_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fa_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fa_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	private void initializeLogic() {
		net.startRequestNetwork(RequestNetworkController.GET, "https://nerbly.com/updatify/apiv1/files/OnlineGroceryAdminApp_947192477.json", "", _net_request_listener);
		_setForeLayout(forelayout, backlayout);
		_SX_CornerRadius_card(swipe_dot_left, "#212121", 50);
		_SX_CornerRadius_card(swipe_dot_right, "#212121", 50);
		_extraViewPager();
		_round(add_new_product, 25, "#00BCD4");
		_round(confirm_orders, 25, "#A5D6A7");
		_round(change_price, 25, "#BCAAA4");
		_round(orders_trans, 25, "#FFF59D");
		_round(mail, 25, "#9EA8DA");
		_round(share, 25, "#BDBDBD");
		if (!ref.getString("Lang", "").equals("English")) {
			username.setText("నీ పేరు");
			mobile_number.setText("మొబైల్ సంఖ్య");
			textview1.setText("క్రొత్త ఉత్పత్తులను జోడించండి");
			textview3.setText("ఉత్పత్తి ధరలను మార్చండి");
			textview2.setText("దరఖాస్తు నిర్ధారణ");
			textview4.setText("ఆర్డర్ స్థితి");
			textview5.setText("చాట్");
			textview6.setText("మీ స్నేహితులతో పంచుకోండి");
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
		url = "";
		flag = false;
		admin_details.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				adminlist = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						adminlist.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				str.clear();
				for (DataSnapshot dshot:
				_dataSnapshot.getChildren()) {
					str.add(dshot.getKey()) ;
				}
				if (str.size() > 0) {
					i = 0;
					for(int _repeat78 = 0; _repeat78 < (int)(str.size()); _repeat78++) {
						if (str.get((int)(i)).equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							flag = true;
							keypos = i;
						}
						i++;
					}
				}
				else {
					cust.setClass(getApplicationContext(), AdminprofileActivity.class);
					cust.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(cust);
				}
				if (flag) {
					username.setText(adminlist.get((int)keypos).get("Shopname").toString());
					mobile_number.setText(adminlist.get((int)keypos).get("Usermob").toString());
					uname = adminlist.get((int)keypos).get("Shopname").toString();
					mob = adminlist.get((int)keypos).get("Usermob").toString();
				}
				else {
					cust.setClass(getApplicationContext(), AdminprofileActivity.class);
					cust.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(cust);
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		d.setTitle("User Signout from App? ");
		d.setMessage("Would you like to signout? ");
		d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				FirebaseAuth.getInstance().signOut();
				finish();
			}
		});
		d.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		d.create().show();
	}
	private void _setForeLayout (final View _forelayout, final View _backlayout) {
		//Creator Error 404
		// Telegram @erroratten
		final LinearLayout _layout = (LinearLayout)_forelayout;
		        final LinearLayout _backview = (LinearLayout) _backlayout;
		_layout.setTranslationZ(1);
		        _backview.post(new Runnable() {
			            @Override
			            public void run() {
				                final boolean[] closed = {true};
				                final int[] current = {0};
				                final int value = 0-_backview.getWidth();
				                _backview.setTranslationX(value);
				
				                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,_layout.getHeight());
				                _layout.setLayoutParams(params);
				
				                ((android.view.ViewGroup)_layout.getParent()).setClickable(true);
				                ((android.view.ViewGroup)_layout.getParent()).setFocusable(true);
				                ((android.view.ViewGroup)_layout.getParent()).setOnTouchListener(new View.OnTouchListener() {
					                    @Override
					                    public boolean onTouch(View v, MotionEvent event) {
						                        switch (event.getAction()){
							                            case MotionEvent.ACTION_DOWN:{
								                                if (closed[0]){
									                                    current[0] = (int) event.getX();
									                                }else {
									                                    current[0] = (int) event.getX()+(_backview.getWidth());
									                                }
								                            }
							                            case MotionEvent.ACTION_MOVE:{
								                                if (event.getX()-current[0]>value&event.getX()-current[0]<0){
									                                    _layout.setTranslationX(event.getX()-current[0]);
									                                }
								
								                                if (event.getX()-current[0]<value){
									                                    _layout.setTranslationX(value);
									                                }
								                                if (event.getX()-current[0]>0){
									                                    _layout.setTranslationX(0);
									                                }
								                                break;
								                            }
							                            case MotionEvent.ACTION_UP:{
								                                int value_2 = (int) (event.getX()-current[0]);
								                                if (value_2>(value/2)){
									                                    ObjectAnimator animator = new ObjectAnimator();
									                                    animator.setTarget(_layout);
									                                    animator.setPropertyName("translationX");
									                                    animator.setFloatValues(0);
									                                    animator.setDuration(200);
									                                    animator.setInterpolator(new android.view.animation.DecelerateInterpolator());
									                                    animator.start();
									                                    closed[0] = true;
									                                }else {
									                                    ObjectAnimator animator = new ObjectAnimator();
									                                    animator.setTarget(_layout);
									                                    animator.setPropertyName("translationX");
									                                    animator.setFloatValues(value);
									                                    animator.setDuration(200);
									                                    animator.setInterpolator(new android.view.animation.DecelerateInterpolator());
									                                    animator.start();
									                                    closed[0] = false;
									                                }
								                                break;
								                            }
							                        }
						                        return false;
						                    }
					                });
				            }
			        });
	}
	
	
	private void _addCardView (final View _layoutView, final double _margins, final double _cornerRadius, final double _cardElevation, final double _cardMaxElevation, final boolean _preventCornerOverlap, final String _backgroundColor) {
		androidx.cardview.widget.CardView cv = new androidx.cardview.widget.CardView(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		int m = (int)_margins;
		lp.setMargins(m,m,m,m);
		cv.setLayoutParams(lp);
		int c = Color.parseColor(_backgroundColor);
		cv.setCardBackgroundColor(c);
		cv.setRadius((float)_cornerRadius);
		cv.setCardElevation((float)_cardElevation);
		cv.setMaxCardElevation((float)_cardMaxElevation);
		cv.setPreventCornerOverlap(_preventCornerOverlap);
		if(_layoutView.getParent() instanceof LinearLayout){
			ViewGroup vg = ((ViewGroup)_layoutView.getParent());
			vg.removeView(_layoutView);
			vg.removeAllViews();
			vg.addView(cv);
			cv.addView(_layoutView);
		}else{
			
		}
	}
	
	
	private void _extraViewPager () {
		if (!ref.getString("Lang", "").equals("English")) {
			mapv = new HashMap<>();
			mapv.put("title", "క్రొత్త ఉత్పత్తులను జోడించండి");
			mapv.put("description", "మీకు నచ్చిన అదనపు ఉత్పత్తులను మీరు జోడించవచ్చు.");
			mapv.put("image", "/storage/emulated/0/Pictures/PngItem_3256461.png");
			map.add(mapv);
			mapv = new HashMap<>();
			mapv.put("title", "ఉత్పత్తి ధరను మార్చండి");
			mapv.put("description", "• ఇప్పుడు మీరు రోజువారీ ప్రాతిపదికన ధరను కూడా మార్చవచ్చు.");
			mapv.put("image", "/storage/emulated/0/Pictures/clipart1434337.png");
			map.add(mapv);
			mapv = new HashMap<>();
			mapv.put("title", "మీ కస్టమర్ ఆర్డర్‌లను నిర్ధారించండి");
			mapv.put("description", "మీ ఆర్డర్‌ను నిర్ధారించండి.\n• ప్రివ్యూ ఆర్డర్.\n*ఏదైనా చెల్లని ఆర్డర్‌లను తొలగించడానికి ఎక్కువసేపు నొక్కండి\n*ఆర్డర్‌ను నిర్ధారించడానికి చెక్అవుట్ క్లిక్ చేయండి.");
			mapv.put("image", "/storage/emulated/0/Pictures/check-completed-confirmation-order-order-completed-icon-order-confirmation-png-512_512.png");
			map.add(mapv);
			mapv = new HashMap<>();
			mapv.put("title", "రవాణాలో ఆర్డర్");
			mapv.put("description", "పరివర్తనలో ఉన్న ఆర్డర్‌లను చూడవచ్చు");
			mapv.put("image", "/storage/emulated/0/Pictures/transit-131964752916205817_512.png");
			map.add(mapv);
		}
		else {
			mapv = new HashMap<>();
			mapv.put("title", "Add new Products ");
			mapv.put("description", "You can add additional Products of your choice. ");
			mapv.put("image", "/storage/emulated/0/Pictures/PngItem_3256461.png");
			map.add(mapv);
			mapv = new HashMap<>();
			mapv.put("title", "Change the product price ");
			mapv.put("description", "•Now you can even change the price on day to day basis.");
			mapv.put("image", "/storage/emulated/0/Pictures/clipart1434337.png");
			map.add(mapv);
			mapv = new HashMap<>();
			mapv.put("title", "Confirm your order ");
			mapv.put("description", "Confirm your order.\n•Preview order. \n•Long press to delete any invalid orders\n•Click checkout to confirm the order. ");
			mapv.put("image", "/storage/emulated/0/Pictures/check-completed-confirmation-order-order-completed-icon-order-confirmation-png-512_512.png");
			map.add(mapv);
			mapv = new HashMap<>();
			mapv.put("title", "Order in transit");
			mapv.put("description", "Orders in transition can be viewed ");
			mapv.put("image", "/storage/emulated/0/Pictures/transit-131964752916205817_512.png");
			map.add(mapv);
		}
		_ViewPager(viewpager, map, 60);
	}
	
	
	private void _ViewPager (final View _linear, final ArrayList<HashMap<String, Object>> _list, final double _padding) {
		androidx.viewpager.widget.ViewPager viewPager = new androidx.viewpager.widget.ViewPager(_linear.getContext());
		        android.view.ViewGroup.LayoutParams params = new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		        viewPager.setLayoutParams(params);
		viewPager.setClipToPadding(false);
		viewPager.setPadding(16,0,120,0);
		
		        viewPager.setAdapter(new AdapterVP(_list));
		LinearLayout layout = (LinearLayout) _linear;
		layout.addView(viewPager);
		        
		    }
	        class AdapterVP extends androidx.viewpager.widget.PagerAdapter {
		
		        ArrayList<HashMap<String,Object>> _list;
		
		            public AdapterVP(ArrayList<HashMap<String, Object>> _list) {
			                this._list = _list;
			            }
		
		            private LayoutInflater layoutInflater;
		
		            @Override
		            public int getCount() {
			                return _list.size();
			            }
		
		            @Override
		            public boolean isViewFromObject(View view, Object object) {
			                return view.equals(object);
			            }
		
		            
		           
		            @Override
		            public Object instantiateItem(ViewGroup container, final int position) {
			                layoutInflater = LayoutInflater.from(container.getContext());
			                View view = layoutInflater.inflate(R.layout.view_pager, container,false);
			                final ImageView image;
			                TextView title, description;
			
			
			                image = view.findViewById(R.id.image);
			                title = view.findViewById(R.id.title);
			                description = view.findViewById(R.id.description);
			
			               
			                
			if (_list.get(position).get("image").toString().contains("https://")) {
				Glide.with(getApplicationContext()).load(Uri.parse(_list.get(position).get("image").toString())).into(image);
			}
			else {
				image.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_list.get(position).get("image").toString(), 1024, 1024));
			}
			LinearLayout card = view.findViewById(R.id.card);
			LinearLayout image_bg = view.findViewById(R.id.image_bg);
			_addCardView(card, 16, 40, 0, 0, true, "#171717");
			_addCardView(image_bg, 16, 40, 0, 0, true, "#212121");
			title.setText(_list.get(position).get("title").toString());
			                description.setText(_list.get(position).get("description").toString());
			
			
			                container.addView(view,0);
			                return view;
			            }
		
		            @Override
		            public void destroyItem(ViewGroup container, int position, Object object) {
			                container.removeView((View)object);
		}
	}
	
	
	private void _SX_CornerRadius_card (final View _view, final String _color, final double _value) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_value);
		_view.setBackground(gd);
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
	
	
	private void _TabTarget (final View _view, final String _title, final String _msg) {
		// onStart()
		
		TapTargetView.showFor(AdminDashboardActivity.this,
		
		TapTarget.forView(_view, _title, _msg)
		
		//Background Color Tab Target
		.outerCircleColorInt(Color.parseColor("#009688"))
		
		.outerCircleAlpha(0.99f)
		.targetCircleColor(android.R.color.white)
		.titleTextSize(24)
		.titleTextColor(android.R.color.white)
		.descriptionTextSize(18)
		.descriptionTextColor(android.R.color.white)
		.textColor(android.R.color.white)
		.textTypeface(Typeface.SANS_SERIF)
		.dimColor(android.R.color.black)
		.drawShadow(true)
		.cancelable(false)
		.tintTarget(true)
		.transparentTarget(true)
		.targetRadius(30),
		new TapTargetView.Listener() {
			@Override public void onTargetClick(TapTargetView view) {
				super.onTargetClick(view);
			}});
		
		// onPostCreate()
		
	}static class UiUtil {
		    UiUtil() {
			    }
		    static int dp(Context context, int val) {
			        return (int) TypedValue.applyDimension(
			                TypedValue.COMPLEX_UNIT_DIP, val, context.getResources().getDisplayMetrics());
			    }
		    static int sp(Context context, int val) {
			        return (int) TypedValue.applyDimension(
			                TypedValue.COMPLEX_UNIT_SP, val, context.getResources().getDisplayMetrics());
			    }
		    static int themeIntAttr(Context context, String attr) {
			        final android.content.res.Resources.Theme theme = context.getTheme();
			        if (theme == null) {
				            return -1;
				        }
			        final TypedValue value = new TypedValue();
			        final int id = context.getResources().getIdentifier(attr, "attr", context.getPackageName());
			
			        if (id == 0) {
				            // Not found
				            return -1;
				        }
			        theme.resolveAttribute(id, value, true);
			        return value.data;
			    }
		    static int setAlpha(int argb, float alpha) {
			        if (alpha > 1.0f) {
				            alpha = 1.0f;
				        } else if (alpha <= 0.0f) {
				            alpha = 0.0f;
				        }
			        return ((int) ((argb >>> 24) * alpha) << 24) | (argb & 0x00FFFFFF);
			    }
	}
			static class FloatValueAnimatorBuilder {
		
		    private final ValueAnimator animator;
		
		    private EndListener endListener;
		
		    interface UpdateListener {
			        void onUpdate(float lerpTime);
			    }
		    interface EndListener {
			        void onEnd();
			    }
		    protected FloatValueAnimatorBuilder() {
			        this(false);
			    }
		    FloatValueAnimatorBuilder(boolean reverse) {
			        if (reverse) {
				            this.animator = ValueAnimator.ofFloat(1.0f, 0.0f);
				        } else {
				            this.animator = ValueAnimator.ofFloat(0.0f, 1.0f);
				        }
			    }
		    public FloatValueAnimatorBuilder delayBy(long millis) {
			        animator.setStartDelay(millis);
			        return this;
			    }
		    public FloatValueAnimatorBuilder duration(long millis) {
			        animator.setDuration(millis);
			        return this;
			    }
		    public FloatValueAnimatorBuilder interpolator(TimeInterpolator lerper) {
			        animator.setInterpolator(lerper);
			        return this;
			    }
		    public FloatValueAnimatorBuilder repeat(int times) {
			        animator.setRepeatCount(times);
			        return this;
			    }
		    public FloatValueAnimatorBuilder onUpdate(final UpdateListener listener) {
			        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                listener.onUpdate((float) animation.getAnimatedValue());
					            }
				        });
			        return this;
			    }
		    public FloatValueAnimatorBuilder onEnd(final EndListener listener) {
			        this.endListener = listener;
			        return this;
			    }
		    public ValueAnimator build() {
			        if (endListener != null) {
				            animator.addListener(new AnimatorListenerAdapter() {
					                @Override
					                public void onAnimationEnd(Animator animation) {
						                    endListener.onEnd();
						                }
					            });
				        }
			        return animator;
			    }
	}
			static class ReflectUtil {
		    ReflectUtil() {
			    }
		    static Object getPrivateField(Object source, String fieldName)
		            throws NoSuchFieldException, IllegalAccessException {
			        final java.lang.reflect.Field objectField = source.getClass().getDeclaredField(fieldName);
			        objectField.setAccessible(true);
			        return objectField.get(source);
			    }
	}
			static class TapTarget extends Activity {
		    final CharSequence title;
		    final CharSequence description;
		    float outerCircleAlpha = 0.96f;
		    int targetRadius = 44;
		    Rect bounds;
		    android.graphics.drawable.Drawable icon;
		    Typeface titleTypeface;
		    Typeface descriptionTypeface;
		
		
		    private int outerCircleColorRes = -1;
		    private int targetCircleColorRes = -1;
		    private int dimColorRes = -1;
		    private int titleTextColorRes = -1;
		    private int descriptionTextColorRes = -1;
		
		    private Integer outerCircleColor = null;
		    private Integer targetCircleColor = null;
		    private Integer dimColor = null;
		    private Integer titleTextColor = null;
		    private Integer descriptionTextColor = null;
		
		    private int titleTextDimen = -1;
		    private int descriptionTextDimen = -1;
		    private int titleTextSize = 20;
		    private int descriptionTextSize = 18;
		    int id = -1;
		    boolean drawShadow = false;
		    boolean cancelable = true;
		    boolean tintTarget = true;
		    boolean transparentTarget = false;
		    float descriptionTextAlpha = 0.54f;
		
		    public static TapTarget forView(View view, CharSequence title) {
			        return forView(view, title, null);
			    }
		    public static TapTarget forView(View view, CharSequence title, CharSequence description) {
			        return new ViewTapTarget(view, title, description);
			    }
		    public static TapTarget forBounds(Rect bounds, CharSequence title) {
			        return forBounds(bounds, title, null);
			    }
		    public static TapTarget forBounds(Rect bounds, CharSequence title,  CharSequence description) {
			        return new TapTarget(bounds, title, description);
			    }
		    protected TapTarget(Rect bounds, CharSequence title,  CharSequence description) {
			        this(title, description);
			        if (bounds == null) {
				            throw new IllegalArgumentException("Cannot pass null bounds or title");
				        }
			        this.bounds = bounds;
			    }
		    protected TapTarget(CharSequence title,  CharSequence description) {
			        if (title == null) {
				            throw new IllegalArgumentException("Cannot pass null title");
				        }
			        this.title = title;
			        this.description = description;
			    }
		    public TapTarget transparentTarget(boolean transparent) {
			        this.transparentTarget = transparent;
			        return this;
			    }
		    public TapTarget outerCircleColor( int color) {
			        this.outerCircleColorRes = color;
			        return this;
			    }
		    public TapTarget outerCircleColorInt( int color) {
			        this.outerCircleColor = color;
			        return this;
			    }
		    public TapTarget outerCircleAlpha(float alpha) {
			        if (alpha < 0.0f || alpha > 1.0f) {
				            throw new IllegalArgumentException("Given an invalid alpha value: " + alpha);
				        }
			        this.outerCircleAlpha = alpha;
			        return this;
			    }
		    public TapTarget targetCircleColor( int color) {
			        this.targetCircleColorRes = color;
			        return this;
			    }
		    public TapTarget targetCircleColorInt( int color) {
			        this.targetCircleColor = color;
			        return this;
			    }
		    public TapTarget textColor( int color) {
			        this.titleTextColorRes = color;
			        this.descriptionTextColorRes = color;
			        return this;
			    }
		    public TapTarget textColorInt( int color) {
			        this.titleTextColor = color;
			        this.descriptionTextColor = color;
			        return this;
			    }
		    public TapTarget titleTextColor( int color) {
			        this.titleTextColorRes = color;
			        return this;
			    }
		    public TapTarget titleTextColorInt( int color) {
			        this.titleTextColor = color;
			        return this;
			    }
		    public TapTarget descriptionTextColor( int color) {
			        this.descriptionTextColorRes = color;
			        return this;
			    }
		    public TapTarget descriptionTextColorInt( int color) {
			        this.descriptionTextColor = color;
			        return this;
			    }
		    public TapTarget textTypeface(Typeface typeface) {
			        if (typeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        titleTypeface = typeface;
			        descriptionTypeface = typeface;
			        return this;
			    }
		    public TapTarget titleTypeface(Typeface titleTypeface) {
			        if (titleTypeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        this.titleTypeface = titleTypeface;
			        return this;
			    }
		    public TapTarget descriptionTypeface(Typeface descriptionTypeface) {
			        if (descriptionTypeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        this.descriptionTypeface = descriptionTypeface;
			        return this;
			    }
		    public TapTarget titleTextSize(int sp) {
			        if (sp < 0) throw new IllegalArgumentException("Given negative text size");
			        this.titleTextSize = sp;
			        return this;
			    }
		    public TapTarget descriptionTextSize(int sp) {
			        if (sp < 0) throw new IllegalArgumentException("Given negative text size");
			        this.descriptionTextSize = sp;
			        return this;
			    }
		    public TapTarget titleTextDimen( int dimen) {
			        this.titleTextDimen = dimen;
			        return this;
			    }
		    public TapTarget descriptionTextAlpha(float descriptionTextAlpha) {
			        if (descriptionTextAlpha < 0 || descriptionTextAlpha > 1f) {
				            throw new IllegalArgumentException("Given an invalid alpha value: " + descriptionTextAlpha);
				        }
			        this.descriptionTextAlpha = descriptionTextAlpha;
			        return this;
			    }
		    public TapTarget descriptionTextDimen( int dimen) {
			        this.descriptionTextDimen = dimen;
			        return this;
			    }
		    public TapTarget dimColor( int color) {
			        this.dimColorRes = color;
			        return this;
			    }
		    public TapTarget dimColorInt( int color) {
			        this.dimColor = color;
			        return this;
			    }
		    public TapTarget drawShadow(boolean draw) {
			        this.drawShadow = draw;
			        return this;
			    }
		    public TapTarget cancelable(boolean status) {
			        this.cancelable = status;
			        return this;
			    }
		    public TapTarget tintTarget(boolean tint) {
			        this.tintTarget = tint;
			        return this;
			    }
		    public TapTarget icon(android.graphics.drawable.Drawable icon) {
			        return icon(icon, false);
			    }
		    public TapTarget icon(android.graphics.drawable.Drawable icon, boolean hasSetBounds) {
			        if (icon == null) throw new IllegalArgumentException("Cannot use null drawable");
			        this.icon = icon;
			        if (!hasSetBounds) {
				            this.icon.setBounds(new Rect(0, 0, this.icon.getIntrinsicWidth(), this.icon.getIntrinsicHeight()));
				        }
			        return this;
			    }
		    public TapTarget id(int id) {
			        this.id = id;
			        return this;
			    }
		    public TapTarget targetRadius(int targetRadius) {
			        this.targetRadius = targetRadius;
			        return this;
			    }
		    public int id() {
			        return id;
			    }
		    public void onReady(Runnable runnable) {
			        runnable.run();
			    }
		    public Rect bounds() {
			        if (bounds == null) {
				            throw new IllegalStateException("Requesting bounds that are not set! Make sure your target is ready");
				        }
			        return bounds;
			    }
		    Integer outerCircleColorInt(Context context) {
			        return colorResOrInt(context, outerCircleColor, outerCircleColorRes);
			    }
		    Integer targetCircleColorInt(Context context) {
			        return colorResOrInt(context, targetCircleColor, targetCircleColorRes);
			    }
		    Integer dimColorInt(Context context) {
			        return colorResOrInt(context, dimColor, dimColorRes);
			    }
		    Integer titleTextColorInt(Context context) {
			        return colorResOrInt(context, titleTextColor, titleTextColorRes);
			    }
		
		    Integer descriptionTextColorInt(Context context) {
			        return colorResOrInt(context, descriptionTextColor, descriptionTextColorRes);
			    }
		    int titleTextSizePx(Context context) {
			        return dimenOrSize(context, titleTextSize, titleTextDimen);
			    }
		    int descriptionTextSizePx(Context context) {
			        return dimenOrSize(context, descriptionTextSize, descriptionTextDimen);
			    }
		
		    private Integer colorResOrInt(Context context, Integer value,  int resource) {
			        if (resource != -1) {
				            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					                return context.getColor(resource);
					            }
				        }
			        return value;
			    }
		    private int dimenOrSize(Context context, int size,  int dimen) {
			        if (dimen != -1) {
				            return context.getResources().getDimensionPixelSize(dimen);
				        }
			        return UiUtil.sp(context, size);
			    }
	}
			static class TapTargetView extends View {
		    private boolean isDismissed = false;
		    private boolean isDismissing = false;
		    private boolean isInteractable = true;
		
		    final int TARGET_PADDING;
		    final int TARGET_RADIUS;
		    final int TARGET_PULSE_RADIUS;
		    final int TEXT_PADDING;
		    final int TEXT_SPACING;
		    final int TEXT_MAX_WIDTH;
		    final int TEXT_POSITIONING_BIAS;
		    final int CIRCLE_PADDING;
		    final int GUTTER_DIM;
		    final int SHADOW_DIM;
		    final int SHADOW_JITTER_DIM;
		
		
		    final ViewGroup boundingParent;
		    final ViewManager parent;
		    final TapTarget target;
		    final Rect targetBounds;
		
		    final TextPaint titlePaint;
		    final TextPaint descriptionPaint;
		    final Paint outerCirclePaint;
		    final Paint outerCircleShadowPaint;
		    final Paint targetCirclePaint;
		    final Paint targetCirclePulsePaint;
		
		    CharSequence title;
		
		    StaticLayout titleLayout;
		
		    CharSequence description;
		
		    StaticLayout descriptionLayout;
		    boolean isDark;
		    boolean debug;
		    boolean shouldTintTarget;
		    boolean shouldDrawShadow;
		    boolean cancelable;
		    boolean visible;
		
		    // Debug related variables
		
		    SpannableStringBuilder debugStringBuilder;
		
		    DynamicLayout debugLayout;
		
		    TextPaint debugTextPaint;
		
		    Paint debugPaint;
		
		    // Drawing properties
		    Rect drawingBounds;
		    Rect textBounds;
		
		    Path outerCirclePath;
		    float outerCircleRadius;
		    int calculatedOuterCircleRadius;
		    int[] outerCircleCenter;
		    int outerCircleAlpha;
		
		    float targetCirclePulseRadius;
		    int targetCirclePulseAlpha;
		
		    float targetCircleRadius;
		    int targetCircleAlpha;
		
		    int textAlpha;
		    int dimColor;
		
		    float lastTouchX;
		    float lastTouchY;
		
		    int topBoundary;
		    int bottomBoundary;
		
		    Bitmap tintedTarget;
		
		    Listener listener;
		
		
		    ViewOutlineProvider outlineProvider;
		
		    public static TapTargetView showFor(Activity activity, TapTarget target) {
			        return showFor(activity, target, null);
			    }
		
		    public static TapTargetView showFor(Activity activity, TapTarget target, Listener listener) {
			        if (activity == null) throw new IllegalArgumentException("Activity is null");
			
			        final ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
			        final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
			                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			        final ViewGroup content = (ViewGroup) decor.findViewById(android.R.id.content);
			        final TapTargetView tapTargetView = new TapTargetView(activity, decor, content, target, listener);
			        decor.addView(tapTargetView, layoutParams);
			
			        return tapTargetView;
			    }
		
		    public static TapTargetView showFor(Dialog dialog, TapTarget target) {
			        return showFor(dialog, target, null);
			    }
		
		    public static TapTargetView showFor(Dialog dialog, TapTarget target, Listener listener) {
			        if (dialog == null) throw new IllegalArgumentException("Dialog is null");
			
			        final Context context = dialog.getContext();
			        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
			        params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
			        params.format = PixelFormat.RGBA_8888;
			        params.flags = 0;
			        params.gravity = Gravity.START | Gravity.TOP;
			        params.x = 0;
			        params.y = 0;
			        params.width = WindowManager.LayoutParams.MATCH_PARENT;
			        params.height = WindowManager.LayoutParams.MATCH_PARENT;
			
			        final TapTargetView tapTargetView = new TapTargetView(context, windowManager, null, target, listener);
			        windowManager.addView(tapTargetView, params);
			
			        return tapTargetView;
			    }
		
		    public static class Listener {
			        /** Signals that the user has clicked inside of the target **/
			        public void onTargetClick(TapTargetView view) {
				            view.dismiss(true);
				        }
			
			        /** Signals that the user has long clicked inside of the target **/
			        public void onTargetLongClick(TapTargetView view) {
				            onTargetClick(view);
				        }
			
			        /** If cancelable, signals that the user has clicked outside of the outer circle **/
			        public void onTargetCancel(TapTargetView view) {
				            view.dismiss(false);
				        }
			
			        /** Signals that the user clicked on the outer circle portion of the tap target **/
			        public void onOuterCircleClick(TapTargetView view) {
				            // no-op as default
				        }
			
			        /**
         * Signals that the tap target has been dismissed
         * @param userInitiated Whether the user caused this action
         *
         *
         */
			        public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
				        }
			    }
		
		    final FloatValueAnimatorBuilder.UpdateListener expandContractUpdateListener = new FloatValueAnimatorBuilder.UpdateListener() {
			        @Override
			        public void onUpdate(float lerpTime) {
				            final float newOuterCircleRadius = calculatedOuterCircleRadius * lerpTime;
				            final boolean expanding = newOuterCircleRadius > outerCircleRadius;
				            if (!expanding) {
					                // When contracting we need to invalidate the old drawing bounds. Otherwise
					                // you will see artifacts as the circle gets smaller
					                calculateDrawingBounds();
					            }
				
				            final float targetAlpha = target.outerCircleAlpha * 255;
				            outerCircleRadius = newOuterCircleRadius;
				            outerCircleAlpha = (int) Math.min(targetAlpha, (lerpTime * 1.5f * targetAlpha));
				            outerCirclePath.reset();
				            outerCirclePath.addCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, Path.Direction.CW);
				
				            targetCircleAlpha = (int) Math.min(255.0f, (lerpTime * 1.5f * 255.0f));
				
				            if (expanding) {
					                targetCircleRadius = TARGET_RADIUS * Math.min(1.0f, lerpTime * 1.5f);
					            } else {
					                targetCircleRadius = TARGET_RADIUS * lerpTime;
					                targetCirclePulseRadius *= lerpTime;
					            }
				
				            textAlpha = (int) (delayedLerp(lerpTime, 0.7f) * 255);
				
				            if (expanding) {
					                calculateDrawingBounds();
					            }
				
				            invalidateViewAndOutline(drawingBounds);
				        }
			    };
		
		    final ValueAnimator expandAnimation = new FloatValueAnimatorBuilder()
		            .duration(250)
		            .delayBy(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    expandContractUpdateListener.onUpdate(lerpTime);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    pulseAnimation.start();
				                    isInteractable = true;
				                }
			            })
		            .build();
		
		    final ValueAnimator pulseAnimation = new FloatValueAnimatorBuilder()
		            .duration(1000)
		            .repeat(ValueAnimator.INFINITE)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    final float pulseLerp = delayedLerp(lerpTime, 0.5f);
				                    targetCirclePulseRadius = (1.0f + pulseLerp) * TARGET_RADIUS;
				                    targetCirclePulseAlpha = (int) ((1.0f - pulseLerp) * 255);
				                    targetCircleRadius = TARGET_RADIUS + halfwayLerp(lerpTime) * TARGET_PULSE_RADIUS;
				
				                    if (outerCircleRadius != calculatedOuterCircleRadius) {
					                        outerCircleRadius = calculatedOuterCircleRadius;
					                    }
				
				                    calculateDrawingBounds();
				                    invalidateViewAndOutline(drawingBounds);
				                }
			            })
		            .build();
		
		    final ValueAnimator dismissAnimation = new FloatValueAnimatorBuilder(true)
		            .duration(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    expandContractUpdateListener.onUpdate(lerpTime);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    onDismiss(true);
				                    ViewUtil.removeView(parent, TapTargetView.this);
				                }
			            })
		            .build();
		
		    private final ValueAnimator dismissConfirmAnimation = new FloatValueAnimatorBuilder()
		            .duration(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    final float spedUpLerp = Math.min(1.0f, lerpTime * 2.0f);
				                    outerCircleRadius = calculatedOuterCircleRadius * (1.0f + (spedUpLerp * 0.2f));
				                    outerCircleAlpha = (int) ((1.0f - spedUpLerp) * target.outerCircleAlpha * 255.0f);
				                    outerCirclePath.reset();
				                    outerCirclePath.addCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, Path.Direction.CW);
				                    targetCircleRadius = (1.0f - lerpTime) * TARGET_RADIUS;
				                    targetCircleAlpha = (int) ((1.0f - lerpTime) * 255.0f);
				                    targetCirclePulseRadius = (1.0f + lerpTime) * TARGET_RADIUS;
				                    targetCirclePulseAlpha = (int) ((1.0f - lerpTime) * targetCirclePulseAlpha);
				                    textAlpha = (int) ((1.0f - spedUpLerp) * 255.0f);
				                    calculateDrawingBounds();
				                    invalidateViewAndOutline(drawingBounds);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    onDismiss(true);
				                    ViewUtil.removeView(parent, TapTargetView.this);
				                }
			            })
		            .build();
		
		    private ValueAnimator[] animators = new ValueAnimator[]
		            {expandAnimation, pulseAnimation, dismissConfirmAnimation, dismissAnimation};
		
		    private final ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
		    public TapTargetView(final Context context,
		                         final ViewManager parent,
		                          final ViewGroup boundingParent,
		                         final TapTarget target,
		                          final Listener userListener) {
			        super(context);
			        if (target == null) throw new IllegalArgumentException("Target cannot be null");
			
			        this.target = target;
			        this.parent = parent;
			        this.boundingParent = boundingParent;
			        this.listener = userListener != null ? userListener : new Listener();
			        this.title = target.title;
			        this.description = target.description;
			
			        TARGET_PADDING = UiUtil.dp(context, 20);
			        CIRCLE_PADDING = UiUtil.dp(context, 40);
			        TARGET_RADIUS = UiUtil.dp(context, target.targetRadius);
			        TEXT_PADDING = UiUtil.dp(context, 40);
			        TEXT_SPACING = UiUtil.dp(context, 8);
			        TEXT_MAX_WIDTH = UiUtil.dp(context, 360);
			        TEXT_POSITIONING_BIAS = UiUtil.dp(context, 20);
			        GUTTER_DIM = UiUtil.dp(context, 88);
			        SHADOW_DIM = UiUtil.dp(context, 8);
			        SHADOW_JITTER_DIM = UiUtil.dp(context, 1);
			        TARGET_PULSE_RADIUS = (int) (0.1f * TARGET_RADIUS);
			
			        outerCirclePath = new Path();
			        targetBounds = new Rect();
			        drawingBounds = new Rect();
			
			        titlePaint = new TextPaint();
			        titlePaint.setTextSize(target.titleTextSizePx(context));
			        titlePaint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
			        titlePaint.setAntiAlias(true);
			
			        descriptionPaint = new TextPaint();
			        descriptionPaint.setTextSize(target.descriptionTextSizePx(context));
			        descriptionPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
			        descriptionPaint.setAntiAlias(true);
			        descriptionPaint.setAlpha((int) (0.54f * 255.0f));
			
			        outerCirclePaint = new Paint();
			        outerCirclePaint.setAntiAlias(true);
			        outerCirclePaint.setAlpha((int) (target.outerCircleAlpha * 255.0f));
			
			        outerCircleShadowPaint = new Paint();
			        outerCircleShadowPaint.setAntiAlias(true);
			        outerCircleShadowPaint.setAlpha(50);
			        outerCircleShadowPaint.setStyle(Paint.Style.STROKE);
			        outerCircleShadowPaint.setStrokeWidth(SHADOW_JITTER_DIM);
			        outerCircleShadowPaint.setColor(Color.BLACK);
			
			        targetCirclePaint = new Paint();
			        targetCirclePaint.setAntiAlias(true);
			
			        targetCirclePulsePaint = new Paint();
			        targetCirclePulsePaint.setAntiAlias(true);
			
			        applyTargetOptions(context);
			
			        globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
				            @Override
				            public void onGlobalLayout() {
					                if (isDismissing) {
						                    return;
						                }
					                updateTextLayouts();
					                target.onReady(new Runnable() {
						                    @Override
						                    public void run() {
							                        final int[] offset = new int[2];
							
							                        targetBounds.set(target.bounds());
							
							                        getLocationOnScreen(offset);
							                        targetBounds.offset(-offset[0], -offset[1]);
							
							                        if (boundingParent != null) {
								                            final WindowManager windowManager
								                                    = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
								                            final DisplayMetrics displayMetrics = new DisplayMetrics();
								                            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
								
								                            final Rect rect = new Rect();
								                            boundingParent.getWindowVisibleDisplayFrame(rect);
								
								                            // We bound the boundaries to be within the screen's coordinates to
								                            // handle the case where the layout bounds do not match
								                            // (like when FLAG_LAYOUT_NO_LIMITS is specified)
								                            topBoundary = Math.max(0, rect.top);
								                            bottomBoundary = Math.min(rect.bottom, displayMetrics.heightPixels);
								                        }
							
							                        drawTintedTarget();
							                        requestFocus();
							                        calculateDimensions();
							
							                        startExpandAnimation();
							                    }
						                });
					            }
				        };
			
			        getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
			
			        setFocusableInTouchMode(true);
			        setClickable(true);
			        setOnClickListener(new OnClickListener() {
				            @Override
				            public void onClick(View v) {
					                if (listener == null || outerCircleCenter == null || !isInteractable) return;
					
					                final boolean clickedInTarget =
					                        distance(targetBounds.centerX(), targetBounds.centerY(), (int) lastTouchX, (int) lastTouchY) <= targetCircleRadius;
					                final double distanceToOuterCircleCenter = distance(outerCircleCenter[0], outerCircleCenter[1],
					                        (int) lastTouchX, (int) lastTouchY);
					                final boolean clickedInsideOfOuterCircle = distanceToOuterCircleCenter <= outerCircleRadius;
					
					                if (clickedInTarget) {
						                    isInteractable = false;
						                    listener.onTargetClick(TapTargetView.this);
						                } else if (clickedInsideOfOuterCircle) {
						                    listener.onOuterCircleClick(TapTargetView.this);
						                } else if (cancelable) {
						                    isInteractable = false;
						                    listener.onTargetCancel(TapTargetView.this);
						                }
					            }
				        });
			
			        setOnLongClickListener(new OnLongClickListener() {
				            @Override
				            public boolean onLongClick(View v) {
					                if (listener == null) return false;
					
					                if (targetBounds.contains((int) lastTouchX, (int) lastTouchY)) {
						                    listener.onTargetLongClick(TapTargetView.this);
						                    return true;
						                }
					
					                return false;
					            }
				        });
			    }
		
		    private void startExpandAnimation() {
			        if (!visible) {
				            isInteractable = false;
				            expandAnimation.start();
				            visible = true;
				        }
			    }
		
		    protected void applyTargetOptions(Context context) {
			        shouldTintTarget = target.tintTarget;
			        shouldDrawShadow = target.drawShadow;
			        cancelable = target.cancelable;
			
			        // We can't clip out portions of a view outline, so if the user specified a transparent
			        // target, we need to fallback to drawing a jittered shadow approximation
			        if (shouldDrawShadow && Build.VERSION.SDK_INT >= 21 && !target.transparentTarget) {
				            outlineProvider = new ViewOutlineProvider() {
					                @Override
					                public void getOutline(View view, Outline outline) {
						                    if (outerCircleCenter == null) return;
						                    outline.setOval(
						                            (int) (outerCircleCenter[0] - outerCircleRadius), (int) (outerCircleCenter[1] - outerCircleRadius),
						                            (int) (outerCircleCenter[0] + outerCircleRadius), (int) (outerCircleCenter[1] + outerCircleRadius));
						                    outline.setAlpha(outerCircleAlpha / 255.0f);
						                    if (Build.VERSION.SDK_INT >= 22) {
							                        outline.offset(0, SHADOW_DIM);
							                    }
						                }
					            };
				
				            setOutlineProvider(outlineProvider);
				            setElevation(SHADOW_DIM);
				        }
			
			        if (shouldDrawShadow && outlineProvider == null && Build.VERSION.SDK_INT < 18) {
				            setLayerType(LAYER_TYPE_SOFTWARE, null);
				        } else {
				            setLayerType(LAYER_TYPE_HARDWARE, null);
				        }
			
			        final android.content.res.Resources.Theme theme = context.getTheme();
			        isDark = UiUtil.themeIntAttr(context, "isLightTheme") == 0;
			
			        final Integer outerCircleColor = target.outerCircleColorInt(context);
			        if (outerCircleColor != null) {
				            outerCirclePaint.setColor(outerCircleColor);
				        } else if (theme != null) {
				            outerCirclePaint.setColor(UiUtil.themeIntAttr(context, "colorPrimary"));
				        } else {
				            outerCirclePaint.setColor(Color.WHITE);
				        }
			
			        final Integer targetCircleColor = target.targetCircleColorInt(context);
			        if (targetCircleColor != null) {
				            targetCirclePaint.setColor(targetCircleColor);
				        } else {
				            targetCirclePaint.setColor(isDark ? Color.BLACK : Color.WHITE);
				        }
			
			        if (target.transparentTarget) {
				            targetCirclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
				        }
			
			        targetCirclePulsePaint.setColor(targetCirclePaint.getColor());
			
			        final Integer targetDimColor = target.dimColorInt(context);
			        if (targetDimColor != null) {
				            dimColor = UiUtil.setAlpha(targetDimColor, 0.3f);
				        } else {
				            dimColor = -1;
				        }
			
			        final Integer titleTextColor = target.titleTextColorInt(context);
			        if (titleTextColor != null) {
				            titlePaint.setColor(titleTextColor);
				        } else {
				            titlePaint.setColor(isDark ? Color.BLACK : Color.WHITE);
				        }
			
			        final Integer descriptionTextColor = target.descriptionTextColorInt(context);
			        if (descriptionTextColor != null) {
				            descriptionPaint.setColor(descriptionTextColor);
				        } else {
				            descriptionPaint.setColor(titlePaint.getColor());
				        }
			
			        if (target.titleTypeface != null) {
				            titlePaint.setTypeface(target.titleTypeface);
				        }
			
			        if (target.descriptionTypeface != null) {
				            descriptionPaint.setTypeface(target.descriptionTypeface);
				        }
			    }
		
		    @Override
		    protected void onDetachedFromWindow() {
			        super.onDetachedFromWindow();
			        onDismiss(false);
			    }
		
		    void onDismiss(boolean userInitiated) {
			        if (isDismissed) return;
			
			        isDismissing = false;
			        isDismissed = true;
			
			        for (final ValueAnimator animator : animators) {
				            animator.cancel();
				            animator.removeAllUpdateListeners();
				        }
			        ViewUtil.removeOnGlobalLayoutListener(getViewTreeObserver(), globalLayoutListener);
			        visible = false;
			
			        if (listener != null) {
				            listener.onTargetDismissed(this, userInitiated);
				        }
			    }
		
		    @Override
		    protected void onDraw(Canvas c) {
			        if (isDismissed || outerCircleCenter == null) return;
			
			        if (topBoundary > 0 && bottomBoundary > 0) {
				            c.clipRect(0, topBoundary, getWidth(), bottomBoundary);
				        }
			
			        if (dimColor != -1) {
				            c.drawColor(dimColor);
				        }
			
			        int saveCount;
			        outerCirclePaint.setAlpha(outerCircleAlpha);
			        if (shouldDrawShadow && outlineProvider == null) {
				            saveCount = c.save();
				            {
					                c.clipPath(outerCirclePath, Region.Op.DIFFERENCE);
					                drawJitteredShadow(c);
					            }
				            c.restoreToCount(saveCount);
				        }
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, outerCirclePaint);
			
			        targetCirclePaint.setAlpha(targetCircleAlpha);
			        if (targetCirclePulseAlpha > 0) {
				            targetCirclePulsePaint.setAlpha(targetCirclePulseAlpha);
				            c.drawCircle(targetBounds.centerX(), targetBounds.centerY(),
				                    targetCirclePulseRadius, targetCirclePulsePaint);
				        }
			        c.drawCircle(targetBounds.centerX(), targetBounds.centerY(),
			                targetCircleRadius, targetCirclePaint);
			
			        saveCount = c.save();
			        {
				            c.translate(textBounds.left, textBounds.top);
				            titlePaint.setAlpha(textAlpha);
				            if (titleLayout != null) {
					                titleLayout.draw(c);
					            }
				
				            if (descriptionLayout != null && titleLayout != null) {
					                c.translate(0, titleLayout.getHeight() + TEXT_SPACING);
					                descriptionPaint.setAlpha((int) (target.descriptionTextAlpha * textAlpha));
					                descriptionLayout.draw(c);
					            }
				        }
			        c.restoreToCount(saveCount);
			
			        saveCount = c.save();
			        {
				            if (tintedTarget != null) {
					                c.translate(targetBounds.centerX() - tintedTarget.getWidth() / 2,
					                        targetBounds.centerY() - tintedTarget.getHeight() / 2);
					                c.drawBitmap(tintedTarget, 0, 0, targetCirclePaint);
					            } else if (target.icon != null) {
					                c.translate(targetBounds.centerX() - target.icon.getBounds().width() / 2,
					                        targetBounds.centerY() - target.icon.getBounds().height() / 2);
					                target.icon.setAlpha(targetCirclePaint.getAlpha());
					                target.icon.draw(c);
					            }
				        }
			        c.restoreToCount(saveCount);
			
			        if (debug) {
				            drawDebugInformation(c);
				        }
			    }
		
		    @Override
		    public boolean onTouchEvent(MotionEvent e) {
			        lastTouchX = e.getX();
			        lastTouchY = e.getY();
			        return super.onTouchEvent(e);
			    }
		
		    @Override
		    public boolean onKeyDown(int keyCode, KeyEvent event) {
			        if (isVisible() && cancelable && keyCode == KeyEvent.KEYCODE_BACK) {
				            event.startTracking();
				            return true;
				        }
			
			        return false;
			    }
		
		    @Override
		    public boolean onKeyUp(int keyCode, KeyEvent event) {
			        if (isVisible() && isInteractable && cancelable
			                && keyCode == KeyEvent.KEYCODE_BACK && event.isTracking() && !event.isCanceled()) {
				            isInteractable = false;
				
				            if (listener != null) {
					                listener.onTargetCancel(this);
					            } else {
					                new Listener().onTargetCancel(this);
					            }
				
				            return true;
				        }
			
			        return false;
			    }
		
		    /**
     * Dismiss this view
     * @param tappedTarget If the user tapped the target or not
     *                     (results in different dismiss animations)
     */
		    public void dismiss(boolean tappedTarget) {
			        isDismissing = true;
			        pulseAnimation.cancel();
			        expandAnimation.cancel();
			        if (tappedTarget) {
				            dismissConfirmAnimation.start();
				        } else {
				            dismissAnimation.start();
				        }
			    }
		
		    /** Specify whether to draw a wireframe around the view, useful for debugging **/
		    public void setDrawDebug(boolean status) {
			        if (debug != status) {
				            debug = status;
				            postInvalidate();
				        }
			    }
		
		    /** Returns whether this view is visible or not **/
		    public boolean isVisible() {
			        return !isDismissed && visible;
			    }
		
		    void drawJitteredShadow(Canvas c) {
			        final float baseAlpha = 0.20f * outerCircleAlpha;
			        outerCircleShadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			        outerCircleShadowPaint.setAlpha((int) baseAlpha);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1] + SHADOW_DIM, outerCircleRadius, outerCircleShadowPaint);
			        outerCircleShadowPaint.setStyle(Paint.Style.STROKE);
			        final int numJitters = 7;
			        for (int i = numJitters - 1; i > 0; --i) {
				            outerCircleShadowPaint.setAlpha((int) ((i / (float) numJitters) * baseAlpha));
				            c.drawCircle(outerCircleCenter[0], outerCircleCenter[1] + SHADOW_DIM ,
				                    outerCircleRadius + (numJitters - i) * SHADOW_JITTER_DIM , outerCircleShadowPaint);
				        }
			    }
		
		    void drawDebugInformation(Canvas c) {
			        if (debugPaint == null) {
				            debugPaint = new Paint();
				            debugPaint.setARGB(255, 255, 0, 0);
				            debugPaint.setStyle(Paint.Style.STROKE);
				            debugPaint.setStrokeWidth(UiUtil.dp(getContext(), 1));
				        }
			
			        if (debugTextPaint == null) {
				            debugTextPaint = new TextPaint();
				            debugTextPaint.setColor(0xFFFF0000);
				            debugTextPaint.setTextSize(UiUtil.sp(getContext(), 16));
				        }
			
			        // Draw wireframe
			        debugPaint.setStyle(Paint.Style.STROKE);
			        c.drawRect(textBounds, debugPaint);
			        c.drawRect(targetBounds, debugPaint);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], 10, debugPaint);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], calculatedOuterCircleRadius - CIRCLE_PADDING, debugPaint);
			        c.drawCircle(targetBounds.centerX(), targetBounds.centerY(), TARGET_RADIUS + TARGET_PADDING, debugPaint);
			
			        // Draw positions and dimensions
			        debugPaint.setStyle(Paint.Style.FILL);
			        final String debugText =
			                "Text bounds: " + textBounds.toShortString() + "n" +
			                        "Target bounds: " + targetBounds.toShortString() + "n" +
			                        "Center: " + outerCircleCenter[0] + " " + outerCircleCenter[1] + "n" +
			                        "View size: " + getWidth() + " " + getHeight() + "n" +
			                        "Target bounds: " + targetBounds.toShortString();
			
			        if (debugStringBuilder == null) {
				            debugStringBuilder = new SpannableStringBuilder(debugText);
				        } else {
				            debugStringBuilder.clear();
				            debugStringBuilder.append(debugText);
				        }
			
			        if (debugLayout == null) {
				            debugLayout = new DynamicLayout(debugText, debugTextPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
				        }
			
			        final int saveCount = c.save();
			        {
				            debugPaint.setARGB(220, 0, 0, 0);
				            c.translate(0.0f, topBoundary);
				            c.drawRect(0.0f, 0.0f, debugLayout.getWidth(), debugLayout.getHeight(), debugPaint);
				            debugPaint.setARGB(255, 255, 0, 0);
				            debugLayout.draw(c);
				        }
			        c.restoreToCount(saveCount);
			    }
		
		    void drawTintedTarget() {
			        final android.graphics.drawable.Drawable icon = target.icon;
			        if (!shouldTintTarget || icon == null) {
				            tintedTarget = null;
				            return;
				        }
			
			        if (tintedTarget != null) return;
			
			        tintedTarget = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(),
			                Bitmap.Config.ARGB_8888);
			        final Canvas canvas = new Canvas(tintedTarget);
			        icon.setColorFilter(new PorterDuffColorFilter(
			                outerCirclePaint.getColor(), PorterDuff.Mode.SRC_ATOP));
			        icon.draw(canvas);
			        icon.setColorFilter(null);
			    }
		
		    void updateTextLayouts() {
			        final int textWidth = Math.min(getWidth(), TEXT_MAX_WIDTH) - TEXT_PADDING * 2;
			        if (textWidth <= 0) {
				            return;
				        }
			
			        titleLayout = new StaticLayout(title, titlePaint, textWidth,
			                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
			
			        if (description != null) {
				            descriptionLayout = new StaticLayout(description, descriptionPaint, textWidth,
				                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
				        } else {
				            descriptionLayout = null;
				        }
			    }
		
		    float halfwayLerp(float lerp) {
			        if (lerp < 0.5f) {
				            return lerp / 0.5f;
				        }
			
			        return (1.0f - lerp) / 0.5f;
			    }
		
		    float delayedLerp(float lerp, float threshold) {
			        if (lerp < threshold) {
				            return 0.0f;
				        }
			
			        return (lerp - threshold) / (1.0f - threshold);
			    }
		
		    void calculateDimensions() {
			        textBounds = getTextBounds();
			        outerCircleCenter = getOuterCircleCenterPoint();
			        calculatedOuterCircleRadius = getOuterCircleRadius(outerCircleCenter[0], outerCircleCenter[1], textBounds, targetBounds);
			    }
		
		    void calculateDrawingBounds() {
			        if (outerCircleCenter == null) {
				            // Called dismiss before we got a chance to display the tap target
				            // So we have no center -> cant determine the drawing bounds
				            return;
				        }
			        drawingBounds.left = (int) Math.max(0, outerCircleCenter[0] - outerCircleRadius);
			        drawingBounds.top = (int) Math.min(0, outerCircleCenter[1] - outerCircleRadius);
			        drawingBounds.right = (int) Math.min(getWidth(),
			                outerCircleCenter[0] + outerCircleRadius + CIRCLE_PADDING);
			        drawingBounds.bottom = (int) Math.min(getHeight(),
			                outerCircleCenter[1] + outerCircleRadius + CIRCLE_PADDING);
			    }
		
		    int getOuterCircleRadius(int centerX, int centerY, Rect textBounds, Rect targetBounds) {
			        final int targetCenterX = targetBounds.centerX();
			        final int targetCenterY = targetBounds.centerY();
			        final int expandedRadius = (int) (1.1f * TARGET_RADIUS);
			        final Rect expandedBounds = new Rect(targetCenterX, targetCenterY, targetCenterX, targetCenterY);
			        expandedBounds.inset(-expandedRadius, -expandedRadius);
			
			        final int textRadius = maxDistanceToPoints(centerX, centerY, textBounds);
			        final int targetRadius = maxDistanceToPoints(centerX, centerY, expandedBounds);
			        return Math.max(textRadius, targetRadius) + CIRCLE_PADDING;
			    }
		
		    Rect getTextBounds() {
			        final int totalTextHeight = getTotalTextHeight();
			        final int totalTextWidth = getTotalTextWidth();
			
			        final int possibleTop = targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight;
			        final int top;
			        if (possibleTop > topBoundary) {
				            top = possibleTop;
				        } else {
				            top = targetBounds.centerY() + TARGET_RADIUS + TARGET_PADDING;
				        }
			
			        final int relativeCenterDistance = (getWidth() / 2) - targetBounds.centerX();
			        final int bias = relativeCenterDistance < 0 ? -TEXT_POSITIONING_BIAS : TEXT_POSITIONING_BIAS;
			        final int left = Math.max(TEXT_PADDING, targetBounds.centerX() - bias - totalTextWidth);
			        final int right = Math.min(getWidth() - TEXT_PADDING, left + totalTextWidth);
			        return new Rect(left, top, right, top + totalTextHeight);
			    }
		
		    int[] getOuterCircleCenterPoint() {
			        if (inGutter(targetBounds.centerY())) {
				            return new int[]{targetBounds.centerX(), targetBounds.centerY()};
				        }
			
			        final int targetRadius = Math.max(targetBounds.width(), targetBounds.height()) / 2 + TARGET_PADDING;
			        final int totalTextHeight = getTotalTextHeight();
			
			        final boolean onTop = targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight > 0;
			
			        final int left = Math.min(textBounds.left, targetBounds.left - targetRadius);
			        final int right = Math.max(textBounds.right, targetBounds.right + targetRadius);
			        final int titleHeight = titleLayout == null ? 0 : titleLayout.getHeight();
			        final int centerY = onTop ?
			                targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight + titleHeight
			                :
			                targetBounds.centerY() + TARGET_RADIUS + TARGET_PADDING + titleHeight;
			
			        return new int[] { (left + right) / 2, centerY };
			    }
		
		    int getTotalTextHeight() {
			        if (titleLayout == null) {
				            return 0;
				        }
			
			        if (descriptionLayout == null) {
				            return titleLayout.getHeight() + TEXT_SPACING;
				        }
			
			        return titleLayout.getHeight() + descriptionLayout.getHeight() + TEXT_SPACING;
			    }
		
		    int getTotalTextWidth() {
			        if (titleLayout == null) {
				            return 0;
				        }
			
			        if (descriptionLayout == null) {
				            return titleLayout.getWidth();
				        }
			
			        return Math.max(titleLayout.getWidth(), descriptionLayout.getWidth());
			    }
		    boolean inGutter(int y) {
			        if (bottomBoundary > 0) {
				            return y < GUTTER_DIM || y > bottomBoundary - GUTTER_DIM;
				        } else {
				            return y < GUTTER_DIM || y > getHeight() - GUTTER_DIM;
				        }
			    }
		    int maxDistanceToPoints(int x1, int y1, Rect bounds) {
			        final double tl = distance(x1, y1, bounds.left, bounds.top);
			        final double tr = distance(x1, y1, bounds.right, bounds.top);
			        final double bl = distance(x1, y1, bounds.left, bounds.bottom);
			        final double br = distance(x1, y1, bounds.right, bounds.bottom);
			        return (int) Math.max(tl, Math.max(tr, Math.max(bl, br)));
			    }
		    double distance(int x1, int y1, int x2, int y2) {
			        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
			    }
		    void invalidateViewAndOutline(Rect bounds) {
			        invalidate(bounds);
			        if (outlineProvider != null && Build.VERSION.SDK_INT >= 21) {
				            invalidateOutline();
				        }
			    }
	}
			static class ViewUtil {
		
		    ViewUtil() {}
		
		    private static boolean isLaidOut(View view) {
			        return true;
			    }
		    static void onLaidOut(final View view, final Runnable runnable) {
			        if (isLaidOut(view)) {
				            runnable.run();
				            return;
				        }
			        final ViewTreeObserver observer = view.getViewTreeObserver();
			        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
				            @Override
				            public void onGlobalLayout() {
					                final ViewTreeObserver trueObserver;
					                if (observer.isAlive()) {
						                    trueObserver = observer;
						                } else {
						                    trueObserver = view.getViewTreeObserver();
						                }
					                removeOnGlobalLayoutListener(trueObserver, this);
					                runnable.run();
					            }
				        });
			    }
		    @SuppressWarnings("deprecation")
		    static void removeOnGlobalLayoutListener(ViewTreeObserver observer,
		                                             ViewTreeObserver.OnGlobalLayoutListener listener) {
			        if (Build.VERSION.SDK_INT >= 16) {
				            observer.removeOnGlobalLayoutListener(listener);
				        } else {
				            observer.removeGlobalOnLayoutListener(listener);
				        }
			    }
		    static void removeView(ViewManager parent, View child) {
			        if (parent == null || child == null) {
				            return;
				        }
			        try {
				            parent.removeView(child);
				        } catch (Exception ignored) {
				        }
			    }
	}
			static class ViewTapTarget extends TapTarget {
		    final View view;
		
		    ViewTapTarget(View view, CharSequence title,  CharSequence description) {
			        super(title, description);
			        if (view == null) {
				            throw new IllegalArgumentException("Given null view to target");
				        }
			        this.view = view;
			    }
		
		    @Override
		    public void onReady(final Runnable runnable) {
			        ViewUtil.onLaidOut(view, new Runnable() {
				            @Override
				            public void run() {
					                // Cache bounds
					                final int[] location = new int[2];
					                view.getLocationOnScreen(location);
					                bounds = new Rect(location[0], location[1],
					                        location[0] + view.getWidth(), location[1] + view.getHeight());
					
					                if (icon == null && view.getWidth() > 0 && view.getHeight() > 0) {
						                    final Bitmap viewBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
						                    final Canvas canvas = new Canvas(viewBitmap);
						                    view.draw(canvas);
						                    icon = new android.graphics.drawable.BitmapDrawable(view.getContext().getResources(), viewBitmap);
						                    icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
						                }
					
					                runnable.run();
					            }
				        });
			    }
	}
			static class TapTargetSequence {
		    private final Activity activity;
		    private final Dialog dialog;
		    private final Queue<TapTarget> targets;
		    private boolean active;
		    private TapTargetView currentView;
		    Listener listener;
		    boolean considerOuterCircleCanceled;
		    boolean continueOnCancel;
		    public interface Listener {
			        void onSequenceFinish();
			        void onSequenceStep(TapTarget lastTarget, boolean targetClicked);
			        void onSequenceCanceled(TapTarget lastTarget);
			    }
		    public TapTargetSequence(Activity activity) {
			        if (activity == null) throw new IllegalArgumentException("Activity is null");
			        this.activity = activity;
			        this.dialog = null;
			        this.targets = new LinkedList<>();
			    }
		    public TapTargetSequence(Dialog dialog) {
			        if (dialog == null) throw new IllegalArgumentException("Given null Dialog");
			        this.dialog = dialog;
			        this.activity = null;
			        this.targets = new LinkedList<>();
			    }
		    public TapTargetSequence targets(List<TapTarget> targets) {
			        this.targets.addAll(targets);
			        return this;
			    }
		    public TapTargetSequence targets(TapTarget... targets) {
			        Collections.addAll(this.targets, targets);
			        return this;
			    }
		    public TapTargetSequence target(TapTarget target) {
			        this.targets.add(target);
			        return this;
			    }
		    public TapTargetSequence continueOnCancel(boolean status) {
			        this.continueOnCancel = status;
			        return this;
			    }
		    public TapTargetSequence considerOuterCircleCanceled(boolean status) {
			        this.considerOuterCircleCanceled = status;
			        return this;
			    }
		    public TapTargetSequence listener(Listener listener) {
			        this.listener = listener;
			        return this;
			    }
		    public void start() {
			        if (targets.isEmpty() || active) {
				            return;
				        }
			        active = true;
			        showNext();
			    }
		    public void startWith(int targetId) {
			        if (active) {
				            return;
				        }
			        while (targets.peek() != null && targets.peek().id() != targetId) {
				            targets.poll();
				        }
			        TapTarget peekedTarget = targets.peek();
			        if (peekedTarget == null || peekedTarget.id() != targetId) {
				            throw new IllegalStateException("Given target " + targetId + " not in sequence");
				        }
			        start();
			    }
		    public void startAt(int index) {
			        if (active) {
				            return;
				        }
			        if (index < 0 || index >= targets.size()) {
				            throw new IllegalArgumentException("Given invalid index " + index);
				        }
			        final int expectedSize = targets.size() - index;
			        while (targets.peek() != null && targets.size() != expectedSize) {
				            targets.poll();
				        }
			        if (targets.size() != expectedSize) {
				            throw new IllegalStateException("Given index " + index + " not in sequence");
				        }
			        start();
			    }
		    public boolean cancel() {
			        if (targets.isEmpty() || !active) {
				            return false;
				        }
			        if (currentView == null || !currentView.cancelable) {
				            return false;
				        }
			        currentView.dismiss(false);
			        active = false;
			        targets.clear();
			        if (listener != null) {
				            listener.onSequenceCanceled(currentView.target);
				        }
			        return true;
			    }
		    void showNext() {
			        try {
				            TapTarget tapTarget = targets.remove();
				            if (activity != null) {
					                currentView = TapTargetView.showFor(activity, tapTarget, tapTargetListener);
					            } else {
					                currentView = TapTargetView.showFor(dialog, tapTarget, tapTargetListener);
					            }
				        } catch (NoSuchElementException e) {
				            // No more targets
				            if (listener != null) {
					                listener.onSequenceFinish();
					            }
				        }
			    }
		    private final TapTargetView.Listener tapTargetListener = new TapTargetView.Listener() {
			        @Override
			        public void onTargetClick(TapTargetView view) {
				            super.onTargetClick(view);
				            if (listener != null) {
					                listener.onSequenceStep(view.target, true);
					            }
				            showNext();
				        }
			        @Override
			        public void onOuterCircleClick(TapTargetView view) {
				            if (considerOuterCircleCanceled) {
					                onTargetCancel(view);
					            }
				        }
			        @Override
			        public void onTargetCancel(TapTargetView view) {
				            super.onTargetCancel(view);
				            if (continueOnCancel) {
					                if (listener != null) {
						                    listener.onSequenceStep(view.target, false);
						                }
					                showNext();
					            } else {
					                if (listener != null) {
						                    listener.onSequenceCanceled(view.target);
						                }
					            }
				        }
			    };
	}
	
	
	private void _ShareApp (final String _name) {
		
		String apk = "";
		String uri = (_name);
		
		try {
			android.content.pm.PackageInfo pi = getPackageManager().getPackageInfo(uri, android.content.pm.PackageManager.GET_ACTIVITIES);
			
			apk = pi.applicationInfo.publicSourceDir;
		} catch (Exception e) {
			showMessage(e.toString());
		}
		Intent iten = new Intent(Intent.ACTION_SEND);
		iten.setType("*/*");
		iten.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new java.io.File(apk)));
		
		startActivity(Intent.createChooser(iten, "Send APK"));
	}
	
	
	private void _UpdatifyComponent (final String _response) {
		try {
			String UpdatifyCurrVer = "0";
			String UpdatifyColor = "0";
			android.content.pm.PackageInfo packageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
			UpdatifyCurrVer = packageInfo.versionName;
			UpdatifyMap = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
			String updatifyContent1 = (UpdatifyMap.get("dialogTitle").toString());
			String updatifyContent2 = (UpdatifyMap.get("dialogSubtitle").toString());
			String updatifyContent3 = (UpdatifyMap.get("dialogBtnExtraTxt").toString());
			String updatifyContent4 = (UpdatifyMap.get("dialogBtnMainTxt").toString());
			if (UpdatifyMap.get("dialogOption").toString().equals("warning")) {
				UpdatifyColor = "#BD081C";
			}
			else {
				if (UpdatifyMap.get("dialogOption").toString().equals("update")) {
					UpdatifyColor = "#0084FF";
				}
				else {
					UpdatifyColor = "#00B489";
				}
			}
			if (UpdatifyMap.get("newVersion").toString().equals(UpdatifyCurrVer)) {
				//first check, nothing happens because latest version is installed
			}
			else {
				if (UpdatifyMap.get("isOneTime").toString().equals("true")) {
					if ((UCSP.getString("isOneTime", "").equals(UpdatifyMap.get("isOneTimeKey").toString()))) {
						//dialog will not show because it is one time and already showed
					}
					else {
						UCSP.edit().putString("isOneTime", UpdatifyMap.get("isOneTimeKey").toString()).commit();
						final LinearLayout main_layout = new LinearLayout(this);
						main_layout.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f));
						main_layout.setPadding(0,0,0,0);
						main_layout.setOrientation(LinearLayout.VERTICAL);
						main_layout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
						AlertDialog.Builder iBuilder = new AlertDialog.Builder(this);
						
						iBuilder.setView(main_layout);
						final AlertDialog nDialog = iBuilder.create();
						nDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
						android.graphics.drawable.GradientDrawable wg1 = new android.graphics.drawable.GradientDrawable();
						wg1.setColor(Color.parseColor(UpdatifyColor));
						wg1.setCornerRadius(100);
						final LinearLayout linear_1 = new LinearLayout(this);
						linear_1.setLayoutParams(new LinearLayout.LayoutParams(175,175, 0.0f));
						linear_1.setPadding(0,0,0,0);
						linear_1.setOrientation(LinearLayout.VERTICAL);
						linear_1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
						linear_1.setBackground(wg1);
						main_layout.addView(linear_1);
						android.graphics.drawable.GradientDrawable wg0 = new android.graphics.drawable.GradientDrawable();
						wg0.setColor(Color.parseColor("#ffffffff"));
						wg0.setCornerRadius(60);
						LinearLayout.LayoutParams lp1= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT); 
						lp1.setMargins(60,0,60,0);
						final LinearLayout linear_55 = new LinearLayout(this);
						linear_55.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f));
						linear_55.setPadding(45,140,45,45);
						linear_55.setLayoutParams(lp1);
						linear_55.setOrientation(LinearLayout.VERTICAL);
						linear_55.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
						linear_55.setBackground(wg0);
						main_layout.addView(linear_55);
						linear_55.setTranslationY((float)(-87.5d));
						final TextView textview_3 = new TextView(this);
						textview_3.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f));
						textview_3.setPadding(0,0,0,0);
						textview_3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
						textview_3.setText(updatifyContent1);
						textview_3.setTextSize(15);
						textview_3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 0);
						textview_3.setTextColor(Color.parseColor("#FF616161"));
						textview_3.setSingleLine(true);
						linear_55.addView(textview_3);
						final LinearLayout linear_71 = new LinearLayout(this);
						linear_71.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,15, 0.0f));
						linear_71.setPadding(10,10,10,10);
						linear_71.setOrientation(LinearLayout.HORIZONTAL);
						linear_71.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
						linear_55.addView(linear_71);
						final TextView textview_4 = new TextView(this);
						textview_4.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f));
						textview_4.setPadding(0,0,0,0);
						textview_4.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
						textview_4.setText(updatifyContent2);
						textview_4.setTextSize(13);
						textview_4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 0);
						textview_4.setTextColor(Color.parseColor("#FFBDBDBD"));
						linear_55.addView(textview_4);
						final ImageView img1 = new ImageView(this);
						img1.setLayoutParams(new LinearLayout.LayoutParams(90,90, 0.0f));
						img1.setPadding(0,0,0,0);
						if (UpdatifyMap.get("dialogOption").toString().equals("warning")) {
							img1.setImageResource(R.drawable.update);
						}
						else {
							if (UpdatifyMap.get("dialogOption").toString().equals("update")) {
								img1.setImageResource(R.drawable.upload);
							}
							else {
								img1.setImageResource(R.drawable.chat1);
							}
						}
						img1.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF")}));
						linear_1.addView(img1);
						//separator
						if (UpdatifyMap.get("dialogBtnExtra").toString().equals("true")) {
							final LinearLayout linear_5 = new LinearLayout(this);
							linear_5.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,50, 0.0f));
							linear_5.setPadding(8,8,8,8);
							linear_5.setOrientation(LinearLayout.HORIZONTAL);
							linear_55.addView(linear_5);
							final TextView textview_5 = new TextView(this);
							textview_5.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,100, 01.0f));
							textview_5.setPadding(16,8,16,8);
							textview_5.setText(updatifyContent3);
							textview_5.setTextSize(13);
							textview_5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 0);
							textview_5.setTextColor(Color.parseColor("#FF757575"));
							textview_5.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
							linear_55.addView(textview_5);
							
							android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
							GG.setColor(Color.parseColor("#FFFFFF"));
							GG.setCornerRadius((float)100);
							GG.setStroke((int) 0,
							Color.parseColor(UpdatifyColor));
							android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#EEEEEE")}), GG, null);
							textview_5.setBackground(RE);
							textview_5.setOnClickListener(new OnClickListener() { 
								public void onClick(View v) {
									if (UpdatifyMap.get("dialogBtnExtraClick").toString().equals("exit")) {
										finishAffinity();
									}
									else {
										if (UpdatifyMap.get("dialogBtnExtraClick").toString().equals("browser")) {
											if (UpdatifyMap.get("isCancelable").toString().equals("true")) {
												try {
													Intent UpdatifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UpdatifyMap.get("openLinkExtra").toString()));
													startActivity(UpdatifyIntent);
												} catch(Exception e) {
													SketchwareUtil.showMessage(getApplicationContext(), (e.toString()));
												}
											}
											else {
												if (UpdatifyMap.get("isCancelable").toString().equals("false")) {
													try {
														Intent UpdatifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UpdatifyMap.get("openLinkExtra").toString()));
														startActivity(UpdatifyIntent);
													} catch(Exception e) {
														SketchwareUtil.showMessage(getApplicationContext(), (e.toString()));
														finishAffinity();
													}
												}
												else {
													SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [CANCEL]");
												}
											}
										}
										else {
											if (UpdatifyMap.get("dialogBtnExtraClick").toString().equals("dismiss")) {
												nDialog.dismiss();
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [DISMISS]");
											}
										}
									}
								} 
							});
						}
						if (UpdatifyMap.get("dialogBtnMain").toString().equals("true")) {
							//separator
							final LinearLayout linear_512 = new LinearLayout(this);
							linear_512.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,30, 0.0f));
							linear_512.setPadding(8,8,8,8);
							linear_512.setOrientation(LinearLayout.HORIZONTAL);
							linear_55.addView(linear_512);
							final TextView textview_6 = new TextView(this);
							textview_6.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,100, 0.0f));
							textview_6.setPadding(16,8,16,8);
							textview_6.setText(updatifyContent4);
							textview_6.setTextSize(13);
							textview_6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 0);
							textview_6.setTextColor(Color.parseColor("#FFFFFF"));
							textview_6.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
							linear_55.addView(textview_6);
							
							android.graphics.drawable.GradientDrawable GG1 = new android.graphics.drawable.GradientDrawable();
							GG1.setColor(Color.parseColor(UpdatifyColor));
							GG1.setCornerRadius((float)100);
							GG1.setStroke((int) 0,
							Color.parseColor(UpdatifyColor));
							android.graphics.drawable.RippleDrawable RE1 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#EEEEEE")}), GG1, null);
							textview_6.setBackground(RE1);
							//separator
							textview_6.setOnClickListener(new OnClickListener() { 
								public void onClick(View v) {
									if (UpdatifyMap.get("dialogBtnMainClick").toString().equals("exit")) {
										finishAffinity();
									}
									else {
										if (UpdatifyMap.get("dialogBtnMainClick").toString().equals("browser")) {
											if (UpdatifyMap.get("isCancelable").toString().equals("true")) {
												try {
													Intent UpdatifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UpdatifyMap.get("openLinkMain").toString()));
													startActivity(UpdatifyIntent);
													finishAffinity();
												} catch(Exception e) {
													SketchwareUtil.showMessage(getApplicationContext(), (e.toString()));
												}
											}
											else {
												if (UpdatifyMap.get("isCancelable").toString().equals("false")) {
													try {
														Intent UpdatifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UpdatifyMap.get("openLinkMain").toString()));
														startActivity(UpdatifyIntent);
													} catch(Exception e) {
														SketchwareUtil.showMessage(getApplicationContext(), (e.toString()));
													}
												}
												else {
													SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [CANCEL]");
												}
											}
										}
										else {
											if (UpdatifyMap.get("dialogBtnMainClick").toString().equals("dismiss")) {
												nDialog.dismiss();
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [DISMISS]");
											}
										}
									}
								} 
							});
						}
						//separator
						linear_1.setElevation((float)2);
						nDialog.show();
						if (UpdatifyMap.get("isCancelable").toString().equals("false")) {
							nDialog.setCancelable(false);
						}
						else {
							if (UpdatifyMap.get("isCancelable").toString().equals("true")) {
								nDialog.setCancelable(true);
							}
							else {
								SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [CANCEL]");
							}
						}
					}
				}
				else {
					final LinearLayout main_layout = new LinearLayout(this);
					main_layout.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f));
					main_layout.setPadding(0,0,0,0);
					main_layout.setOrientation(LinearLayout.VERTICAL);
					main_layout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
					AlertDialog.Builder iBuilder = new AlertDialog.Builder(this);
					
					iBuilder.setView(main_layout);
					final AlertDialog nDialog = iBuilder.create();
					nDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
					android.graphics.drawable.GradientDrawable wg1 = new android.graphics.drawable.GradientDrawable();
					wg1.setColor(Color.parseColor(UpdatifyColor));
					wg1.setCornerRadius(100);
					final LinearLayout linear_1 = new LinearLayout(this);
					linear_1.setLayoutParams(new LinearLayout.LayoutParams(175,175, 0.0f));
					linear_1.setPadding(0,0,0,0);
					linear_1.setOrientation(LinearLayout.VERTICAL);
					linear_1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
					linear_1.setBackground(wg1);
					main_layout.addView(linear_1);
					android.graphics.drawable.GradientDrawable wg0 = new android.graphics.drawable.GradientDrawable();
					wg0.setColor(Color.parseColor("#ffffffff"));
					wg0.setCornerRadius(60);
					LinearLayout.LayoutParams lp1= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT); 
					lp1.setMargins(60,0,60,0);
					final LinearLayout linear_55 = new LinearLayout(this);
					linear_55.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f));
					linear_55.setPadding(45,140,45,45);
					linear_55.setLayoutParams(lp1);
					linear_55.setOrientation(LinearLayout.VERTICAL);
					linear_55.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
					linear_55.setBackground(wg0);
					main_layout.addView(linear_55);
					linear_55.setTranslationY((float)(-87.5d));
					final TextView textview_3 = new TextView(this);
					textview_3.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f));
					textview_3.setPadding(0,0,0,0);
					textview_3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
					textview_3.setText(updatifyContent1);
					textview_3.setTextSize(15);
					textview_3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 0);
					textview_3.setTextColor(Color.parseColor("#FF616161"));
					textview_3.setSingleLine(true);
					linear_55.addView(textview_3);
					
					final LinearLayout linear_71 = new LinearLayout(this);
					linear_71.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,15, 0.0f));
					linear_71.setPadding(10,10,10,10);
					linear_71.setOrientation(LinearLayout.HORIZONTAL);
					linear_71.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
					linear_55.addView(linear_71);
					final TextView textview_4 = new TextView(this);
					textview_4.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f));
					textview_4.setPadding(0,0,0,0);
					textview_4.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
					textview_4.setText(updatifyContent2);
					textview_4.setTextSize(13);
					textview_4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 0);
					textview_4.setTextColor(Color.parseColor("#FFBDBDBD"));
					linear_55.addView(textview_4);
					final ImageView img1 = new ImageView(this);
					img1.setLayoutParams(new LinearLayout.LayoutParams(90,90, 0.0f));
					img1.setPadding(0,0,0,0);
					if (UpdatifyMap.get("dialogOption").toString().equals("warning")) {
						img1.setImageResource(R.drawable.update);
					}
					else {
						if (UpdatifyMap.get("dialogOption").toString().equals("update")) {
							img1.setImageResource(R.drawable.upload);
						}
						else {
							img1.setImageResource(R.drawable.chat1);
						}
					}
					img1.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF")}));
					linear_1.addView(img1);
					//separator
					if (UpdatifyMap.get("dialogBtnExtra").toString().equals("true")) {
						final LinearLayout linear_5 = new LinearLayout(this);
						linear_5.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,50, 0.0f));
						linear_5.setPadding(8,8,8,8);
						linear_5.setOrientation(LinearLayout.HORIZONTAL);
						linear_55.addView(linear_5);
						final TextView textview_5 = new TextView(this);
						textview_5.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,100, 01.0f));
						textview_5.setPadding(16,8,16,8);
						textview_5.setText(updatifyContent3);
						textview_5.setTextSize(13);
						textview_5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 0);
						textview_5.setTextColor(Color.parseColor("#FF757575"));
						textview_5.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
						linear_55.addView(textview_5);
						android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
						GG.setColor(Color.parseColor("#FFFFFF"));
						GG.setCornerRadius((float)100);
						GG.setStroke((int) 0,
						Color.parseColor(UpdatifyColor));
						android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#EEEEEE")}), GG, null);
						textview_5.setBackground(RE);
						textview_5.setOnClickListener(new OnClickListener() { 
							public void onClick(View v) {
								if (UpdatifyMap.get("dialogBtnExtraClick").toString().equals("exit")) {
									finishAffinity();
								}
								else {
									if (UpdatifyMap.get("dialogBtnExtraClick").toString().equals("browser")) {
										if (UpdatifyMap.get("isCancelable").toString().equals("true")) {
											try {
												Intent UpdatifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UpdatifyMap.get("openLinkExtra").toString()));
												startActivity(UpdatifyIntent);
											} catch(Exception e) {
												SketchwareUtil.showMessage(getApplicationContext(), (e.toString()));
											}
										}
										else {
											if (UpdatifyMap.get("isCancelable").toString().equals("false")) {
												try {
													Intent UpdatifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UpdatifyMap.get("openLinkExtra").toString()));
													startActivity(UpdatifyIntent);
													finishAffinity();
												} catch(Exception e) {
													SketchwareUtil.showMessage(getApplicationContext(), (e.toString()));
												}
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [CANCEL]");
											}
										}
									}
									else {
										if (UpdatifyMap.get("dialogBtnExtraClick").toString().equals("dismiss")) {
											nDialog.dismiss();
										}
										else {
											SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [DISMISS]");
										}
									}
								}
							} 
						});
					}
					if (UpdatifyMap.get("dialogBtnMain").toString().equals("true")) {
						//separator
						final LinearLayout linear_512 = new LinearLayout(this);
						linear_512.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,30, 0.0f));
						linear_512.setPadding(8,8,8,8);
						linear_512.setOrientation(LinearLayout.HORIZONTAL);
						linear_55.addView(linear_512);
						final TextView textview_6 = new TextView(this);
						textview_6.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,100, 0.0f));
						textview_6.setPadding(16,8,16,8);
						textview_6.setText(updatifyContent4);
						textview_6.setTextSize(13);
						textview_6.setTextColor(Color.parseColor("#FFFFFF"));
						textview_6.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
						linear_55.addView(textview_6);
						textview_6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/droidsans.ttf"), 0);
						android.graphics.drawable.GradientDrawable GG1 = new android.graphics.drawable.GradientDrawable();
						GG1.setColor(Color.parseColor(UpdatifyColor));
						GG1.setCornerRadius((float)100);
						GG1.setStroke((int) 0,
						Color.parseColor(UpdatifyColor));
						android.graphics.drawable.RippleDrawable RE1 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#EEEEEE")}), GG1, null);
						textview_6.setBackground(RE1);
						//separator
						textview_6.setOnClickListener(new OnClickListener() { 
							public void onClick(View v) {
								if (UpdatifyMap.get("dialogBtnMainClick").toString().equals("exit")) {
									finishAffinity();
								}
								else {
									if (UpdatifyMap.get("dialogBtnMainClick").toString().equals("browser")) {
										if (UpdatifyMap.get("isCancelable").toString().equals("true")) {
											try {
												Intent UpdatifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UpdatifyMap.get("openLinkMain").toString()));
												startActivity(UpdatifyIntent);
												finishAffinity();
											} catch(Exception e) {
												SketchwareUtil.showMessage(getApplicationContext(), (e.toString()));
											}
										}
										else {
											if (UpdatifyMap.get("isCancelable").toString().equals("false")) {
												try {
													Intent UpdatifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UpdatifyMap.get("openLinkMain").toString()));
													startActivity(UpdatifyIntent);
												} catch(Exception e) {
													SketchwareUtil.showMessage(getApplicationContext(), (e.toString()));
												}
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [CANCEL]");
											}
										}
									}
									else {
										if (UpdatifyMap.get("dialogBtnMainClick").toString().equals("dismiss")) {
											nDialog.dismiss();
										}
										else {
											SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [DISMISS]");
										}
									}
								}
							} 
						});
					}
					//separator
					linear_1.setElevation((float)2);
					nDialog.show();
					if (UpdatifyMap.get("isCancelable").toString().equals("false")) {
						nDialog.setCancelable(false);
					}
					else {
						if (UpdatifyMap.get("isCancelable").toString().equals("true")) {
							nDialog.setCancelable(true);
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "Updatify error [CANCEL]");
						}
					}
				}
			}
		} catch(Exception e) {
			SketchwareUtil.showMessage(getApplicationContext(), (e.toString()));
		}
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
