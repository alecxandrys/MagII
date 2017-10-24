package FX;

import Util.Map;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application{

	Thread unitThread;
	static int lineLength=30;
	private int width=900;
	private int height=720;

	static long refreshRate=500;
	public static int xSize=30;
	public static int ySize=23;

	@Override
	public void start(Stage primaryStage){

		new Map(xSize,ySize);
		Canvas field=new Canvas(width,height);
		GraphicsContext gcField=field.getGraphicsContext2D();

		Canvas unit=new Canvas(width,height);
		GraphicsContext gcUnit=unit.getGraphicsContext2D();


		TextArea log=new TextArea("Лог сообщении\n");
		log.setEditable(false);
		log.setMaxWidth(300);
		FlowPane topPane=new FlowPane();

		Button btn=new Button("Начать симуляцию");
		btn.addEventHandler(ActionEvent.ACTION,event->{
			Button button=(Button) event.getTarget();
			if( button.getText().equals("Начать симуляцию") ){
				unitThread=new Thread(new UnitController(gcUnit,log));
				unitThread.start();
				button.setText("Остановить");
			}else if( button.getText().equals("Остановить") ){
				unitThread.interrupt();
				log.appendText("Симуляция остановлена");
				button.setDisable(true);
		}});
		topPane.getChildren().add(btn);
		BorderPane root=new BorderPane();
		root.setTop(topPane);
		Pane pane=new Pane();
		pane.getChildren().addAll(field,unit);
		root.setCenter(pane);
		root.setLeft(log);

		gcField.setStroke(Color.BLACK);
		gcField.strokeLine(0,0,width,0);
		gcField.strokeLine(0,0,0,height);
		for( int i=0; i<xSize; i++ ){//width=x
			for( int j=0; j<ySize; j++ ){//height=y
				//заливка поля
				gcField.strokeLine(i*lineLength,(j+1)*lineLength,(i+1)*lineLength,(j+1)*lineLength);
				gcField.strokeLine((i+1)*lineLength,j*lineLength,(i+1)*lineLength,(j+1)*lineLength);
			}
		}

		Scene scene=new Scene(root,1200,1000);

		primaryStage.setTitle("MagII");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(event->{
			System.out.println("exit");
			System.exit(0);
		});

	}

	public static void main(String[] args){
		launch(args);
	}
}