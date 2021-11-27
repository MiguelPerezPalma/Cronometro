package PSP.Cronometro.Modelo;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Contador extends Observable implements Runnable{
	
	private int segundos;
	private int minutos;
	private int horas;
	private String tiempo;
	private Observer observer;
	private Suspender suspendido=new Suspender();

	public Contador() {
		this.segundos = 0;
		this.minutos = 0;
		this.horas = 0;
		this.suspendido.setSuspendido(false);
	}


	public int getSegundos() {
		return segundos;
	}


	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}


	public int getMinutos() {
		return minutos;
	}


	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}


	public int getHoras() {
		return horas;
	}


	public void setHoras(int horas) {
		this.horas = horas;
	}


	public String getTiempo() {
		return tiempo;
	}


	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}


	public Observer getObserver() {
		return observer;
	}


	public void setObserver(Observer observer) {
		this.observer = observer;
	}


	public Suspender getSuspendido() {
		return suspendido;
	}


	public void setSuspendido(Suspender suspendido) {
		this.suspendido = suspendido;
	}


	@Override
	public void run() {
		while(!this.suspendido.isSuspendido()) {
			tiempo=horas+":"+minutos+":"+segundos;
			setChanged();
			notifyObservers();
			segundos++;
			if(segundos==60) {
				minutos++;
				segundos=0;
			}
			if(minutos==60) {
				horas++;
				minutos=0;
			}
			
			try {
				Thread.sleep(1000);
				this.suspendido.esperaReanudar();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public synchronized void addObserver(Observer o) {
		this.observer = o;
	}
	@Override
	public void notifyObservers() {
		if(observer != null) {
			observer.update(this, tiempo);
		}
	}
}
