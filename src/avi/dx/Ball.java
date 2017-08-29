package avi.dx;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import avi.dx.Ball;
import avi.dx.Stick;


public class Ball {

	private boolean CheckBall;
	
	public float centerX, centerY,r,NewCenterX, NewCenterY;
	
	boolean firstTime;
	
	public Ball(float radius){
		this.CheckBall = false;
		this.centerX = 0;
		this.centerY = 0;
		this.r = radius;
		
		this.NewCenterX = 10;
		this.NewCenterY = -10;
		
		this.firstTime = true;
	}
	
	public void setAvailable(boolean check) 
	{
		this.CheckBall = check;
	}
	
	public boolean isAvailable() 
	{
		return this.CheckBall;
	}
	
	//Set Position for Ball
	public void setBall(Canvas canvas, Stick st)
	{
		float stCenter = (st.StickRight - st.StickLeft)/2;
		this.centerX = st.StickLeft + stCenter;
		this.centerY = (st.StickTop - this.r);
	}
	
	public void drawBall(Canvas canvas, Paint paint)
	{
		canvas.drawCircle(this.centerX, this.centerY, this.r, paint);
	}
	
	public void nextPos(Canvas canvas, Stick st, Paint paint){
		if(this.firstTime)
		{
			this.firstTime = false;
			this.centerX = canvas.getWidth() / 2;
			this.centerY = canvas.getHeight() / 2;
		}
		
		this.centerX += 4 * this.NewCenterX;
		this.centerY += 4* this.NewCenterY;
		
		if(this.centerX <= 0 + 20) this.NewCenterX *= -1;
		if(this.centerX >= canvas.getWidth() - 20) this.NewCenterX *= -1;
		if(this.centerY <= 0 + 20) this.NewCenterY *= -1;
		
		if(this.centerY + 45 >= canvas.getHeight() - 30) {
			if(this.centerX >= st.StickLeft && this.centerX <= st.StickRight){
				this.NewCenterX *= -1;
				this.NewCenterY *= -1;
			}
		}
		if(this.centerY >= canvas.getHeight())
			System.exit(0);
	}
	
	public void detectCollision(ArrayList<Wall> wl, Ball ball){
        for(int i = 0; i < wl.size(); i++) {
            if (((ball.centerY - ball.r) <= wl.get(i).BotWall) && ((ball.centerY + ball.r) >= wl.get(i).TopWall) && ((ball.centerX) >= wl.get(i).LeftWall) && ((ball.centerX) <= wl.get(i).RightWall)) {
            	wl.remove(i);
                MainGame.score++;
                ball.NewCenterY= -ball.NewCenterY;
            }
            else if(((ball.centerY) <= wl.get(i).BotWall) && ((ball.centerY) >= wl.get(i).TopWall) && ((ball.centerX + ball.r) >= wl.get(i).LeftWall) && ((ball.centerX - ball.r) <= wl.get(i).RightWall)) {
            	wl.remove(i);
            	MainGame.score++;
            	ball.NewCenterX= -ball.NewCenterX;
                //
            }
        }
    }
}
