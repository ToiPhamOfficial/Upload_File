package com.tpo.tmusic;



import android.Manifest;

import android.animation.*;

import android.animation.ObjectAnimator;

import android.app.*;

import android.content.*;

import android.content.Intent;

import android.content.pm.PackageManager;

import android.content.res.*;

import android.graphics.*;

import android.graphics.Typeface;

import android.graphics.drawable.*;

import android.media.*;

import android.net.*;

import android.net.Uri;

import android.os.*;

import android.text.*;

import android.text.Editable;

import android.text.TextWatcher;

import android.text.style.*;

import android.util.*;

import android.view.*;

import android.view.View;

import android.view.View.*;

import android.view.animation.*;

import android.view.animation.AccelerateDecelerateInterpolator;

import android.view.animation.AccelerateInterpolator;

import android.view.animation.BounceInterpolator;

import android.view.animation.DecelerateInterpolator;

import android.view.animation.LinearInterpolator;

import android.webkit.*;

import android.widget.*;

import android.widget.EditText;

import android.widget.ImageView;

import android.widget.LinearLayout;

import android.widget.ProgressBar;

import android.widget.TextView;

import androidx.annotation.*;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;

import androidx.core.content.ContextCompat;

import androidx.fragment.app.DialogFragment;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentStatePagerAdapter;

import androidx.viewpager.widget.PagerAdapter;

import androidx.viewpager.widget.ViewPager;

import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;

import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import com.bumptech.glide.Glide;

import com.google.android.material.color.MaterialColors;

import com.google.android.material.tabs.TabLayout;

import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import de.hdodenhof.circleimageview.*;

import java.io.*;

import java.text.*;

import java.text.DecimalFormat;

import java.util.*;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.regex.*;

import org.json.*;

import android.graphics.Rect;

import android.view.ViewTreeObserver;




public class HomeActivity extends AppCompatActivity {
	private BroadcastReceiver progressReceiver = new BroadcastReceiver() {
		    @Override
		    public void onReceive(Context context, Intent intent) {
			        if ("ACTION_UPDATE_PROGRESS".equals(intent.getAction())) {
				            int duration = intent.getIntExtra("duration", 0);
				            int current = intent.getIntExtra("current", 0);
				
				            progressbar.setMax(duration);
				            //c.setTimeInMillis((long)(MusicPlayerManager.instance.getDuration()));

				            //duration.setText(new SimpleDateFormat("mm:ss").format(c.getTime()));
				            progressbar.setProgress(current);
				        }
			    }
	};

	private boolean keyboardWasOpen = false;
	
	private void listenKeyboardClosedOnly() {
					    final View rootView = getWindow().getDecorView().getRootView();
					    rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
									        @Override
									        public void onGlobalLayout() {
													            Rect r = new Rect();
													            rootView.getWindowVisibleDisplayFrame(r);
													            int screenHeight = rootView.getHeight();
													            int keypadHeight = screenHeight - r.bottom;
													
													            boolean isKeyboardNowOpen = keypadHeight > screenHeight * 0.15;
													
													            // Khi bàn phím vừa được đóng
													            if (keyboardWasOpen && !isKeyboardNowOpen) {
																	                if (linear8.getVisibility() == View.VISIBLE) {
											                                                    if(getSupportFragmentManager().findFragmentByTag("SEARCH_FRAGMENT") == null) {
													                                                        container.setVisibility(View.GONE);

							linear8.setVisibility(View.GONE);

							tablayout1.setVisibility(View.VISIBLE);

							linear1.setVisibility(View.VISIBLE);

							edittext1.setText("");
													                                                    }

															                                }
																	    }
													
													            // Cập nhật trạng thái
													            keyboardWasOpen = isKeyboardNowOpen;
													        }
									    });
	}

	public void slideDown(View view) {
				    view.setVisibility(View.VISIBLE);
				    view.setTranslationY(-view.getHeight());
				
				    view.animate()
				        .translationY(0f)
				        .setDuration(2500)
				        .setListener(null)
				        .start();
	}

	public void slideUp(final View view) {
		    TranslateAnimation animate = new TranslateAnimation(
		        0, 0,
		        0, -view.getHeight()
		    );
		    animate.setDuration(300);
		    animate.setFillAfter(false);
		    view.startAnimation(animate);
		
		    // Sau animation, ẩn view luôn
		    animate.setAnimationListener(new Animation.AnimationListener() {
			        @Override
			        public void onAnimationStart(Animation animation) { }
			
			        @Override
			        public void onAnimationEnd(Animation animation) {
				            view.setVisibility(View.GONE);
				        }
			
			        @Override
			        public void onAnimationRepeat(Animation animation) { }
			    });
	}

		

		private boolean isPlaying = false;

		private String jsonDataArr = "";

		private HashMap<String, Object> responseMap = new HashMap<>();

		private double currentIndex = 0;

		private boolean hasSetSource = false;

		private String ggg = "";

		private boolean isLooping = false;

		private boolean isRandom = false;

		private double height = 0;

		

		private ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();

		

		private RelativeLayout relativelayout3;

		private RelativeLayout relativelayout1;

		private LinearLayout miniControl;

		private ViewPager viewpager1;

		private LinearLayout container;

		private ProgressBar progressbar;

		private LinearLayout linear4;

		private CircleImageView circleimageview1;

		private LinearLayout linear7;

		private ImageView imageview4;

		private ImageView imageview5;

		private TextView textview2;

		private TextView textview3;

		private LinearLayout linear_tooltab_and_tablayout;

		private LinearLayout linear8;

		private LinearLayout linear9;

		private TextView textview4;

		private ImageView imageview7;

		private EditText edittext1;

		private CircleImageView circleimageview3;

		private LinearLayout linear1;

		private TabLayout tablayout1;

		private TextView textview1;

		private LinearLayout linear2;

		private ImageView search_song;

		private ImageView settings_and_more;

		

		private FragFragmentAdapter frag;

		private RequestNetwork rq;

		private RequestNetwork.RequestListener _rq_request_listener;

		private Intent it = new Intent();

		private Intent i = new Intent();

		private ObjectAnimator test = new ObjectAnimator();

		

		@Override

		protected void onCreate(Bundle _savedInstanceState) {

				super.onCreate(_savedInstanceState);

				setContentView(R.layout.home);

				initialize(_savedInstanceState);

				

				if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED

				|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

						ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);

				} else {

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

				relativelayout3 = findViewById(R.id.relativelayout3);

				relativelayout1 = findViewById(R.id.relativelayout1);

				miniControl = findViewById(R.id.miniControl);

				viewpager1 = findViewById(R.id.viewpager1);

				container = findViewById(R.id.container);

				progressbar = findViewById(R.id.progressbar);

				linear4 = findViewById(R.id.linear4);

				circleimageview1 = findViewById(R.id.circleimageview1);

				linear7 = findViewById(R.id.linear7);

				imageview4 = findViewById(R.id.imageview4);

				imageview5 = findViewById(R.id.imageview5);

				textview2 = findViewById(R.id.textview2);

				textview3 = findViewById(R.id.textview3);

				linear_tooltab_and_tablayout = findViewById(R.id.linear_tooltab_and_tablayout);

				linear8 = findViewById(R.id.linear8);

				linear9 = findViewById(R.id.linear9);

				textview4 = findViewById(R.id.textview4);

				imageview7 = findViewById(R.id.imageview7);

				edittext1 = findViewById(R.id.edittext1);

				circleimageview3 = findViewById(R.id.circleimageview3);

				linear1 = findViewById(R.id.linear1);

				tablayout1 = findViewById(R.id.tablayout1);

				textview1 = findViewById(R.id.textview1);

				linear2 = findViewById(R.id.linear2);

				search_song = findViewById(R.id.search_song);

				settings_and_more = findViewById(R.id.settings_and_more);

				frag = new FragFragmentAdapter(getApplicationContext(), getSupportFragmentManager());

				rq = new RequestNetwork(this);

				

				miniControl.setOnClickListener(new View.OnClickListener() {

						@Override

						public void onClick(View _view) {

								it.setClass(getApplicationContext(), ControlActivity.class);

								startActivity(it);

						}

				});

				

				container.setOnClickListener(new View.OnClickListener() {

						@Override

						public void onClick(View _view) {

								Fragment existingFragment = getSupportFragmentManager()
								    .findFragmentByTag("SEARCH_FRAGMENT");
								
								if (existingFragment == null) {

										textview4.performClick();

								}

						}

				});

				

				circleimageview1.setOnClickListener(new View.OnClickListener() {

						@Override

						public void onClick(View _view) {

								height = linear8.getHeight();

						}

				});

				

				imageview4.setOnClickListener(new View.OnClickListener() {

						@Override

						public void onClick(View _view) {

								if (MusicPlayerManager.instance.getCurrentIndex() > -1) {

										if (MusicPlayerManager.instance.isPlaying()) {

												imageview4.setImageResource(R.drawable.ic_mt_play);

										} else {

												imageview4.setImageResource(R.drawable.ic_mt_pause);

										}

										MusicPlayerManager.instance.togglePlayPause();

										MusicPlayerManager.instance.refresh(MusicPlayerManager.StateChangeType.PLAY_STATE_CHANGED);

								} else {

										MusicPlayerManager.instance.play();

										MusicPlayerManager.instance.setCurrentIndex(0);

										MusicPlayerManager.instance.play();

										MusicPlayerManager.instance.refresh(MusicPlayerManager.StateChangeType.INDEX_CHANGED);

										imageview4.setImageResource(R.drawable.ic_mt_pause);

								}

						}

				});

				

				textview4.setOnClickListener(new View.OnClickListener() {

						@Override

						public void onClick(View _view) {

								Fragment existingFragment = getSupportFragmentManager()
								    .findFragmentByTag("SEARCH_FRAGMENT");
								
								if (existingFragment != null) {
														    getSupportFragmentManager()
														        .beginTransaction()
														        .remove(existingFragment)
														        .commit();
														    container.setAlpha(0.5f); // Khôi phục trạng thái container
														    container.setBackgroundColor(0xFF424242);
								}

								test.setTarget(linear8);

								test.setPropertyName("translationY");

								test.setFloatValues((float)(0), (float)(0 - linear8.getHeight()));

								test.setDuration((int)(300));

								test.start();

								edittext1.setText("");

								SketchwareUtil.hideKeyboard(getApplicationContext());

								tablayout1.setVisibility(View.VISIBLE);

								container.setVisibility(View.GONE);

						}

				});

				

				edittext1.addTextChangedListener(new TextWatcher() {

						@Override

						public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

								final String _charSeq = _param1.toString();

								/*TestFragmentActivity fragment = new TestFragmentActivity();

if (_charSeq.length() == 0) {

container.setAlpha((float)(0.5d));

container.setBackgroundColor(0xFF424242);

getSupportFragmentManager()
    .beginTransaction()
    .remove(fragment) // Dùng lại biến fragment luôn
    .commit();

} else {

container.setAlpha((float)(1));

container.setBackgroundColor(0xFFFFFFFF);

Bundle args = new Bundle();

if (_charSeq.equals("daubuoi")) {

args.putString("gg", "daubuoi");

} else {

args.putString("gg", "lonmup");

}

fragment.setArguments(args);
getSupportFragmentManager()
    .beginTransaction()
    .replace(R.id.container, fragment)
    .commit();

}

*/

								TestFragmentActivity fragment = new TestFragmentActivity(); // Luôn tạo mới
								
								if (_charSeq.length() == 0) {
											    container.setAlpha(0.5f);
											    container.setBackgroundColor(0xFF424242);
											    
											    // Xóa Fragment nếu đang hiển thị
											    Fragment oldFragment = getSupportFragmentManager().findFragmentByTag("SEARCH_FRAGMENT");
											    if (oldFragment != null) {
														        getSupportFragmentManager()
														            .beginTransaction()
														            .remove(oldFragment)
														            .commit();
														    }
								} else {
											    container.setAlpha(1.0f);
											    container.setBackgroundColor(0xFFFFFFFF);
											    
											    Bundle args = new Bundle();
											    args.putString("gg", _charSeq.equals("daubuoi") ? "daubuoi" : "lonmup");
											    fragment.setArguments(args); // Đặt arguments trước khi hiển thị
											    
											    // Sử dụng replace() để đảm bảo Fragment cũ bị xóa
											    getSupportFragmentManager()
											        .beginTransaction()
											        .replace(R.id.container, fragment, "SEARCH_FRAGMENT")
											        .commit();
								}

						}

						

						@Override

						public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

								

						}

						

						@Override

						public void afterTextChanged(Editable _param1) {

								

						}

				});

				

				circleimageview3.setOnClickListener(new View.OnClickListener() {

						@Override

						public void onClick(View _view) {

								edittext1.setText("");

						}

				});

				

				search_song.setOnClickListener(new View.OnClickListener() {

						@Override

						public void onClick(View _view) {

								tablayout1.setVisibility(View.GONE);

								test.setTarget(linear8);

								test.setPropertyName("translationY");

								test.setFloatValues((float)(0 - linear8.getHeight()), (float)(0));

								test.setDuration((int)(300));

								linear8.setVisibility(View.VISIBLE);

								test.start();

								edittext1.requestFocus();

								SketchwareUtil.showKeyboard(getApplicationContext());

								container.setVisibility(View.VISIBLE);

						}

				});

				

				_rq_request_listener = new RequestNetwork.RequestListener() {

						@Override

						public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {

								final String _tag = _param1;

								final String _response = _param2;

								final HashMap<String, Object> _responseHeaders = _param3;

								responseMap = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());

								jsonDataArr = new Gson().toJson(responseMap.get("data"));

								dataList = new Gson().fromJson(jsonDataArr, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

								textview2.setText(dataList.get((int)0).get("title").toString());

								textview3.setText(dataList.get((int)0).get("artist").toString());

								Glide.with(getApplicationContext()).load(Uri.parse(dataList.get((int)0).get("image_url").toString())).into(circleimageview1);

								miniControl.setVisibility(View.VISIBLE);

								MusicPlayerManager.instance.setSongs(dataList);

								MusicPlayerManager.instance.refresh(MusicPlayerManager.StateChangeType.SONGS_CHANGED);

						}

						

						@Override

						public void onErrorResponse(String _param1, String _param2) {

								final String _tag = _param1;

								final String _message = _param2;

								

						}

				};

		}

		

		private void initializeLogic() {

				FileUtil.writeFile("/sdcard/db.txt", String.valueOf(linear8.getHeight()).concat(String.valueOf(linear_tooltab_and_tablayout.getHeight())));

				edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fz_poppins_regular.ttf"), 0);

				textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fz_poppins_regular.ttf"), 0);

				linear9.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFBDBDBD));

				registerReceiver(progressReceiver, new IntentFilter("ACTION_UPDATE_PROGRESS"));

				// Init UI

				textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fz_poppins_semibold.ttf"), 0);

				textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fz_poppins_regular.ttf"), 0);

				textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/fz_poppins_light.ttf"), 0);

				tablayout1.setupWithViewPager(viewpager1);

				frag.setTabCount(3);

				viewpager1.setAdapter(frag);

				// Sub event

				MusicPlayerManager.instance.addListener(new MusicPlayerManager.Listener() {
						    @Override
						    public void onStateChanged(MusicPlayerManager manager, MusicPlayerManager.StateChangeType type) {
								        switch(type){
										case INDEX_CHANGED: {
												i.setClass(getApplicationContext(), MusicService.class);

												i.setAction("ACTION_PLAY_INDEX");

												i.putExtra("index", MusicPlayerManager.instance.getCurrentIndex());
												startForegroundService(i);

												textview2.setText(dataList.get((int)MusicPlayerManager.instance.getCurrentIndex()).get("title").toString());

												textview3.setText(dataList.get((int)MusicPlayerManager.instance.getCurrentIndex()).get("artist").toString());

												imageview4.setImageResource(R.drawable.ic_mt_pause);

												Glide.with(getApplicationContext()).load(Uri.parse(dataList.get((int)MusicPlayerManager.instance.getCurrentIndex()).get("image_url").toString())).into(circleimageview1);

												break;
										}

										case PLAY_STATE_CHANGED: {
												i.setClass(getApplicationContext(), MusicService.class);

												i.setAction("ACTION_TOGGLE_PLAY");

												startForegroundService(i);

												break;
										}
								}
								    }
				});

				// rq API

				rq.startRequestNetwork(RequestNetworkController.GET, "https://api.phamvantoi.id.vn/music/get-songs", "", _rq_request_listener);

				Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/fz_poppins_regular.ttf");
				
				ViewGroup tabs = (ViewGroup) ((ViewGroup) tablayout1.getChildAt(0));
				for (int i = 0; i < tabs.getChildCount(); i++) {
							    View tab = tabs.getChildAt(i);
							    if (tab instanceof ViewGroup) {
						
