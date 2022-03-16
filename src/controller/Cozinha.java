package controller;

import java.util.concurrent.Semaphore;

public class Cozinha  extends Thread {
	private int threadId;
	String pratos;
	private Semaphore semaforo;
	
	public Cozinha (int threadId, Semaphore semaforo) {
		this.threadId = threadId;
		this.semaforo = semaforo;
	}
	
	public void run () {
		cozimento();
		entrega();
	}
	
	private void entrega() {
		
		if (threadId % 2 == 0) {
			pratos = "Lasanha de bolonhesa";
			
		}
		
		else {
			pratos = "Sopa de cebola";
		}
		
		try {
			semaforo.acquire();
			System.out.println (pratos + " esta sendo entregue.");
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		} finally {
			semaforo.release();
		}
		
		System.out.println(pratos + " foi entregue.");
	}
	
	private void cozimento() {
		int t = 0;
		int cont = 0;
		
		if (threadId % 2 == 0) {
			pratos = "Lasanha de bolonhesa";
			System.out.println ("Começa o cozimento da " + pratos + ".");
			
			t = (int) ((int) ((Math.random()*601)+600));
		}
		
		else {
			pratos = "Sopa de cebola";
			System.out.println("Começa o cozimento da " + pratos + ".");
			
			t = (int) ((int) ((Math.random()*301)+500));
		}
		
		while (cont < t) {
			try {
				if (cont + 100 <= t) 
					cont +=100;	
				
				else { 
					cont += t - cont;
				}
				
				System.out.println ("Cozinheiro " + threadId + ": " + pratos + " " + (cont*100)/t + "%");
				sleep(100);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
			 System.out.println(pratos + " esta pronta para entregar.");
			}
		}
				
	}


