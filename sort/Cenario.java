// Grupo 7
// Gabriel Ely
// Jorge Medeiros
// William Azevedo de Castro

package sort;

import java.util.Random;

public class Cenario {
	// Atributo para criação de um array comum.
	private int[] escopo;

	// Construtor.
	public Cenario(int tamanho) {
		this.escopo = new int[tamanho];
	}

	// Escopo será preenchido em ordem ascendente.
	public int[] ordemAscendente() {
		for (int i = 0; i < escopo.length; i++) {
			escopo[i] = i + 1;
		}
		return escopo;
	}

	// Escopo será preenchido em ordem descendente.
	public int[] ordemDescendente() {
		for (int i = 0, j = escopo.length; i < escopo.length; i++, j--) {
			escopo[i] = j;
		}
		return escopo;
	}

	// Escopo será preenchido aleatoriamente, mas sem repetição.
	public int[] ordemAleatoria() {
		// Primeiramente é criado um escopo em ordem ascendente, para não ter repetições.
		ordemAscendente();
		
		// Realização do embaralhamento do array.
		Random random = new Random();
		for (int i = 0; i < (this.escopo.length - 1); i++) {
			int j = random.nextInt(this.escopo.length);
			int temp = escopo[i];
			escopo[i] = escopo[j];
			escopo[j] = temp;
		}
		return escopo;
	}	
	
	// Escopo será preenchido aleatoriamente com repetições de 5% do tamanho do array para cada chave.
	public int[] ordemAleatoriaRepetida() {
		int i = 0;
		int aux = 1;
		
		// Guarda o valor de 5% sobre o tamanho do escopo.
		int numRepeticoes = (int) (escopo.length * 0.05);
		
		// Repete o número de vezes necessário para cada chave.
		while(i < escopo.length) {
			for (int j = 0; j < numRepeticoes && i < escopo.length; j++, i++) {
				escopo[i] = aux;
			}
			aux++;
		}
		
		// Realização do embaralhamento do array.
		Random random = new Random();
		for (int a = 0; a < (this.escopo.length - 1); a++) {
			int b = random.nextInt(this.escopo.length);
			int temp = escopo[a];
			escopo[a] = escopo[b];
			escopo[b] = temp;
		}
		return escopo;
	}

	// Impressão na tela dos valores do escopo.
	public String toString() {
		String t = "";
		for (int i = 0; i < this.escopo.length; i++)
			t += this.escopo[i] + " ";
		return t;
	}

}
