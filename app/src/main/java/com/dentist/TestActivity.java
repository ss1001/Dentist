package com.dentist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.interfaces.Iview;

public class TestActivity extends Activity implements Iview,View.OnTouchListener {
	private View content,menu;
	private LinearLayout.LayoutParams menu_params;
	private int menu_padding=200;//
	private int width;
	private Point point;
	private int leftEdge;
	private int rightEdge=0;
    boolean isVisiable;
	private VelocityTracker mVelocityTracker;
	private float xUp,xDown,xMove;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_protect);
		init();
	}

	private void init() {

		menu=findViewById(R.id.menu);
		content=findViewById(R.id.content);
		menu_params=(LinearLayout.LayoutParams) menu.getLayoutParams();
		WindowManager manager=(WindowManager) getSystemService(Context.WINDOW_SERVICE);
		point=new Point();
		manager.getDefaultDisplay().getSize(point);
		width=point.x;
		menu_params.width=width-menu_padding;
		leftEdge=-menu_params.width;
		menu_params.leftMargin=leftEdge;
        content.getLayoutParams().width=width;
		content.setOnTouchListener(this);
	}



    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void start_working(String s) {

	}

	@Override
	public void start_working(Bitmap bitmap) {
	//	photo.setImageBitmap(bitmap);
	}

	@Override
	public void error_working() {

	}

    @Override
    public boolean onTouch(View v, MotionEvent event) {
		createVelocityTracker(event);
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				xDown=event.getRawX();
				break;
			case MotionEvent.ACTION_MOVE:
				xMove=event.getRawX();
				int distance=(int)(xMove-xDown);
				if(isVisiable){
					menu_params.leftMargin=distance;
				}
				else{
					menu_params.leftMargin=leftEdge+distance;
				}
				if(menu_params.leftMargin<leftEdge){
					menu_params.leftMargin=leftEdge;
				}
				if(menu_params.leftMargin>rightEdge){
					menu_params.leftMargin=rightEdge;
				}
				menu.setLayoutParams(menu_params);
				break;
			case MotionEvent.ACTION_UP:
				xUp=event.getRawX();
				if(wantShowMenu()){
					if(shouldShowMenu()){
						scrollMenu();
					}
					else{
						scrollContent();
					}
				}
				if(wantShowContent()){
					if(shouldShowcontent()){
						scrollContent();
					}
					else{
						scrollMenu();
					}
				}
				recicleVelocityTyacker();
				break;

		}
		return true;
	}

	private void scrollMenu(){
		new ScrollTask().execute(20);
	}
	private void scrollContent(){
		new ScrollTask().execute(-20);
	}
	private boolean shouldShowMenu(){
		return xUp-xDown>width/2||getScrollVelocity()>200;
	}

	private boolean shouldShowcontent(){
		return xDown-xUp>width/2||getScrollVelocity()>200;
	}

	private boolean wantShowMenu(){
		return xUp>xDown&&!isVisiable;
	}

	private boolean wantShowContent(){
		return xDown>xUp&&isVisiable;
	}
    class ScrollTask extends AsyncTask<Integer,Integer,Integer>{

		@Override
		protected Integer doInBackground(Integer... params) {
			int left_margin=menu_params.leftMargin;
			while(true){
				left_margin=left_margin+params[0];
                if(left_margin<leftEdge){

                    left_margin=leftEdge;
                    break;
                }
                if(left_margin>rightEdge){
                    left_margin=rightEdge;
                    break;
                }
                publishProgress(left_margin);
                sleep(20);
			}
            if(params[0]<0){
                isVisiable=false;
            }else{
                isVisiable=true;
            }
            return left_margin;
		}
        @Override
        protected void onProgressUpdate(Integer... left_margin){
            menu_params.leftMargin=left_margin[0];
            menu.setLayoutParams(menu_params);
        }
        @Override
        protected void onPostExecute(Integer left_margin){
            menu_params.leftMargin=left_margin;
            menu.setLayoutParams(menu_params);
        }
	}

	private int getScrollVelocity() {
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}

	private void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void createVelocityTracker(MotionEvent event){
		if(mVelocityTracker==null){
			mVelocityTracker=VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}
	private void recicleVelocityTyacker(){
		mVelocityTracker.recycle();
		mVelocityTracker=null;
	}
}
