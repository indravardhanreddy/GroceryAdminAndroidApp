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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView;
import android.view.View;

public class AdminOrdersActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> orderlist = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView imageview1;
	private TextView textview1;
	private TextView textview3;
	private TextView textview2;
	private ListView listview1;
	
	private DatabaseReference valid_orders = _firebase.getReference("valid_orders");
	private ChildEventListener _valid_orders_child_listener;
	private Calendar c = Calendar.getInstance();
	private Intent v = new Intent();
	private AlertDialog.Builder d;
	private DatabaseReference archive_order = _firebase.getReference("archive_order");
	private ChildEventListener _archive_order_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.admin_orders);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview2 = (TextView) findViewById(R.id.textview2);
		listview1 = (ListView) findViewById(R.id.listview1);
		d = new AlertDialog.Builder(this);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				v.setClass(getApplicationContext(), BillActivity.class);
				v.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				v.putExtra("orderid", str.get((int)(_position)));
				startActivity(v);
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				map.clear();
				d.setTitle("Alert dialog !!! ");
				d.setMessage("Would you like to delete the order? ");
				d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						valid_orders.child(str.get((int)(_position))).removeValue();
					}
				});
				d.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.setNeutralButton("Archive", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						map = orderlist.get((int)_position);
						archive_order.child(str.get((int)(_position))).updateChildren(map);
						valid_orders.child(str.get((int)(_position))).removeValue();
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
				valid_orders.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						orderlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								orderlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						c = Calendar.getInstance();
						str.clear();
						for (DataSnapshot dshot:
						_dataSnapshot.getChildren()) {
							str.add(dshot.getKey()) ;
						}
						textview1.setText("Total Orders:".concat(String.valueOf((long)(orderlist.size()))));
						textview2.setText(new SimpleDateFormat("dd MMM yyyy HH:mm a ").format(c.getTime()));
						listview1.setAdapter(new Listview1Adapter(orderlist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
				valid_orders.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						orderlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								orderlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						c = Calendar.getInstance();
						str.clear();
						for (DataSnapshot dshot:
						_dataSnapshot.getChildren()) {
							str.add(dshot.getKey()) ;
						}
						textview1.setText("Total Orders:".concat(String.valueOf((long)(orderlist.size()))));
						textview2.setText(new SimpleDateFormat("dd MMM yyyy HH:mm a ").format(c.getTime()));
						listview1.setAdapter(new Listview1Adapter(orderlist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
				valid_orders.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						orderlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								orderlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						c = Calendar.getInstance();
						str.clear();
						for (DataSnapshot dshot:
						_dataSnapshot.getChildren()) {
							str.add(dshot.getKey()) ;
						}
						textview1.setText("Total Orders:".concat(String.valueOf((long)(orderlist.size()))));
						textview2.setText(new SimpleDateFormat("dd MMM yyyy HH:mm a ").format(c.getTime()));
						listview1.setAdapter(new Listview1Adapter(orderlist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
		valid_orders.addChildEventListener(_valid_orders_child_listener);
		
		_archive_order_child_listener = new ChildEventListener() {
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
		archive_order.addChildEventListener(_archive_order_child_listener);
	}
	private void initializeLogic() {
		valid_orders.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				orderlist = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						orderlist.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				c = Calendar.getInstance();
				str.clear();
				for (DataSnapshot dshot:
				_dataSnapshot.getChildren()) {
					str.add(dshot.getKey()) ;
				}
				textview1.setText("Total Orders:".concat(String.valueOf((long)(orderlist.size()))));
				textview2.setText(new SimpleDateFormat("dd MMM yyyy HH:mm a ").format(c.getTime()));
				listview1.setAdapter(new Listview1Adapter(orderlist));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
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
				_v = _inflater.inflate(R.layout.orderpreview, null);
			}
			
			final LinearLayout bg_color = (LinearLayout) _v.findViewById(R.id.bg_color);
			final LinearLayout linear5 = (LinearLayout) _v.findViewById(R.id.linear5);
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
			final LinearLayout linear3 = (LinearLayout) _v.findViewById(R.id.linear3);
			final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);
			final TextView orderid = (TextView) _v.findViewById(R.id.orderid);
			final ImageView imageview2 = (ImageView) _v.findViewById(R.id.imageview2);
			final TextView address = (TextView) _v.findViewById(R.id.address);
			final ImageView imageview3 = (ImageView) _v.findViewById(R.id.imageview3);
			final TextView item_count = (TextView) _v.findViewById(R.id.item_count);
			final LinearLayout linear9 = (LinearLayout) _v.findViewById(R.id.linear9);
			final LinearLayout linear7 = (LinearLayout) _v.findViewById(R.id.linear7);
			final LinearLayout linear8 = (LinearLayout) _v.findViewById(R.id.linear8);
			final TextView items = (TextView) _v.findViewById(R.id.items);
			final TextView textview2 = (TextView) _v.findViewById(R.id.textview2);
			final TextView totalprice = (TextView) _v.findViewById(R.id.totalprice);
			final TextView username = (TextView) _v.findViewById(R.id.username);
			final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);
			final TextView contactnum = (TextView) _v.findViewById(R.id.contactnum);
			final TextView ordertime = (TextView) _v.findViewById(R.id.ordertime);
			
			orderid.setText(String.valueOf((long)(_position + 1)));
			item_count.setText(orderlist.get((int)_position).get("Totalitems").toString());
			totalprice.setText(orderlist.get((int)_position).get("Totalamt").toString());
			contactnum.setText(orderlist.get((int)_position).get("Cust_num").toString());
			username.setText(orderlist.get((int)_position).get("Username").toString());
			address.setText(orderlist.get((int)_position).get("Loc").toString());
			items.setText(orderlist.get((int)_position).get("Items").toString());
			ordertime.setText(orderlist.get((int)_position).get("Ordertime").toString());
			if (Double.parseDouble(orderlist.get((int)_position).get("Flag").toString()) == 0) {
				bg_color.setBackgroundColor(0xFFFFFFFF);
			}
			else {
				bg_color.setBackgroundColor(0xFFC5E1A5);
			}
			if (Double.parseDouble(orderlist.get((int)_position).get("UserFlag").toString()) == 0) {
				imageview3.setVisibility(View.GONE);
			}
			else {
				imageview3.setVisibility(View.VISIBLE);
			}
			
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
