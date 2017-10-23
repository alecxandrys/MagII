package FX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application{


	Thread unitThread;
	private int lineLength=30;
	private int width=900;
	private int height=720;

	static long refreshRate=500;
	static int xSize=30;
	static int ySize=23;

	@Override
	public void start(Stage primaryStage){

		Canvas field=new Canvas(width,height);
		GraphicsContext gcField=field.getGraphicsContext2D();

		Canvas unit=new Canvas(width,height);
		GraphicsContext gcUnit=unit.getGraphicsContext2D();


		TextArea log=new TextArea("Лог сообщении\n");
		log.setEditable(false);
		log.setMaxWidth(300);

		BorderPane root=new BorderPane();

		Button btn=new Button();
		btn.setText("Начать симуляцию");
		btn.addEventHandler(ActionEvent.ACTION,event->{
			Button button=(Button) event.getTarget();
			if( button.getText().equals("Начать симуляцию") ){
				unitThread=new Thread(new UnitController(gcUnit,log));
				unitThread.start();
				button.setText("Пауза");
				Button stop=new Button("Остановить");
				stop.addEventHandler(ActionEvent.ACTION,event1->{
					button.setDisable(true);
					unitThread.interrupt();
				});
				root.setTop(new Button());
			}else if( button.getText().equals("Пауза") ){
				try{
					unitThread.wait();
					button.setText("Продолжить");
					log.appendText("Симуляция остановлена\n");
				} catch( InterruptedException e ){
					e.printStackTrace();
				}
			}else if( button.getText().equals("Пауза") ){
				unitThread.notify();
				log.appendText("Симуляция продолжена\n");
				button.setText("Пауза");
			}
		});

		root.setTop(btn);
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