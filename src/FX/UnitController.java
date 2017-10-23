package FX;

import Agent.BaseAgent;
import Agent.Civilian.Civilian;
import Agent.Civilian.Medic;
import Agent.Military.Berserk;
import Agent.Military.Bowman;
import Agent.Military.Military;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class UnitController implements Runnable {

    private GraphicsContext gc;
    private TextArea log;
    private ArrayList<BaseAgent> redTeam;
    private ArrayList<BaseAgent> blueTeam;

    UnitController(GraphicsContext gc, TextArea log) {
        this.gc = gc;
        this.log = log;
        int count = (int) (Math.random() * 9 + 1);//1-10
        redTeam = new ArrayList<>(count);
        blueTeam = new ArrayList<>(count);
        int t0=1,t1=1,t2=1,t3=1,t4=1;
        for (int i=0;i<count;i++)
        {
            int type=(int) (Math.random()*5);
            switch (type)
            {
                case 0:{redTeam.add(new Civilian("Red Civilian #"+t0));t0++;break;}
                case 1:{redTeam.add(new Medic("Red Medic #"+t1));t1++;break;}
                case 2:{redTeam.add(new Military("Red Military #"+t2));t2++;break;}
                case 3:{redTeam.add(new Berserk("Red Berserk #"+t3));t3++;break;}
                case 4:{redTeam.add(new Bowman("Red Bowman #"+t4));t4++;break;}
                default:{break;}
            }
        }
        t0=1;t1=1;t2=1;t3=1;t4=1;
        for (int i=0;i<count;i++)
        {
            int type=(int) (Math.random()*5);
            switch (type)
            {
                case 0:{blueTeam.add(new Civilian("Blue Civilian #"+t0));t0++;break;}
                case 1:{blueTeam.add(new Medic("Blue Medic #"+t1));t1++;break;}
                case 2:{blueTeam.add(new Military("Blue Military #"+t2));t2++;break;}
                case 3:{blueTeam.add(new Berserk("Blue Berserk #"+t3));t3++;break;}
                case 4:{blueTeam.add(new Bowman("Blue Bowman #"+t4));t4++;break;}
                default:{break;}
            }
        }
    }

    @Override
    public void run() {
        log.appendText("Симуляция началась\n");
		while(!Thread.interrupted())
		{
			redTeam.forEach(BaseAgent::activity);
			blueTeam.forEach(BaseAgent::activity);
			try{
				Thread.sleep(Main.refreshRate);
			} catch( InterruptedException e ){
				e.printStackTrace();
			}
		}
        log.appendText("Симуляция закончилась\n");
    }

}
