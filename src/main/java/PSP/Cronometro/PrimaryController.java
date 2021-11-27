package PSP.Cronometro;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import PSP.Cronometro.Modelo.Contador;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PrimaryController implements Observer{
	@FXML
	private Text texto;
	@FXML
	private Button inicia;
	@FXML
	private Button pausar;
	@FXML
	private Button reiniciar;
	public Contador con;
	public Thread t;
	@FXML
	public void initialize() {
		pausar.setDisable(true);
		reiniciar.setDisable(true);
	}
	
	@FXML
	private void iniciar() {
		con=new Contador();
		t=new Thread(con);
		con.addObserver(this);
		t.start();
		inicia.setDisable(true);
		pausar.setDisable(false);
		reiniciar.setDisable(false);
	}
	@FXML
	private void para() {
		if(!con.getSuspendido().isSuspendido()) {
			con.getSuspendido().setSuspendido(true);
			pausar.setText("Continuar");
		}else if(con.getSuspendido().isSuspendido()) {
			con.getSuspendido().setSuspendido(false);
			pausar.setText("Parar");
		}
		
	}
	@FXML
	private void reinicia() {
		texto.setText("0:0:0");
		con.getSuspendido().setSuspendido(true);
		t.interrupt();
		con.setSegundos(0);
		con.setMinutos(0);
		con.setHoras(0);
		pausar.setDisable(true);
		reiniciar.setDisable(true);
		inicia.setDisable(false);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.texto.setText((String) arg1);
		
	}
    @FXML
    public void closeApp() {
    	System.exit(0);
    }
}
