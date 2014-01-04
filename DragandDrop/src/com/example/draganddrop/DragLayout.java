package com.example.draganddrop;

import java.util.ArrayList;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DragLayout extends RelativeLayout {

	boolean DEBUG = true;
	
	Context context;
	int screenWidth,screenHeight;
	static float up_x=0,up_y=0;

	
	boolean mIsScrolling = false;
    
	public DragLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		
		//not to include in main programe
		getDimensionsofScreen();
		
		setLayout();
		setViews();
		
	}


	private void setLayout() {

		
		// set according to parent layout (not according to current layout)
		RelativeLayout.LayoutParams rLp = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,  LayoutParams.MATCH_PARENT);
		rLp.topMargin = 2 * (screenHeight / 25); // calculating 1/10 of 4/5
													// screen

		this.setLayoutParams(rLp);

	}
	
	void setViews() {
		
		ImageView img1 = new ImageView(context);
		
		RelativeLayout.LayoutParams rLp = new RelativeLayout.LayoutParams(
				(screenWidth / 5), (screenHeight / 5));
		rLp.topMargin = (screenHeight / 10);
		rLp.leftMargin = (screenWidth / 10);
		
		img1.setLayoutParams(rLp);
		img1.setBackgroundDrawable(getResources().getDrawable(R.drawable.chair));
		img1.setOnTouchListener(new ChoiceTouchListener(context, 1));
		this.addView(img1);
		
            ImageView img2 = new ImageView(context);
		
		 rLp = new RelativeLayout.LayoutParams(
				(screenWidth / 5), (screenHeight / 5));
		rLp.topMargin = (screenHeight / 10);
		rLp.leftMargin = (4*screenWidth / 10);
		
		img2.setLayoutParams(rLp);
		img2.setBackgroundDrawable(getResources().getDrawable(R.drawable.box_o));
		img2.setOnDragListener(new ChoiceDragListener(context));
		this.addView(img2);
	}
	
	
	public ArrayList<Integer> getDimensionsofScreen() {
		
		//metrics that holds the value of height and width
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();;
		ArrayList<Integer> vals = new ArrayList<Integer>();
		
		vals.add(displayMetrics.widthPixels);
		vals.add(displayMetrics.heightPixels);
		screenHeight = displayMetrics.heightPixels;
		screenWidth = displayMetrics.widthPixels;
		
		return vals;
	}
	


	@SuppressLint("NewApi")
	@Override
	public boolean onDragEvent(DragEvent event) {
		
		int mCurX = (int) event.getX();
        int mCurY = (int) event.getY();
		
        
		
		if(event.getAction() == DragEvent.ACTION_DROP || event.getAction() == DragEvent.ACTION_DRAG_EXITED) {
		
		
	        Log.v("here","it is :: " + mCurX + ", "+mCurY);
	        
			View view1 = (View) event.getLocalState();
			view1.setVisibility(View.VISIBLE);
			ObjectAnimator animationx = ObjectAnimator.ofFloat(view1,"translationX", mCurX - ChoiceTouchListener.offsetX-(screenWidth / 10),0.0f);
			ObjectAnimator animationy = ObjectAnimator.ofFloat(view1, "translationY", mCurY - ChoiceTouchListener.offsetY-(screenHeight / 10),0.0f);
			AnimatorSet animSet = new AnimatorSet();
			animSet.setDuration(500);
			animSet.playTogether(animationx,animationy);
			
			animSet.start();
			
		}
		return true;
		
		
	}
	
	

}
