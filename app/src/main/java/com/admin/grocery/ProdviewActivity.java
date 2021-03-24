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
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Continuation;
import android.net.Uri;
import java.io.File;
import android.content.Intent;
import android.content.ClipData;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import com.bumptech.glide.Glide;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class ProdviewActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private HashMap<String, Object> productmap = new HashMap<>();
	private String keyid = "";
	private String POS = "";
	private double position = 0;
	private String filep = "";
	private String url = "";
	private double i = 0;
	private String temp_img_path = "";
	
	private ArrayList<HashMap<String, Object>> productlist = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	private ArrayList<String> categorylist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> cat_temp = new ArrayList<>();
	private ArrayList<String> keyids = new ArrayList<>();
	
	private LinearLayout linear1;
	private Button button1;
	private LinearLayout linear2;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private TextView textview1;
	private EditText tilte;
	private Spinner spinner1;
	private TextView textview7;
	private EditText category;
	private TextView textview8;
	private EditText prod_desc;
	private TextView textview9;
	private EditText price;
	private TextView textview12;
	private EditText wt;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear15;
	private LinearLayout linear14;
	private TextView textview11;
	private EditText qty;
	private ImageView imageview1;
	private Button button2;
	private Button button3;
	private ImageView imageview2;
	
	private DatabaseReference Products = _firebase.getReference("Products");
	private ChildEventListener _Products_child_listener;
	private FirebaseAuth fireauth;
	private OnCompleteListener<Void> fireauth_updateEmailListener;
	private OnCompleteListener<Void> fireauth_updatePasswordListener;
	private OnCompleteListener<Void> fireauth_emailVerificationSentListener;
	private OnCompleteListener<Void> fireauth_deleteUserListener;
	private OnCompleteListener<Void> fireauth_updateProfileListener;
	private OnCompleteListener<AuthResult> fireauth_phoneAuthListener;
	private OnCompleteListener<AuthResult> fireauth_googleSignInListener;
	private OnCompleteListener<AuthResult> _fireauth_create_user_listener;
	private OnCompleteListener<AuthResult> _fireauth_sign_in_listener;
	private OnCompleteListener<Void> _fireauth_reset_password_listener;
	private StorageReference products_img = _firebase_storage.getReference("products_img");
	private OnCompleteListener<Uri> _products_img_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _products_img_download_success_listener;
	private OnSuccessListener _products_img_delete_success_listener;
	private OnProgressListener _products_img_upload_progress_listener;
	private OnProgressListener _products_img_download_progress_listener;
	private OnFailureListener _products_img_failure_listener;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private SharedPreferences ref;
	private DatabaseReference New_Products = _firebase.getReference("New_Products");
	private ChildEventListener _New_Products_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.prodview);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		button1 = (Button) findViewById(R.id.button1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		textview1 = (TextView) findViewById(R.id.textview1);
		tilte = (EditText) findViewById(R.id.tilte);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		textview7 = (TextView) findViewById(R.id.textview7);
		category = (EditText) findViewById(R.id.category);
		textview8 = (TextView) findViewById(R.id.textview8);
		prod_desc = (EditText) findViewById(R.id.prod_desc);
		textview9 = (TextView) findViewById(R.id.textview9);
		price = (EditText) findViewById(R.id.price);
		textview12 = (TextView) findViewById(R.id.textview12);
		wt = (EditText) findViewById(R.id.wt);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		textview11 = (TextView) findViewById(R.id.textview11);
		qty = (EditText) findViewById(R.id.qty);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		fireauth = FirebaseAuth.getInstance();
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		ref = getSharedPreferences("ref", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				tilte.setText(cat_temp.get((int)_position).get("Title").toString());
				category.setText(cat_temp.get((int)_position).get("Type").toString());
				prod_desc.setText(cat_temp.get((int)_position).get("Desc").toString());
				wt.setText(cat_temp.get((int)_position).get("weight").toString());
				Glide.with(getApplicationContext()).load(Uri.parse(cat_temp.get((int)_position).get("Path").toString())).into(imageview2);
				temp_img_path = cat_temp.get((int)_position).get("Path").toString();
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (url.length() > 0) {
					products_img.child(keyid).putFile(Uri.fromFile(new File(url))).addOnFailureListener(_products_img_failure_listener).addOnProgressListener(_products_img_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
						@Override
						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
							return products_img.child(keyid).getDownloadUrl();
						}}).addOnCompleteListener(_products_img_upload_success_listener);
				}
				else {
					productmap = new HashMap<>();
					productmap.put("title", tilte.getText().toString());
					productmap.put("type", category.getText().toString());
					productmap.put("description", prod_desc.getText().toString());
					productmap.put("price", price.getText().toString());
					productmap.put("filename", temp_img_path);
					productmap.put("rating", qty.getText().toString());
					productmap.put("UOM", wt.getText().toString());
					productmap.put("created_by", FirebaseAuth.getInstance().getCurrentUser().getUid());
					productmap.put("Shopname", ref.getString("Shopname", ""));
					Products.child(keyid).updateChildren(productmap);
					finish();
				}
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		_Products_child_listener = new ChildEventListener() {
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
		Products.addChildEventListener(_Products_child_listener);
		
		_products_img_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), String.valueOf((long)(_progressValue)));
			}
		};
		
		_products_img_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_products_img_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				productmap = new HashMap<>();
				productmap.put("title", tilte.getText().toString());
				productmap.put("type", category.getText().toString());
				productmap.put("description", prod_desc.getText().toString());
				productmap.put("price", price.getText().toString());
				productmap.put("filename", _downloadUrl);
				productmap.put("rating", qty.getText().toString());
				productmap.put("UOM", wt.getText().toString());
				productmap.put("created_by", FirebaseAuth.getInstance().getCurrentUser().getUid());
				productmap.put("Shopname", ref.getString("Shopname", ""));
				Products.child(keyid).updateChildren(productmap);
				finish();
			}
		};
		
		_products_img_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_products_img_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_products_img_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_New_Products_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				keyids.add(_childKey);
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
		New_Products.addChildEventListener(_New_Products_child_listener);
		
		fireauth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fireauth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fireauth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fireauth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fireauth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		fireauth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fireauth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_fireauth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fireauth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fireauth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	private void initializeLogic() {
		url = "";
		if (getIntent().getStringExtra("POS").equals("NEW")) {
			Products.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					productlist = new ArrayList<>();
					try {
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							productlist.add(_map);
						}
					}
					catch (Exception _e) {
						_e.printStackTrace();
					}
					keyid = Products.push().getKey();
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		}
		else {
			Products.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					productlist = new ArrayList<>();
					try {
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							productlist.add(_map);
						}
					}
					catch (Exception _e) {
						_e.printStackTrace();
					}
					i = 0;
					str.clear();
					for (DataSnapshot dshot:
					_dataSnapshot.getChildren()) {
						str.add(dshot.getKey()) ;
					}
					if (productlist.size() > 0) {
						for(int _repeat102 = 0; _repeat102 < (int)(productlist.size()); _repeat102++) {
							if (str.get((int)(i)).equals(getIntent().getStringExtra("POS"))) {
								position = i;
								keyid = str.get((int)(position));
								tilte.setText(productlist.get((int)position).get("title").toString());
								category.setText(productlist.get((int)position).get("type").toString());
								prod_desc.setText(productlist.get((int)position).get("description").toString());
								price.setText(productlist.get((int)position).get("price").toString());
								Glide.with(getApplicationContext()).load(Uri.parse(productlist.get((int)position).get("filename").toString())).into(imageview2);
								temp_img_path = productlist.get((int)position).get("filename").toString();
								qty.setText(productlist.get((int)position).get("rating").toString());
								wt.setText(productlist.get((int)position).get("UOM").toString());
							}
							else {
								
							}
							i++;
						}
					}
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				url = _filePath.get((int)(0));
				imageview1.setImageResource(R.drawable.ic_done_black);
			}
			else {
				
			}
			break;
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
		i = 0;
		spinner1.setVisibility(View.GONE);
		if (getIntent().getStringExtra("POS").equals("NEW")) {
			New_Products.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					cat_temp = new ArrayList<>();
					try {
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							cat_temp.add(_map);
						}
					}
					catch (Exception _e) {
						_e.printStackTrace();
					}
					for(int _repeat40 = 0; _repeat40 < (int)(cat_temp.size()); _repeat40++) {
						categorylist.add(cat_temp.get((int)i).get("Title").toString());
						i++;
					}
					spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, categorylist));
					((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
			spinner1.setVisibility(View.VISIBLE);
		}
		if (!ref.getString("Lang", "").equals("English")) {
			textview1.setText("క్రొత్త ఉత్పత్తిని జోడించండి");
			textview7.setText("ఆహార వర్గం రకం");
			textview1.setText("ఉత్పత్తి యొక్క శీర్షిక");
			textview8.setText("ఉత్పత్తి వివరణ");
			textview9.setText("ధర");
			
			textview11.setText("ఉత్పత్తి యొక్క బరువు");
			button2.setText("సేవ్");
			button3.setText("రద్దు చేయండి");
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
