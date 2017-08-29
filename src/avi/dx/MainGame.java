package avi.dx;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;


public class MainGame extends View {
	
	public static boolean gameOver = false;
    boolean GameBegin = true;
    boolean StartGame;
    boolean needRandom=true;
    public static int score = 0;
    float Touch;
    int var,randbrx,randbry;
    int level;
    
    Canvas canvas;
    Paint paint;
    Stick st;
    Ball ball;
    public boolean count=false;
    ArrayList<Wall>wl =new ArrayList<Wall>();
    
    float row, brickH = 70, wlX, wlY;
    int col;

    Random rand = new Random();
    
	public MainGame(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		this.paint = new Paint();
		
		
		StartGame = true;
		gameOver = false;
		
		st = new Stick();
		ball = new Ball(10);
		
		level = 1;
		 
		setOnTouchListener(new OnTouchListener()
		{
			public boolean onTouch(View v,MotionEvent m)
			{
				if(ball.isAvailable())
				{
					Touch = m.getX();
					if(Touch < v.getWidth()/2 && st.StickLeft>0)
					{
						st.StickLeft=st.StickLeft-20;
						st.StickRight=st.StickRight-20;
						Touch = -20;
					}
					else if(Touch >= v.getWidth()/2 && st.StickRight<v.getWidth())
					{
						st.StickLeft=st.StickLeft+20;
						st.StickRight=st.StickRight+20;
						Touch = -20;//
					}
				}
				return true;
			}
		});
		
		
	}
	
	public void drawLifeText(Canvas canvas){
        paint.setTypeface(Typeface.create("TAHOMA",Typeface.BOLD));
        paint.setTextSize(35);
        canvas.drawText("SCORE : " + score,  140, 50, paint); 
    }
	
	public void RandomLevel(Canvas canvas,ArrayList<Wall> wls){
    	wlX=rand.nextInt(100) + 70;
    	wlY=rand.nextInt(30) + 10;
    	float bricWidth = ((canvas.getWidth()) / row);
        for (int i = 0; i < var; i++) {
            for (int j = 0; j < row; j++) {
            		col = Color.CYAN;
            	wls.add(new Wall(wlX, wlX + rand.nextInt(100) + 70,wlY, wlY + rand.nextInt(30) + 10, rand.nextInt(255) + 0,rand.nextInt(255) + 0,rand.nextInt(255) + 0));//
            	wlX = wlX+ bricWidth;
            }
            wlY = wlY+ brickH;
            wlX = rand.nextInt(100) + 70;//
        } 
    }
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		if(wl.size()<=0){
			count=true;
			needRandom=true;
		}
		super.onDraw(canvas);
		
		if(GameBegin){
		GameBegin=false;
		st.setSt(canvas);
		ball.setBall(canvas,st);
		}
		
		 canvas.drawRGB(0,0,0);

		 paint.setARGB(255,211, 211, 211);
		 paint.setStyle(Paint.Style.FILL);   
		 ball.drawBall(canvas,paint);
		 paint.setARGB(255,211, 211, 211);
		 st.drawSt(canvas,paint);
		 ball.nextPos(canvas, st, paint);
		 
		 drawLifeText(canvas);
		 
		 if(StartGame)
		 {
			 StartGame=false;
			 if(level == 1)
			 {
				 if(!count){
				 if(needRandom){
					 var=rand.nextInt(7) + 2;//
					 needRandom=false;
					 row=rand.nextInt(5) + 1;
				 }
				 this.RandomLevel(canvas, wl);
				 }
				 else{
					 count=false;
					 if(needRandom){
						 var=rand.nextInt(7) + 2;//
						 needRandom=false;
						 row=rand.nextInt(5) + 1;
					 }
					 this.RandomLevel(canvas, wl);
					 }
					 
				 }
				
			 }
			 else
			 {
				gameOver=true;
			 }
		 
		 for(int i = 0; i< wl.size(); i++)
		 {
			 Wall b = wl.get(i);
		     canvas.drawRect(wl.get(i).LeftWall, wl.get(i).TopWall, wl.get(i).RightWall, wl.get(i).BotWall, wl.get(i).objPaint);
		 }
		 
		 invalidate();
		 
		 ball.detectCollision(wl, ball);
		 
		 if(ball.isAvailable() == false && gameOver==false)
		 {
		     ball.setAvailable(true);
		     GameBegin = true;
		     ball.NewCenterX=5;
		     ball.NewCenterY=-5;
		 }
		}
	}
