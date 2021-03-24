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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BillActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private String orderid = "";
	private double i = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private String orderstr = "";
	private double ordercount = 0;
	private boolean f = false;
	private String imagename = "";
	private double adj_cost = 0;
	private String items = "";
	private double cus_mobile = 0;
	private String location = "";
	private String cust_name = "";
	private String sms = "";
	private double shipping_charges = 0;
	private String message = "";
	
	private ArrayList<HashMap<String, Object>> validorderlist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> currentorder = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	
	private LinearLayout linear14;
	private LinearLayout linear1;
	private Button button1;
	private Button button2;
	private LinearLayout linear3;
	private LinearLayout linear16;
	private LinearLayout linear6;
	private LinearLayout linear8;
	private LinearLayout linear10;
	private ListView listview1;
	private LinearLayout linear11;
	private LinearLayout shipping;
	private LinearLayout linear17;
	private LinearLayout linear2;
	private ImageView imageview3;
	private TextView address;
	private TextView textview4;
	private TextView textview5;
	private TextView textview14;
	private TextView custname;
	private ImageView imageview2;
	private TextView contactnum;
	private TextView textview13;
	private TextView orderidtxt;
	private TextView textview15;
	private TextView ordertime;
	private TextView textview7;
	private TextView textview8;
	private TextView textview18;
	private TextView textview11;
	private TextView textview9;
	private TextView totalprice;
	private TextView textview17;
	private TextView ship_cost;
	private TextView textview16;
	private LinearLayout linear18;
	private TextView owner_status;
	private TextView cust_status;
	
	private DatabaseReference valid_orders = _firebase.getReference("valid_orders");
	private ChildEventListener _valid_orders_child_listener;
	private Intent in = new Intent();
	private AlertDialog.Builder d;
	private Intent mess = new Intent();
	private Calendar c = Calendar.getInstance();
	private SharedPreferences ref;
	private AlertDialog.Builder d_w;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.bill);
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
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		listview1 = (ListView) findViewById(R.id.listview1);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		shipping = (LinearLayout) findViewById(R.id.shipping);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		address = (TextView) findViewById(R.id.address);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview5 = (TextView) findViewById(R.id.textview5);
		textview14 = (TextView) findViewById(R.id.textview14);
		custname = (TextView) findViewById(R.id.custname);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		contactnum = (TextView) findViewById(R.id.contactnum);
		textview13 = (TextView) findViewById(R.id.textview13);
		orderidtxt = (TextView) findViewById(R.id.orderidtxt);
		textview15 = (TextView) findViewById(R.id.textview15);
		ordertime = (TextView) findViewById(R.id.ordertime);
		textview7 = (TextView) findViewById(R.id.textview7);
		textview8 = (TextView) findViewById(R.id.textview8);
		textview18 = (TextView) findViewById(R.id.textview18);
		textview11 = (TextView) findViewById(R.id.textview11);
		textview9 = (TextView) findViewById(R.id.textview9);
		totalprice = (TextView) findViewById(R.id.totalprice);
		textview17 = (TextView) findViewById(R.id.textview17);
		ship_cost = (TextView) findViewById(R.id.ship_cost);
		textview16 = (TextView) findViewById(R.id.textview16);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		owner_status = (TextView) findViewById(R.id.owner_status);
		cust_status = (TextView) findViewById(R.id.cust_status);
		d = new AlertDialog.Builder(this);
		ref = getSharedPreferences("ref", Activity.MODE_PRIVATE);
		d_w = new AlertDialog.Builder(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d_w.setTitle("Customer Invoice");
				d_w.setMessage("How would you like to send the Invoice to customer? ");
				d_w.setPositiveButton("WhatApp", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						_pdf();
					}
				});
				d_w.setNegativeButton("SMS", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						sms = "Online Grocery Store : Thanks for shopping with us! Your order is confirmed and will be shipped once you pay the amount on Google Pay or phone pay. ";
						mess.setAction(Intent.ACTION_VIEW);
						mess.setData(Uri.parse("sms:".concat(String.valueOf((long)(cus_mobile)))));
						mess.putExtra("sms_body", "Hi ".concat(cust_name.concat(", ")).concat(sms.concat("Order_id:".concat(orderid.concat("Total cart value :".concat(String.valueOf((long)(adj_cost))))))));
						startActivity(mess);
						finish();
					}
				});
				map.clear();
				map = new HashMap<>();
				map.put("Flag", "1");
				valid_orders.child(orderid).updateChildren(map);
				d_w.create().show();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				map.clear();
				map = new HashMap<>();
				map.put("Flag", "0");
				valid_orders.child(orderid).updateChildren(map);
				finish();
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				map.clear();
				d.setTitle("Alert Dialog!!! ");
				d.setMessage("Would you like to delete the item? ");
				d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						currentorder.remove((int)(_position));
						i = 0;
						adj_cost = 0;
						items = "";
						if (currentorder.size() > 0) {
							for(int _repeat60 = 0; _repeat60 < (int)(currentorder.size()); _repeat60++) {
								adj_cost = adj_cost + Double.parseDouble(currentorder.get((int)i).get("price").toString());
								if (items.length() > 0) {
									items = items.concat(",".concat(currentorder.get((int)i).get("title").toString()));
								}
								else {
									items = currentorder.get((int)i).get("title").toString();
								}
								i++;
							}
						}
						map.put("Items", items);
						map.put("Orderstr", new Gson().toJson(currentorder));
						map.put("Totalamt", String.valueOf((long)(adj_cost)));
						map.put("Totalitems", String.valueOf((long)(currentorder.size())));
						valid_orders.child(orderid).updateChildren(map);
					}
				});
				d.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
				return true;
			}
		});
		
		_valid_orders_child_listener = new ChildEventListener() {
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
				finish();
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
		valid_orders.addChildEventListener(_valid_orders_child_listener);
	}
	private void initializeLogic() {
		c = Calendar.getInstance();
		textview5.setText(new SimpleDateFormat("dd-MMM-yyyy").format(c.getTime()));
		if (!ref.getString("Lang", "").equals("English")) {
			textview4.setText("ఇన్వాయిస్ బిల్");
			textview14.setText("కస్టమ్ పేరు:");
			textview13.setText("ఆర్డర్ గుర్తింపు సంఖ్యా");
			textview7.setText("అంశాల");
			textview8.setText("వివరణ");
			textview18.setText("మొత్తము");
			textview11.setText("ధర");
			textview9.setText("మొత్తం: ₹ ");
			textview17.setText("డెలివరీ ఛార్జీలు:");
		}
		message = "Please find the attached bill for your online Grocery shopping ";
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
		shipping_charges = 20;
		orderid = getIntent().getStringExtra("orderid");
		if (orderid.length() > 0) {
			valid_orders.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					validorderlist = new ArrayList<>();
					try {
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							validorderlist.add(_map);
						}
					}
					catch (Exception _e) {
						_e.printStackTrace();
					}
					str.clear();
					currentorder.clear();
					for (DataSnapshot dshot:
					_dataSnapshot.getChildren()) {
						str.add(dshot.getKey()) ;
					}
					if (str.size() > 0) {
						i = 0;
						for(int _repeat24 = 0; _repeat24 < (int)(str.size()); _repeat24++) {
							if (str.get((int)(i)).equals(getIntent().getStringExtra("orderid"))) {
								map = validorderlist.get((int)i);
								orderstr = validorderlist.get((int)i).get("Orderstr").toString();
								ordercount = Double.parseDouble(validorderlist.get((int)i).get("Totalitems").toString());
								currentorder = new Gson().fromJson(orderstr, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
							}
							i++;
						}
					}
					if (orderid.length() > 0) {
						orderidtxt.setText(orderid);
						totalprice.setText(map.get("Totalamt").toString());
						custname.setText(map.get("Username").toString());
						address.setText(map.get("Loc").toString());
						adj_cost = Double.parseDouble(map.get("Totalamt").toString());
						cust_name = map.get("Username").toString();
						cus_mobile = Double.parseDouble(map.get("Cust_num").toString());
						location = map.get("Loc").toString();
						contactnum.setText(map.get("Cust_num").toString());
						ordertime.setText(map.get("Ordertime").toString());
						if (Double.parseDouble(map.get("Flag").toString()) == 1) {
							owner_status.setText("Order approved by shop owner. ");
						}
						else {
							owner_status.setText("Order is pending. ");
						}
						listview1.setAdapter(new Listview1Adapter(currentorder));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						shipping.setVisibility(View.GONE);
						if (Double.parseDouble(map.get("Totalamt").toString()) < 300) {
							adj_cost = Double.parseDouble(map.get("Totalamt").toString()) + shipping_charges;
							shipping.setVisibility(View.VISIBLE);
							ship_cost.setText(String.valueOf((long)(shipping_charges)));
						}
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "No orders created");
						finish();
					}
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		}
		else {
			finish();
		}
	}
	private void _pdf () {
		try {
			
			android.graphics.pdf.PdfDocument document = new android.graphics.pdf.PdfDocument();
			android.graphics.pdf.PdfDocument.PageInfo pageInfo = new android.graphics.pdf.PdfDocument.PageInfo.Builder(1080, 1920, 2).create();
			android.graphics.pdf.PdfDocument.Page page1 = document.startPage(pageInfo);
			View content1 = linear1;
			content1.draw(page1.getCanvas());
			document.finishPage(page1);
			android.graphics.pdf.PdfDocument.Page page2 = document.startPage(pageInfo);
			View content2 = linear2;
			content2.draw(page2.getCanvas());
			document.finishPage(page2);
			java.io.File myFile = new java.io.File(getExternalCacheDir() + "/newpdf.pdf");
			myFile.createNewFile();
			java.io.FileOutputStream fOut = new java.io.FileOutputStream(myFile);
			document.writeTo(fOut);
			fOut.close();
			document.close();
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			emailIntent.setType("*/*");
			emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new java.io.File(getExternalCacheDir() +"/newpdf.pdf")));
			startActivity(Intent.createChooser(emailIntent, "/ PDF"));
			Toast.makeText(getBaseContext(), "  PDF", Toast.LENGTH_SHORT).show();
		}
		
		catch (Exception e) {
			
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			
		}
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.printout, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final TextView sno = (TextView) _v.findViewById(R.id.sno);
			final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
			final TextView items = (TextView) _v.findViewById(R.id.items);
			final TextView qty = (TextView) _v.findViewById(R.id.qty);
			final LinearLayout linear3 = (LinearLayout) _v.findViewById(R.id.linear3);
			final TextView price = (TextView) _v.findViewById(R.id.price);
			
			sno.setText(String.valueOf((long)(_position + 1)));
			items.setText(currentorder.get((int)_position).get("title").toString());
			price.setText(currentorder.get((int)_position).get("price").toString());
			qty.setText(currentorder.get((int)_position).get("Qty").toString());
			
			return _v;
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
