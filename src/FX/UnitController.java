package FX;

import Agent.BaseAgent;
import Agent.Civilian.Civilian;
import Agent.Civilian.Healer;
import Agent.Military.Archer;
import Agent.Military.Berserk;
import Agent.Military.Military;
import Util.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class UnitController implements Runnable{

	private GraphicsContext gc;
	private TextArea log;
	static public ArrayList< BaseAgent > redTeam;
	static public ArrayList< BaseAgent > blueTeam;

	UnitController(GraphicsContext gc,TextArea log){
		this.gc=gc;
		this.log=log;
		int count=(int) (Math.random()*9+1);//1-10
		redTeam=new ArrayList<>(count);
		blueTeam=new ArrayList<>(count);
		int t0=1, t1=1, t2=1, t3=1, t4=1;
		for( int i=0; i<count; i++ ){
			int type=(int) (Math.random()*5);
			switch( type ){
				case 0:{
					redTeam.add(place(new Civilian("Red Civilian #"+t0)));
					t0++;
					break;
				}
				case 1:{
					redTeam.add(place(new Healer("Red Healer #"+t1)));
					t1++;
					break;
				}
				case 2:{
					redTeam.add(place(new Military("Red Military #"+t2)));
					t2++;
					break;
				}
				case 3:{
					redTeam.add(place(new Berserk("Red Berserk #"+t3)));
					t3++;
					break;
				}
				case 4:{
					redTeam.add(place(new Archer("Red Archer #"+t4)));
					t4++;
					break;
				}
				default:{break;}
			}
		}
		t0=1;
		t1=1;
		t2=1;
		t3=1;
		t4=1;
		for( int i=0; i<count; i++ ){
			int type=(int) (Math.random()*5);
			switch( type ){
				case 0:{
					blueTeam.add(place(new Civilian("Blue Civilian #"+t0)));
					t0++;
					break;
				}
				case 1:{
					blueTeam.add(place(new Healer("Blue Healer #"+t1)));
					t1++;
					break;
				}
				case 2:{
					blueTeam.add(place(new Military("Blue Military #"+t2)));
					t2++;
					break;
				}
				case 3:{
					blueTeam.add(place(new Berserk("Blue Berserk #"+t3)));
					t3++;
					break;
				}
				case 4:{
					blueTeam.add(place(new Archer("Blue Archer #"+t4)));
					t4++;
					break;
				}
				default:{break;}
			}
		}
	}

	@Override
	public void run(){
		log.appendText("Симуляция началась\n");
		while( !Thread.interrupted() ){
			gc.clearRect(0,0,Main.xSize*Main.lineLength,Main.ySize*Main.lineLength);

			redTeam.forEach((elem)->{
				elem.activity();
				gc.setFill(Color.RED);
				gc.fillOval(elem.getCoordinate()[0]*Main.lineLength,elem.getCoordinate()[1]*Main.lineLength,Main.lineLength,Main.lineLength);
				gc.setFill(Color.BLACK);
				gc.fillText(String.valueOf(elem.getLiteral()),elem.getCoordinate()[0]*Main.lineLength,elem.getCoordinate()[1]*Main.lineLength,Main.lineLength);
			});
			blueTeam.forEach((elem)->{
				elem.activity();
				gc.setFill(Color.BLUE);
				gc.fillOval(elem.getCoordinate()[0]*Main.lineLength,elem.getCoordinate()[1]*Main.lineLength,Main.lineLength,Main.lineLength);
				gc.setFill(Color.BLACK);
				gc.fillText(String.valueOf(elem.getLiteral()),elem.getCoordinate()[0]*Main.lineLength,elem.getCoordinate()[1]*Main.lineLength,Main.lineLength);
			});
			try{
				Thread.sleep(Main.refreshRate);
			} catch( InterruptedException e ){
				e.printStackTrace();
				log.appendText("Симуляция закончилась\n");
			}
		}
		log.appendText("Симуляция закончилась\n");
	}

	public BaseAgent place(BaseAgent a){
		boolean empty=false;
		while( !empty ){
			int i=(int) (Math.random()*Main.xSize);
			int j=(int) (Math.random()*Main.ySize);

			if( !Map.placed[i][j] ){
				a.setCoordinate(i,j);
				empty=true;
				Map.placed[i][j]=true;
			}
		}
		return a;
	}

}
