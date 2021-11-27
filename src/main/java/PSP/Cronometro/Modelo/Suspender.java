package PSP.Cronometro.Modelo;

public class Suspender {
	private boolean suspendido;
	
	public synchronized void setSuspendido(Boolean b) {
		this.suspendido=b;
		notifyAll();
	}
	
	public boolean isSuspendido() {
		return suspendido;
	}

	

	public synchronized void esperaReanudar() throws InterruptedException {
		while(this.suspendido){
			wait();
		}
		
	}
}
