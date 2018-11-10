package com.hm.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressLoader  {

	private TransparentProgressDialog pd;
	private String displayMessage;

	public ProgressLoader(Context context, String displayMessage){
		this.displayMessage=displayMessage;
		pd= new TransparentProgressDialog(context);
	}

	public void show(){
		pd.show();
	}


	public void dismiss(){
		if(pd!=null && pd.isShowing()) {
			pd.dismiss();
		}
	}

	public boolean isShowing(){
		return pd.isShowing();
	}

	public void setIndeterminate(boolean indeterminate) {

		pd.iv.setIndeterminate(indeterminate);
	}

	public void setProgress(int progress) {

		pd.iv.setProgress(progress);
	}

	public void setMax(int max) {

		pd.iv.setMax(max);
	}
	
	private class TransparentProgressDialog extends Dialog {
		
	    ProgressBar iv;
	    TextView tv;
			
		public TransparentProgressDialog(Context context) {
			super(context, R.style.TransparentProgressDialog);
	        	WindowManager.LayoutParams wlmp = getWindow().getAttributes();
	        	wlmp.gravity = Gravity.CENTER_HORIZONTAL;
	        	getWindow().setAttributes(wlmp);
			setTitle(null);
			setCancelable(false);
			//setCanceledOnTouchOutside(false);
			setOnCancelListener(null);
			LinearLayout layout = new LinearLayout(context);
			layout.setOrientation(LinearLayout.VERTICAL);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.CENTER;
			iv = new ProgressBar(context);
			iv.setIndeterminate(true);
			iv.setLayoutParams(params);
			//iv.setImageResource(R.drawable.loading_spinner);
			layout.addView(iv, params);
			tv =new TextView(context);
			tv.setText(displayMessage);
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
			layout.addView(tv,params);
			addContentView(layout, params);
		}
			
		@Override
		public void show() {
			try {
				super.show();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
