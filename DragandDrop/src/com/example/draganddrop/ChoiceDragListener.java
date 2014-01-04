package com.example.draganddrop;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.ClipData.Item;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;

/**
 * DragListener will handle dragged views being dropped on the drop area
 * - only the drop action will have processing added to it as we are not
 * - amending the default behavior for other parts of the drag process
 *
 */
public class ChoiceDragListener implements OnDragListener {

	boolean DEBUG = true;
	Context context;
	
	ChoiceDragListener(Context context){
		this.context = context;
	}
	
	@Override
	public boolean onDrag(View v, DragEvent event) {
		switch (event.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED:
			if(DEBUG) Log.v("here","drag started");
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			break;
		case DragEvent.ACTION_DRAG_LOCATION:
		 int mCurX = (int) event.getX();
        int mCurY = (int) event.getY();
        
        if(DEBUG) Log.v("Cur(X, Y) : " ,"here ::" + mCurX + ", " + mCurY );
        
        break;
		case DragEvent.ACTION_DRAG_EXITED:        
			
			if(DEBUG)
			Log.v("here","drag exits");
			
			break;
		case DragEvent.ACTION_DROP:

			//handle the dragged view being dropped over a drop view
			View view = (View) event.getLocalState();
			ClipData cd =  event.getClipData();
			Item item = cd.getItemAt(0);
			String resp = item.coerceToText(context).toString();
			
			
			//stop displaying the view where it was before it was dragged
			view.setVisibility(View.INVISIBLE);
			
			//view dragged item is being dropped on
			ImageView dropTarget = (ImageView) v;
			
			//view being dragged and dropped
			ImageView dropped = (ImageView) view;
			
			dropped.setEnabled(false);

			//update the text in the target view to reflect the data being dropped
			dropTarget.setBackgroundDrawable(view.getBackground());

			//if an item has already been dropped here, there will be a tag
			Object tag = dropTarget.getTag();
			
			//if there is already an item here, set it back visible in its original place
			if(tag!=null)
			{
				//the tag is the view id already dropped here
				int existingID = (Integer)tag;
			
				//set the original view visible again
				((Activity) context).findViewById(existingID).setVisibility(View.VISIBLE);
			}
			
			//set the tag in the target view being dropped on - to the ID of the view being dropped
			dropTarget.setTag(dropped.getId());
			break;
		case DragEvent.ACTION_DRAG_ENDED:
			
			if(DEBUG) Log.i("drag event","ended::" + ChoiceTouchListener.offsetX+ "," + ChoiceTouchListener.offsetY);
			
			/**
			 * returning false so that goes to parentView onDrag function
			 */
			return false;
			//break;
		default:
			break;
		}
		return true;
	}
}
