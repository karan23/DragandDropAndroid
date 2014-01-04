package com.example.draganddrop;

import java.util.ArrayList;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnTouchListener;

/**
 * ChoiceTouchListener will handle touch events on draggable views
 *
 */
public final class ChoiceTouchListener implements OnTouchListener {
	Context context;
	int index;
	static float offsetX = 0,offsetY = 0;
	
	DragShadowBuilder shadowBuilder;
	
	public ChoiceTouchListener(Context context,int index) {
		super();
		this.context = context;
		this.index = index;
	}
	
	
	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			
			/*
			 * Drag details: we only need default behavior
			 * - clip data could be set to pass data as part of drag
			 * - shadow can be tailored
			 */
			
			view.setTag("option"+index);
			ClipData data = ClipData.newPlainText("tag", "option"+index);
			shadowBuilder = new View.DragShadowBuilder(view);
			
			//start dragging the item touched
			view.startDrag(data, shadowBuilder, view, 0);
			
			offsetX = view.getLeft();//(int)view.getX();//(int)motionEvent.getX();
			 offsetY = view.getTop();//(int)view.getY();//motionEvent.getY();
			 view.setVisibility(View.GONE);
			 Log.v("here","it is ::" + (int)motionEvent.getX() + " , "+(int)motionEvent.getY());
			 
			 return false;
			
			 
			
//		} else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
//			int x =  offsetX - (int)motionEvent.getX();
//			 int y = offsetY - (int)motionEvent.getY();
//			
////			 Log.i("here","it is ::" + x + "," + y);
////			 Log.i("here","it is ::" + offsetX + "," + offsetY);
//			 Log.i("here","it is ::" + (int)motionEvent.getX() + "," + (int)motionEvent.getY());
////			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
////					100, 100);
////			lp.leftMargin = x;
////			lp.topMargin = y;
////			
////			view.setLayoutParams(lp);
//			
//			return true;
		}
//		else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
////		else {
//			Log.v("here","it is:: " + motionEvent.getX() + ", "+ motionEvent.getY());
//		}
		
		return true;
		
	}
}