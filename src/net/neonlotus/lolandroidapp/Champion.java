package net.neonlotus.lolandroidapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitleProvider;

public class Champion extends Activity implements View.OnClickListener{

	private ChampObj mChampData;
    private String mPrefPrefix;
	
	Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
	private SharedPreferences mPrefs;
	
	//Saved stuff
	String item1, item2, item3, item4, item5, item6, item7, item8, item9, item10;
	//Temp string...
	String t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.othermain);

		//LoadPreferences();
        Bundle b = getIntent().getExtras();
        if(b != null && b.getParcelable("net.neonlotus.lolandroidapp.ChampObj") != null){
            mChampData = b.getParcelable("net.neonlotus.lolandroidapp.ChampObj");
        }else {
            Toast.makeText(this, "No Champion selected!", Toast.LENGTH_SHORT).show();
            finish();
        }

        mPrefPrefix = mChampData.getChampName() + "_";

		mPrefs = getPreferences(MODE_PRIVATE);
		item1 = mPrefs.getString(mPrefPrefix+"item1","Item 1");
		item2 = mPrefs.getString(mPrefPrefix+"item2","Item 2");
		item3 = mPrefs.getString(mPrefPrefix+"item3","Item 3");
		item4 = mPrefs.getString(mPrefPrefix+"item4","Item 4");
		item5 = mPrefs.getString(mPrefPrefix+"item5","Item 5");
		item6 = mPrefs.getString(mPrefPrefix+"item6","Item 6");
		item7 = mPrefs.getString(mPrefPrefix+"item7","Item 7");
		item8 = mPrefs.getString(mPrefPrefix+"item8","Item 8");
		item9 = mPrefs.getString(mPrefPrefix+"item9","Item 9");
		item10 = mPrefs.getString(mPrefPrefix+"item10","Item 10");

		MyPagerAdapter adapter = new MyPagerAdapter();
		ViewPager myPager = (ViewPager) findViewById(R.id.thisshouldwork);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);

		TitlePageIndicator titleIndicator = (TitlePageIndicator)findViewById(R.id.titles);
		titleIndicator.setViewPager(myPager);
	}

	public void onClick(View view) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	private class MyPagerAdapter extends PagerAdapter implements TitleProvider{

		public int getCount() {
			return 3; //Amount of pages
		}

		public Object instantiateItem(View collection, final int position) {

			LayoutInflater inflater = (LayoutInflater) collection.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View view = null;
			switch (position) {
				case 0:
					view = inflater.inflate(R.layout.left_page, null);
					((TextView)view.findViewById(R.id.lorechamp)).setText(mChampData.getChampStory());
					((TextView)view.findViewById(R.id.champName)).setText(mChampData.getChampTags());

					break;
				case 1:
					view = inflater.inflate(R.layout.mid_page, null);
					((TextView)view.findViewById(R.id.champName)).setText(mChampData.getChampTags());
					((TextView)view.findViewById(R.id.champ_stats)).setText(mChampData.getChampStats());

					//----------
					//Experimental // Success :D
					//----------
					String allRoles = "";
					String junkStuff = mChampData.getChampStats();
					String noSpace = junkStuff.replaceAll(" ", "").toLowerCase();
					String noJunk[] = noSpace.split(",");
					for (int i=0; i<noJunk.length;i++) {
						// WORKS, returns each role Toast.makeText(Champion.this, noJunk[i], Toast.LENGTH_SHORT).show();
						if (noJunk[i].equals("assassin")) {
							allRoles= allRoles+ getString(R.string.assassin)+"\n\n";
						}
						if (noJunk[i].equals("carry")) {
							allRoles= allRoles + getString(R.string.carry)+"\n\n";
						}
						if (noJunk[i].equals("fighter")) {
							allRoles= allRoles + getString(R.string.fighter)+"\n\n";
						}
						if (noJunk[i].equals("jungler")) {
							allRoles= allRoles + getString(R.string.jungler)+"\n\n";
						}
						if (noJunk[i].equals("mage")) {
							allRoles= allRoles + getString(R.string.mage)+"\n\n";
						}
						if (noJunk[i].equals("melee")) {
							allRoles= allRoles + getString(R.string.melee)+"\n\n";
						}
						if (noJunk[i].equals("pusher")) {
							allRoles= allRoles + getString(R.string.pusher)+"\n\n";
						}
						if (noJunk[i].equals("ranged")) {
							allRoles= allRoles + getString(R.string.ranged)+"\n\n";
						}
						if (noJunk[i].equals("recommended")) {
							allRoles= allRoles + getString(R.string.recommended)+"\n\n";
						}
						if (noJunk[i].equals("stealth")) {
							allRoles= allRoles + getString(R.string.stealth)+"\n\n";
						}
						if (noJunk[i].equals("support")) {
							allRoles= allRoles + getString(R.string.support)+"\n\n";
						}
						if (noJunk[i].equals("tank")) {
							allRoles= allRoles + getString(R.string.tank)+"\n\n";
						}

						((TextView)view.findViewById(R.id.descone)).setText(allRoles);
					}
					break;
				case 2:
					view = inflater.inflate(R.layout.right_page, null);
					((TextView)view.findViewById(R.id.champName)).setText(mChampData.getChampTags());
					((TextView)view.findViewById(R.id.champ_build)).setText(mChampData.getChampBuilds());
					b1 = ((Button)view.findViewById(R.id.itemButton1));
					b2 = ((Button)view.findViewById(R.id.itemButton2));
					b3 = ((Button)view.findViewById(R.id.itemButton3));
					b4 = ((Button)view.findViewById(R.id.itemButton4));
					b5 = ((Button)view.findViewById(R.id.itemButton5));
					b6 = ((Button)view.findViewById(R.id.itemButton6));
					b7 = ((Button)view.findViewById(R.id.itemButton7));
					b8 = ((Button)view.findViewById(R.id.itemButton8));
					b9 = ((Button)view.findViewById(R.id.itemButton9));
					b10 = ((Button)view.findViewById(R.id.itemButton10));

					LoadPreferences();

					((Button)view.findViewById(R.id.itemButton1)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(0);
						}
					});
					((Button)view.findViewById(R.id.itemButton2)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(1);
						}
					});
					((Button)view.findViewById(R.id.itemButton3)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(2);
						}
					});
					((Button)view.findViewById(R.id.itemButton4)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(3);
						}
					});
					((Button)view.findViewById(R.id.itemButton5)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(4);
						}
					});
					((Button)view.findViewById(R.id.itemButton6)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(5);
						}
					});
					((Button)view.findViewById(R.id.itemButton7)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(6);
						}
					});
					((Button)view.findViewById(R.id.itemButton8)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(7);
						}
					});
					((Button)view.findViewById(R.id.itemButton9)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(8);
						}
					});
					((Button)view.findViewById(R.id.itemButton10)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							showOptionsMenu(9);
						}
					});

					break;
			}

			((ViewPager) collection).addView(view, 0);

			return view;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub
		}

		public String getTitle(int position) {
			switch (position) {
				case 0:
					return "Lore";
				case 1:
					return "Tags";
				case 2:
					return "Items";
			}
			return Integer.toString(position);
		}
	}

	public void showOptionsMenu(final int position) {
		new AlertDialog.Builder(this)
				.setTitle("Item List").setCancelable(true).setItems(R.array.item_list,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
						//take actions here according to what the user has selected
						final String[] big_item_list = getResources().getStringArray(R.array.item_list);

						switch (position) {
							case 0:
								b1.setText(big_item_list[i]);
								t1 = b1.getText().toString();
								SavePreferences(mPrefPrefix+"item1",t1);
								break;
							case 1:
								b2.setText(big_item_list[i]);
								t2 = b2.getText().toString();
								SavePreferences(mPrefPrefix+"item2",t2);
								break;
							case 2:
								b3.setText(big_item_list[i]);
								t3 = b3.getText().toString();
								SavePreferences(mPrefPrefix+"item3",t3);
								break;
							case 3:
								b4.setText(big_item_list[i]);
								t4 = b4.getText().toString();
								SavePreferences(mPrefPrefix+"item4",t4);
								break;
							case 4:
								b5.setText(big_item_list[i]);
								t5 = b5.getText().toString();
								SavePreferences(mPrefPrefix+"item5",t5);
								break;
							case 5:
								b6.setText(big_item_list[i]);
								t6 = b6.getText().toString();
								SavePreferences(mPrefPrefix+"item6",t6);
								break;
							case 6:
								b7.setText(big_item_list[i]);
								t7 = b7.getText().toString();
								SavePreferences(mPrefPrefix+"item7",t7);
								break;
							case 7:
								b8.setText(big_item_list[i]);
								t8 = b8.getText().toString();
								SavePreferences(mPrefPrefix+"item8",t8);
								break;
							case 8:
								b9.setText(big_item_list[i]);
								t9 = b9.getText().toString();
								SavePreferences(mPrefPrefix+"item9",t9);
								break;
							case 9:
								b10.setText(big_item_list[i]);
								t10 = b10.getText().toString();
								SavePreferences(mPrefPrefix+"item10",t10);
								break;
						}

					}
				}
		).show();
	}

	private void SavePreferences(String key, String value){
		mPrefs = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	private void LoadPreferences(){
		mPrefs = getPreferences(MODE_PRIVATE);
		String s1=mPrefs.getString(mPrefPrefix+"item1","Item 1");
		b1.setText(s1);
		String s2=mPrefs.getString(mPrefPrefix+"item2","Item 2");
		b2.setText(s2);
		String s3=mPrefs.getString(mPrefPrefix+"item3","Item 3");
		b3.setText(s3);
		String s4=mPrefs.getString(mPrefPrefix+"item4","Item 4");
		b4.setText(s4);
		String s5=mPrefs.getString(mPrefPrefix+"item5","Item 5");
		b5.setText(s5);
		String s6=mPrefs.getString(mPrefPrefix+"item6","Item 6");
		b6.setText(s6);
		String s7=mPrefs.getString(mPrefPrefix+"item7","Item 7");
		b7.setText(s7);
		String s8=mPrefs.getString(mPrefPrefix+"item8","Item 8");
		b8.setText(s8);
		String s9=mPrefs.getString(mPrefPrefix+"item9","Item 9");
		b9.setText(s9);
		String s10=mPrefs.getString(mPrefPrefix+"item10","Item 10");
		b10.setText(s10);
	}
}