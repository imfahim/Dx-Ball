package avi.dx;

import android.graphics.Paint;


public class Wall {
	
	public float LeftWall, RightWall, TopWall, BotWall;
	
	public Paint objPaint;
	
	private int ColorWall;
	
	
	public Wall(float top, float bottom, float left, float right, int r,int g,int b){
		this.TopWall = top;
		this.BotWall = bottom;
		this.LeftWall = left;
		this.RightWall = right;
		
		
		this.objPaint = new Paint();
		
		this.objPaint.setColor(this.ColorWall);
		this.objPaint.setARGB(255, r, g, b);
		
	}
	
}
