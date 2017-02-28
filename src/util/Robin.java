package util;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import proceso.Proceso;

public class Robin {
	private Queue<Proceso> cola = new ArrayDeque<>();
	private int quantum;
	private int tiempoDecontexto;
	private int cambioDeContexto;
	private ArrayList<Proceso> procesos;
	public ArrayList<Proceso> procesosFinales;
	private int procesosDisponibles;
	private int tiempo = 0;
	private boolean run = true;
	private boolean cambiandoDeContexto;
	public ArrayList<Registro> registro = new ArrayList<>();

	public Robin(int quantum, int cambio, ArrayList<Proceso> procesos) {
		this.quantum = quantum;
		this.cambioDeContexto = cambio;
		this.procesos = procesos;
		this.tiempoDecontexto = 0;
		this.procesosDisponibles = procesos.size();
		this.procesosFinales = new ArrayList<>();
	}

	public void ejecutar() {
		while (run) {
			actualizar(tiempo);
			tiempo += 1;

	
		}
	}

	private void actualizar(int tiempo) {
		// si la lista de procesos disponibles esta vacia
		if (procesosDisponibles == 0) {
			run = false;
		} else {
			if (!cola.isEmpty()) {
				if (cola.peek().isEjecucion()) {
					registro.add(new Registro(cola.peek().getColor(), tiempo));
				} else {
					registro.add(new Registro(Color.gray, tiempo));
				}
			} else {
				registro.add(new Registro(Color.black, tiempo));
			}
			// agregar a la cola los procesos que entrar el tiempo actual
			buscar(tiempo);

			if (!cambiandoDeContexto) {

				if (!cola.isEmpty() && cola.peek().isEjecucion() && cola.peek().getTiempoEjecutado() == 0) {
					System.out.println("el proceso " + cola.peek().getNombre()
							+ " entro en ejecucion por primera vez en el tiempo:: " + tiempo);
					cola.peek().setTimempoDeInicio(tiempo);
				}

				// si el tiempo ejecutado es igual a el tiempo que se
				// debe
				// ejecutar

				if (!cola.isEmpty() && cola.peek().getTiempoEjecutado() == cola.peek().getTiempoDeEjecucion()) {
					procesosDisponibles--;
					System.out.println(
							"se a terminado el proceso " + cola.peek().getNombre() + " en el tiempo " + tiempo);
					System.out.println("tiempo de espera " + cola.peek().getTiempoRequetido());

					if (cola.size() == 1 || cambioDeContexto == 0) {
						cambiandoDeContexto = false;
					} else {
						cambiandoDeContexto = true;
					}
					cola.peek().setTimempoFinal(tiempo);

					// agrega los procesos con su estado final a la lista
					procesosFinales.add(cola.peek());
					// se remueve el proceso de la cola
					cola.remove();

				}

				// si el tiempo ejecutado es igual al tiempo de
				// ejecucion

				if (!cola.isEmpty()) {

					// si el tiempo que demora en un quamtun es igual al
					// quantum
					if ((cola.peek().getTiempoEjecutadoPorQuatum() == quantum)) {

						System.out.println("se finalizo el tiempo del proceso " + cola.peek().getNombre()
								+ " en el tiempo " + tiempo);

						if (cola.size() == 1 || cambioDeContexto == 0) {
							cambiandoDeContexto = false;
						} else {
							cambiandoDeContexto = true;
						}
						cola.peek().setTiempoEjecutadoPorQuatum(0);
						cola.peek().setEjecucion(false);
						cola.add(cola.remove());
						cola.peek().setEjecucion(false);
					}

					// si aun no esta vacia pone el sigiende proceso
					// en
					// ejecucion

					if (!cambiandoDeContexto && !cola.isEmpty() && !cola.peek().isEjecucion()) {
						tiempoDecontexto = 0;
						cola.peek().setEjecucion(true);
						if (cola.peek().getTiempoEjecutado() == 0) {
							System.out.println("el proceso " + cola.peek().getNombre()
									+ " entro en ejecucion por primera vez en el tiempo " + tiempo);
							cola.peek().setTimempoDeInicio(tiempo);
						} else {
							System.out.println("el proceso " + cola.peek().getNombre()
									+ " entro en ejecucion el tiempo " + tiempo);
						}

					}
				}

			}

			if (cambiandoDeContexto) {

				if (tiempoDecontexto < cambioDeContexto) {
					tiempoDecontexto++;
				}
				if (tiempoDecontexto == cambioDeContexto) {
					cambiandoDeContexto = false;
					tiempoDecontexto = 0;
				}

			}

			// aumenta el tiempo en ejecucion del proceso
			if (!cola.isEmpty()) {
				if (!cambiandoDeContexto && cola.peek().isEjecucion()) {
					int tiempoEjecutado = cola.peek().getTiempoEjecutado();
					cola.peek().setTiempoEjecutado(tiempoEjecutado + 1);
					// aumenta el tiempo que toma un proceso cuando se esta
					// ejecutando hasta que se acabe su tiempo de ejecucion
					int tiempoEjecutadoPorQuantum = cola.peek().getTiempoEjecutadoPorQuatum();
					cola.peek().setTiempoEjecutadoPorQuatum(tiempoEjecutadoPorQuantum + 1);
				}
				// aumenta el tiempo de espera de los procesos en la cola
				for (Proceso proceso : cola) {
					int tiempoDeEspera = proceso.getTiempoRequetido();
					proceso.setTiempoRequetido(tiempoDeEspera + 1);
				}
			}
		}

	}

	//

	// busca el proceso en el tiempo de llegada expecificado
	//
	private void buscar(int tiempo) {

		System.out.println(tiempo);

		for (int i = 0; i < procesos.size(); i++) {

			if (procesos.get(i).getTiempoDeLlegada() == tiempo && !cola.contains(procesos.get(i))) {
				System.out.println("llego del proceso " + procesos.get(i).getNombre() + " en el tiempo " + tiempo);
				// return procesos.get(i);
				cola.add(procesos.get(i));
				if (cola.size() == 1 && !cambiandoDeContexto) {
					cola.peek().setEjecucion(true);
				}

			}
		}

	}

	public static void main(String[] args) {
		ArrayList<Proceso> p = new ArrayList<>();
		// Proceso pa = new Proceso("a", 0, 7);
		// Proceso pb = new Proceso("b", 2-2, 3);
		// Proceso pc = new Proceso("c", 6-6, 12);
		// Proceso pd = new Proceso("d", 20-20, 4);
		Proceso pa = new Proceso("a", 0, 10, Color.red);
		Proceso pb = new Proceso("b", 0, 4, Color.blue);
		Proceso pc = new Proceso("c", 0, 8, Color.green);
		Proceso pd = new Proceso("d", 0, 5, Color.yellow);
		Proceso pe = new Proceso("e", 0, 12, Color.orange);

		p.add(pa);
		p.add(pb);
		p.add(pc);
		p.add(pd);
		p.add(pe);
		Robin r = new Robin(5, 0, p);
		r.ejecutar();
	}

}
