// Grupo 7
// Gabriel Ely
// Jorge Medeiros
// William Azevedo de Castro

package sort;

public class CenarioTest {
	// Atributos para o armazenamento dos tempos de execu��o e dos cen�rios,
	// respectivamente.
	private static double tempos[] = new double[10];
	private static int array[][] = new int[10][];

	public static void main(String[] args) {
		int tamanho = 128;

		// Cen�rio
		// Cria��o de v�rios cen�rios de mesmo tamanho e tipo.
		Cenario a1 = new Cenario(tamanho);
		Cenario a2 = new Cenario(tamanho);
		Cenario a3 = new Cenario(tamanho);
		Cenario a4 = new Cenario(tamanho);
		Cenario a5 = new Cenario(tamanho);
		Cenario a6 = new Cenario(tamanho);
		Cenario a7 = new Cenario(tamanho);
		Cenario a8 = new Cenario(tamanho);
		Cenario a9 = new Cenario(tamanho);
		Cenario a10 = new Cenario(tamanho);
		// Chamada dos m�todos necess�rios para obten��o dos tempos de execu��o do
		// m�todo de classifica��o.
		arrayFill(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
		tempoExecucao(array);
		verificaValores(tempos);
	}

	// M�todo preenche a matriz com v�rios cen�rios iguais.
	public static void arrayFill(Cenario a, Cenario b, Cenario c, Cenario d, Cenario e, Cenario f, Cenario g, Cenario h,
			Cenario i, Cenario j) {
		// OBS: para mudar o cen�rio deve-se alterar o m�todo de gera��o de cen�rio
		// (ordemAscendente(), ordemDescendente(), ordemAletoria() e
		// ordemAleatoriaRepetida()).
		array[0] = a.ordemAscendente();
		array[1] = b.ordemAscendente();
		array[2] = c.ordemAscendente();
		array[3] = d.ordemAscendente();
		array[4] = e.ordemAscendente();
		array[5] = f.ordemAscendente();
		array[6] = g.ordemAscendente();
		array[7] = h.ordemAscendente();
		array[8] = i.ordemAscendente();
		array[9] = j.ordemAscendente();
	}

	// M�todo que realiza as 10 repeti��es do mesmo cen�rio, tamanho e m�todo de
	// classifica��o.
	// OBS: para mudar o m�todo de classifica��o deve mudar o m�todo entre ///.
	// OBS: para mudar o cen�rio deve-se mudar a ordem no arrayFill().
	public static void tempoExecucao(int a[][]) {
		int count = 0;
		System.out.println("Valores antes de serem verificados:");
		while (count < 10) {
			// In�cio da contagem de tempo em nanosegundos.
			double init = System.nanoTime();
			///
			bubbleSort(a[count]);
			///
			// Fim da contagem de tempo.
			double finish = System.nanoTime();
			double time = (finish - init);

			// Tempo armazenado em um array.
			System.out.println(count + ": " + time);
			tempos[count++] = time;
			init = finish = 0;
		}
	}

	// M�todo para verificar se os valores obtidos em nanosegundos est�o dentro do
	// desvio padr�o.
	public static void verificaValores(double tempos[]) {
		// Vari�vel para m�dia, vari�ncia e quantidade de tempos n�o inclu�dos no
		// c�lculo da nova m�dia.
		double media = 0;
		double var = 0;
		int fora = 0;

		// C�lculo da m�dia de todos os tempos.
		for (int i = 0; i < tempos.length; i++) {
			media += tempos[i];
		}
		media = (media / tempos.length);

		// Somat�rio de todos os tempos menos a m�dia para o c�lcula da vari�ncia.
		for (int i = 0; i < tempos.length; i++) {
			var += Math.pow((tempos[i] - media), 2);
		}

		// Divis�o do somat�rio para vari�ncia e c�lculo do desvio padr�o.
		var = (var / (tempos.length - 1));
		double desvio = Math.sqrt(var);

		// Verifica se o tempo do m�todo de classifica��o est� dentro do desvio e soma a
		// quantidade de elementos que n�o est�o dentro.
		// Tamb�m exibe na tela os valores v�lidos para a m�dia.
		System.out.println("\nValores v�lidos:");
		for (int i = 0; i < tempos.length; i++) {
			if (tempos[i] > (media + desvio) || tempos[i] < (media - desvio)) {
				tempos[i] = 0;
				fora++;
			}
			else {
				System.out.println(i + ": " + tempos[i]);
			}
		}

		// Recalcula a m�dia com os tempos v�lidos,
		System.out.println("M�dia antes: " + media);
		media = 0;
		for (int i = 0; i < tempos.length; i++) {
			media += tempos[i];
		}
		media = (media / (tempos.length - fora));
		System.out.println("Desvio padr�o: " + desvio);
		System.out.println("M�dia depois: " + media);
	}

	// Abaixo se encontram todos os m�todos de classifca��o. - faltam Merge e Quick

	public static <T extends Comparable<? super T>> void bubbleSort(int[] a) {
		boolean exchange;
		do {
			exchange = false;
			for (int i = 0; i < a.length - 1; i++) {
				if (Integer.compare(a[i], a[i + 1]) > 0) {
					exchange(a, i, i + 1);
					exchange = true;
				}
			}
		} while (exchange);
	}

	public static <T extends Comparable<? super T>> void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0 && Integer.compare(a[j - 1], a[j]) > 0; j--) {
				exchange(a, j - 1, j);
			}
		}
	}

	public static <T extends Comparable<? super T>> void selectionSort(int[] a) {
		for (int min, i = 0; i < a.length; i++) {
			min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (Integer.compare(a[j], a[min]) < 0) {
					min = j;
				}
			}
			exchange(a, min, i);
		}
	}

	public static <T extends Comparable<? super T>> void heapSort(int[] a) {
		buildMaxHeap(a);
		for (int i = a.length - 1; i > 0; i--) {
			exchange(a, 0, i);
			maxHeapify(a, 0, i);
		}
	}

	private static <T extends Comparable<? super T>> void buildMaxHeap(int[] a) {
		for (int i = a.length / 2 - 1; i >= 0; i--) {
			maxHeapify(a, i, a.length);
		}
	}

	private static <T extends Comparable<? super T>> void maxHeapify(int[] a, int i, int n) {
		int max = 2 * i + 1;
		if (max + 1 < n && Integer.compare(a[max], a[max + 1]) < 0)
			max++;
		if (max < n && Integer.compare(a[max], a[i]) > 0) {
			exchange(a, i, max);
			maxHeapify(a, max, n);
		}
	}

	public static <T extends Comparable<? super T>> void shellSort(int[] a) {
		int h = 1;
		while (3 * h + 1 < a.length)
			h = 3 * h + 1; // Baseado no h do Knuth
		while (h > 0) {
			for (int i = h; i < a.length; i++) {
				for (int j = i; j >= h && Integer.compare(a[j - h], a[j]) > 0; j -= h) {
					exchange(a, j - h, j);
				}
			}
			h /= 3;
		}
	}

	private static <T extends Comparable<? super T>> void exchange(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

}
