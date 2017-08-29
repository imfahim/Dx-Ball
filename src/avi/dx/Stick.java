package avi.dx;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Stick {
	
	public float StickLeft, StickRight, StickTop, StickBot, Wide;
	
	public Stick(){
		this.StickTop = 0;
		this.StickBot = 0;
		this.StickLeft = 0;
		this.StickRight = 0;
		
		this.Wide = 0;
	}
	
    
    //Set Features for Bar
    public void setSt(Canvas canvas) 
    {
    	this.StickLeft = (canvas.getWidth()/2) - (canvas.getWidth()/8);
    	this.StickRight = (canvas.getWidth()/2) + (canvas.getWidth()/8);
        this.StickBot = canvas.getHeight() - 30;
        this.StickTop = this.StickBot - 20;
        this.Wide = this.StickLeft + this.StickRight;
    }
    
    // Draw the Bar
    public void drawSt(Canvas canvas, Paint paint)
    {
    	canvas.drawRect(this.StickLeft, this.StickTop, this.StickRight, this.StickBot, paint);
    }
}
