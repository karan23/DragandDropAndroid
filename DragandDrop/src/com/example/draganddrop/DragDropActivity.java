package com.example.draganddrop;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class DragDropActivity extends Activity {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drag_drop);
		
		
		DragLayout dragLayout = new DragLayout(DragDropActivity.this);

		RelativeLayout rel1 = (RelativeLayout) findViewById(R.id.lin1);
		rel1.addView(dragLayout);
		
		
	}
	
	
	

	 

	
	
	
	 
}