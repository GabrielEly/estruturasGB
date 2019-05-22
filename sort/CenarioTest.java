// Grupo 7
// Gabriel Ely
// Jorge Medeiros
// William Azevedo de Castro

package sort;

public class CenarioTest {
	// Atributos para o armazenamento dos tempos de execução e dos cenários,
	// respectivamente.
	private static double tempos[] = new double[10];
	private static int array[][] = new int[10][];

	public static void main(String[] args) {
		int tamanho = 128;

		// Cenário
		// Criação de vários cenários de mesmo tamanho e tipo.
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
		// Chamada dos métodos necessários para obtenção dos tempos de execução do
		// método de classificação.
		arrayFill(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
		tempoExecucao(array);
		verificaValores(tempos);
	}

	// Método preenche a matriz com vários cenários iguais.
	public static void arrayFill(Cenario a, Cenario b, Cenario c, Cenario d, Cenario e, Cenario f, Cenario g, Cenario h,
			Cenario i, Cenario j) {
		// OBS: para mudar o cenário deve-se alterar o método de geração de cenário
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

	// Método que realiza as 10 repetições do mesmo cenário, tamanho e método de
	// classificação.
	// OBS: para mudar o método de classificação deve mudar o método entre ///.
	// OBS: para mudar o cenário deve-se mudar a ordem no arrayFill().
	public static void tempoExecucao(int a[][]) {
		int count = 0;
		System.out.println("Valores antes de serem verificados:");
		while (count < 10) {
			// Início da contagem de tempo em nanosegundos.
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

	// Método para verificar se os valores obtidos em nanosegundos estão dentro do
	// desvio padrão.
	public static void verificaValores(double tempos[]) {
		// Variável para média, variância e quantidade de tempos não incluídos no
		// cálculo da nova média.
		double media = 0;
		double var = 0;
		int fora = 0;

		// Cálculo da média de todos os tempos.
		for (int i = 0; i < tempos.length; i++) {
			media += tempos[i];
		}
		media = (media / tempos.length);

		// Somatório de todos os tempos menos a média para o cálcula da variância.
		for (int i = 0; i < tempos.length; i++) {
			var += Math.pow((tempos[i] - media), 2);
		}

		// Divisão do somatório para variância e cálculo do desvio padrão.
		var = (var / (tempos.length - 1));
		double desvio = Math.sqrt(var);

		// Verifica se o tempo do método de classificação está dentro do desvio e soma a
		// quantidade de elementos que não estão dentro.
		// Também exibe na tela os valores válidos para a média.
		System.out.println("\nValores válidos:");
		for (int i = 0; i < tempos.length; i++) {
			if (tempos[i] > (media + desvio) || tempos[i] < (media - desvio)) {
				tempos[i] = 0;
				fora++;
			}
			else {
				System.out.println(i + ": " + tempos[i]);
			}
		}

		// Recalcula a média com os tempos válidos,
		System.out.println("Média antes: " + media);
		media = 0;
		for (int i = 0; i < tempos.length; i++) {
			media += tempos[i];
		}
		media = (media / (tempos.length - fora));
		System.out.println("Desvio padrão: " + desvio);
		System.out.println("Média depois: " + media);
	}

	// Abaixo se encontram todos os métodos de classifcação. - faltam Merge e Quick

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
