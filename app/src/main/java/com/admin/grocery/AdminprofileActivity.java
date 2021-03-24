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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Switch;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.widget.CompoundButton;
import android.view.View;
import java.text.DecimalFormat;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class AdminprofileActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private HashMap<String, Object> map = new HashMap<>();
	private String cust_id = "";
	private double i = 0;
	private HashMap<String, Object> adminlist = new HashMap<>();
	private String url = "";
	private String file_path = "";
	private String strAdd = "";
	private String strCity = "";
	private String strState = "";
	private String strCountry = "";
	private String strKN = "";
	private String strPC = "";
	private HashMap<String, Object> shopmap = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> adminlistmap = new ArrayList<>();
	private ArrayList<String> file = new ArrayList<>();
	
	private LinearLayout linear18;
	private ScrollView vscroll4;
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linear15;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private LinearLayout geo;
	private LinearLayout geo2;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout other_details;
	private LinearLayout linear16;
	private LinearLayout linear14;
	private ImageView shoplogo;
	private TextView textview14;
	private EditText shopname;
	private TextView textview2;
	private EditText name;
	private TextView textview4;
	private EditText mob;
	private Switch switch1;
	private ImageView imageview4;
	private TextView textview6;
	private TextView lat;
	private TextView textview8;
	private TextView lon;
	private EditText city;
	private EditText state;
	private EditText country;
	private TextView textview10;
	private EditText address;
	private TextView textview11;
	private EditText other;
	private Switch switch2;
	private TextView textview12;
	private EditText delivery_charges;
	private TextView textview13;
	private EditText radius;
	private TextView textview15;
	private EditText merch_id;
	private TextView stop;
	private TextView save;
	
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
	private DatabaseReference admin_details = _firebase.getReference("admin_details");
	private ChildEventListener _admin_details_child_listener;
	private SharedPreferences ref;
	private LocationManager loc;
	private LocationListener _loc_location_listener;
	private DatabaseReference shop_details = _firebase.getReference("shop_details");
	private ChildEventListener _shop_details_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.adminprofile);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
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
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		vscroll4 = (ScrollView) findViewById(R.id.vscroll4);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		geo = (LinearLayout) findViewById(R.id.geo);
		geo2 = (LinearLayout) findViewById(R.id.geo2);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		other_details = (LinearLayout) findViewById(R.id.other_details);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		shoplogo = (ImageView) findViewById(R.id.shoplogo);
		textview14 = (TextView) findViewById(R.id.textview14);
		shopname = (EditText) findViewById(R.id.shopname);
		textview2 = (TextView) findViewById(R.id.textview2);
		name = (EditText) findViewById(R.id.name);
		textview4 = (TextView) findViewById(R.id.textview4);
		mob = (EditText) findViewById(R.id.mob);
		switch1 = (Switch) findViewById(R.id.switch1);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview6 = (TextView) findViewById(R.id.textview6);
		lat = (TextView) findViewById(R.id.lat);
		textview8 = (TextView) findViewById(R.id.textview8);
		lon = (TextView) findViewById(R.id.lon);
		city = (EditText) findViewById(R.id.city);
		state = (EditText) findViewById(R.id.state);
		country = (EditText) findViewById(R.id.country);
		textview10 = (TextView) findViewById(R.id.textview10);
		address = (EditText) findViewById(R.id.address);
		textview11 = (TextView) findViewById(R.id.textview11);
		other = (EditText) findViewById(R.id.other);
		switch2 = (Switch) findViewById(R.id.switch2);
		textview12 = (TextView) findViewById(R.id.textview12);
		delivery_charges = (EditText) findViewById(R.id.delivery_charges);
		textview13 = (TextView) findViewById(R.id.textview13);
		radius = (EditText) findViewById(R.id.radius);
		textview15 = (TextView) findViewById(R.id.textview15);
		merch_id = (EditText) findViewById(R.id.merch_id);
		stop = (TextView) findViewById(R.id.stop);
		save = (TextView) findViewById(R.id.save);
		auth = FirebaseAuth.getInstance();
		ref = getSharedPreferences("ref", Activity.MODE_PRIVATE);
		loc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		switch1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (switch1.isChecked()) {
					geo.setVisibility(View.VISIBLE);
					geo2.setVisibility(View.VISIBLE);
				}
				else {
					geo.setVisibility(View.GONE);
					geo2.setVisibility(View.GONE);
				}
			}
		});
		
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2)  {
				final boolean _isChecked = _param2;
				
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (ContextCompat.checkSelfPermission(AdminprofileActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
					loc.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, _loc_location_listener);
				}
			}
		});
		
		switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2)  {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					other_details.setVisibility(View.VISIBLE);
				}
				else {
					other_details.setVisibility(View.GONE);
				}
			}
		});
		
		stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if ((shopname.getText().toString().length() > 0) && ((mob.getText().toString().length() > 0) && (name.getText().toString().length() > 0))) {
					map = new HashMap<>();
					map.put("Username", name.getText().toString());
					map.put("Usermob", mob.getText().toString());
					map.put("Address", address.getText().toString());
					map.put("Othernumber", other.getText().toString());
					map.put("City", city.getText().toString());
					map.put("State", state.getText().toString());
					map.put("Country", country.getText().toString());
					map.put("DeliveryCharges", delivery_charges.getText().toString());
					map.put("Lat", lat.getText().toString());
					map.put("Lon", lon.getText().toString());
					map.put("radius", radius.getText().toString());
					map.put("Mail_id", FirebaseAuth.getInstance().getCurrentUser().getEmail());
					map.put("Uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("Shopname", shopname.getText().toString());
					map.put("Merchant_id", merch_id.getText().toString());
					admin_details.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
					if ((lat.getText().toString().length() > 0) && (lon.getText().toString().length() > 0)) {
						shopmap = new HashMap<>();
						shopmap.put("Title", shopname.getText().toString());
						shopmap.put("Lat", lat.getText().toString());
						shopmap.put("Lon", lon.getText().toString());
						shopmap.put("Merchant_id", merch_id.getText().toString());
						shop_details.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(shopmap);
					}
					ref.edit().putString("Shopname", shopname.getText().toString()).commit();
					finish();
				}
				else {
					mob.setError("Enter valid mobile number") ;
					name.setError("Enter valid user name") ;
					shopname.setError("shop name can not be null") ;
					SketchwareUtil.showMessage(getApplicationContext(), "Please enter valid mobile number, shop name and owner name ");
				}
			}
		});
		
		_admin_details_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		admin_details.addChildEventListener(_admin_details_child_listener);
		
		_loc_location_listener = new LocationListener() {
			@Override
			public void onLocationChanged(Location _param1) {
				final double _lat = _param1.getLatitude();
				final double _lng = _param1.getLongitude();
				final double _acc = _param1.getAccuracy();
				lat.setText(String.valueOf(_lat));
				lon.setText(String.valueOf(_lng));
				_getLocation(_lat, _lng);
				city.setText(strCity);
				state.setText(strState);
				country.setText(strCountry);
				address.setText(strAdd);
			}
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {}
			@Override
			public void onProviderEnabled(String provider) {}
			@Override
			public void onProviderDisabled(String provider) {}
		};
		
		_shop_details_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		shop_details.addChildEventListener(_shop_details_child_listener);
		
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
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	private void initializeLogic() {
		switch1.setChecked(true);
		switch2.setChecked(true);
		save.setVisibility(View.VISIBLE);
		stop.setVisibility(View.VISIBLE);
		geo.setVisibility(View.VISIBLE);
		geo2.setVisibility(View.VISIBLE);
		other_details.setVisibility(View.GONE);
		if (!ref.getString("Lang", "").equals("English")) {
			textview8.setText("మీ పేరు");
			textview4.setText("మొబైల్ సంఖ్య");
			textview10.setText("ఇంటి చిరునామ");
			textview13.setText("సమీపంలో బాగా తెలిసిన ప్రదేశం");
			textview11.setText("ప్రత్యామ్నాయ మొబైల్");
			save.setText("సేవ్");
			stop.setText("రద్దు చేయండి");
		}
		if (ContextCompat.checkSelfPermission(AdminprofileActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			loc.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, _loc_location_listener);
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
		map.clear();
		i = 0;
		admin_details.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				adminlistmap = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						adminlistmap.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				for(int _repeat49 = 0; _repeat49 < (int)(adminlistmap.size()); _repeat49++) {
					if (adminlistmap.get((int)i).get("Uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						map = adminlistmap.get((int)i);
					}
					else {
						i++;
					}
				}
				if (map.size() > 0) {
					shopname.setText(map.get("Shopname").toString());
					name.setText(map.get("Username").toString());
					mob.setText(map.get("Usermob").toString());
					address.setText(map.get("Address").toString());
					other.setText(map.get("Othernumber").toString());
					city.setText(map.get("City").toString());
					state.setText(map.get("State").toString());
					country.setText(map.get("Country").toString());
					delivery_charges.setText(map.get("DeliveryCharges").toString());
					lat.setText(map.get("Lat").toString());
					lon.setText(map.get("Lon").toString());
					radius.setText(map.get("radius").toString());
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	private void _getLocation (final double _LATITUDE, final double _LONGITUDE) {
		android.location.Geocoder geocoder = new android.location.Geocoder(getApplicationContext(), Locale.getDefault());
		
		try {
			List<android.location.Address> addresses = geocoder.getFromLocation(_LATITUDE, _LONGITUDE, 1);
			if (addresses != null) {
				android.location.Address returnedAddress = addresses.get(0);
				StringBuilder strReturnedAddress = new StringBuilder("");
				StringBuilder strReturnedCity = new StringBuilder("");
				StringBuilder strReturnedState = new StringBuilder("");
				StringBuilder strReturnedCountry = new StringBuilder("");
				StringBuilder strReturnedPC = new StringBuilder("");
				StringBuilder strReturnedKN = new StringBuilder("");
				for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
					strReturnedAddress.append(returnedAddress.getAddressLine(i));
					strReturnedCity.append(returnedAddress.getLocality()); 
					strReturnedState.append(returnedAddress.getAdminArea());
					strReturnedCountry.append(returnedAddress.getCountryName());
					strReturnedPC.append(returnedAddress.getPostalCode());
					strReturnedKN.append(returnedAddress.getFeatureName());
				}
				strAdd = strReturnedAddress.toString();
				strCity = strReturnedCity.toString();
				strState = strReturnedState.toString();
				strCountry = strReturnedCountry.toString();
				strPC = strReturnedPC.toString();
				strKN = strReturnedKN.toString();
			}
			else
			{
				strAdd = "No Address returned";
				strCity = "No City returned";
				strState = "No State returned";
				strCountry = "No Country returned";
				strPC = "No Postal Code returned";
				strKN = "No Know Name returned";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			strAdd = "Can't get Address";
			strCity = "Can't get City";
			strState = "Can't get State";
			strCountry = "Can't get Country";
			strPC = "Can't get Postal Code";
			strKN = "Can't get Know Name";
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
