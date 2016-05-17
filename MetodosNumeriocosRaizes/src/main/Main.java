/**
 * @author anusio
 */
package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import metodos.Exibir;
import metodos.Funcao;
import metodos.Metodos;
import metodos.Resposta;

public class Main {

	public static void main(String[] args) {
		entre[] cincoprimeiros = new entre[5];
		Resposta r;
		// para a=1
		// cincoprimeiros[0] = new entre(4, 4.5);
		// cincoprimeiros[1] = new entre(7.5, 7.8);
		// cincoprimeiros[2] = new entre(10.8, 10.99);
		// cincoprimeiros[3] = new entre(14, 14.09);
		// cincoprimeiros[4] = new entre(17, 17.222);

		/* para a=2 */

		cincoprimeiros[0] = new entre(1.9, 2.1);
		cincoprimeiros[1] = new entre(3.6, 3.8);
		cincoprimeiros[2] = new entre(5.2, 5.4);
		cincoprimeiros[3] = new entre(6.8, 7);
		cincoprimeiros[4] = new entre(8.5, 8.6);
		// func(a)
		func f1 = new func(2);
		/*
		 * o predestinado é nome de um filme em que o cara e pai e mãe e
		 * filha(o) dele(ela) mesmo e se mata para se tornar o que ele matou
		 */

		// exibir delalhadamente as iteracoes do metodo
		Exibir exibir = new Exibir();
		// os metodos
		Metodos metodos1 = new Metodos(f1, exibir);
		// de 100 pra 1000 iteracoes
		metodos1.setMax_interacao(1000);
		// so pra saber qdo iniciou
		System.out.println("iniciando f1");
		for (entre e : cincoprimeiros) {
			// vai comentando e descomentando ai, a ideia era uma tabela com
			// todos para comparar mas ums nao converjiram e deu treta, mas
			// agora ta certo, porem é uma tabela por metodo
			//newline pro arquivo (que arquivo? vc pergunta ¬¬)
			exibir.newline();
			// doRes(metodos1.bissecao(e.a, e.b), f1);
			doRes(metodos1.falsa_posicao(e.a, e.b), f1);
			// doRes(metodos1.ponto_fixo(e.a), f1);
			// doRes(metodos1.nweton_raphson(e.b), f1);
			// doRes(metodos1.nweton_raphson(e.b), f1);
			// doRes(metodos1.secante(e.a+.1, e.b), f1);
		}
		//este aqui (o arquivo...  X)
		File fp = new File("resp.txt");
		try {
			FileWriter fw = new FileWriter(fp);
			fw.write(exibir.getText());
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//para saber quando acabou
		System.out.println("done");
	}
	/**
	 * para imprimir no formato do tabularx do latex...
	 * @param r
	 * @param f
	 */
	private static void doRes(Resposta r, Funcao f) {
		String text;
		if (r == null) {
			text = "nao convergiu";
		} else {
			//11 zeros pode contar
			DecimalFormat df = new DecimalFormat("00.00000000000");
			text = r.getI() + " & " + df.format(r.getX()) + " & " + df.format(f.f(r.getX())) + " & "
					+ df.format(r.getEa()) + "\\\\";
		}
		System.out.println(text);
	}

}
//so para fazer um vetor (coisa de c struct...)
class entre {
	public double a, b;

	public entre(double a, double b) {
		this.a = a;
		this.b = b;
	}
}
/**
 * uma classe com a função do professor
 * @author Anusio
 *
 */
class func extends Funcao {
	private double a;

	public func(double a) {
		this.a = a;
	}

	@Override
	public double f(double x) {
		return (x / a) - Math.tan(a * x);
	}

	@Override
	public double fl(double x) {
		// 1/a-a*sec^2(ax)
		return (1 / a) - (a * Math.pow(secante(a * x), 2));
	}

	@Override
	public double g(double x) {
		// 1/(2*tan(2*x)) - 1/x + x
		return 1 / (a * Math.tan(a * x)) - 1 / x + x;
	}

	private double secante(double x) {
		return 1 / Math.cos(x);
	}
}