/**
 * @author anusio
 */
package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import metodos.Exibir;
import metodos.Funcao;
import metodos.Metodos;
import metodos.Resposta;

public class Main {

	public static void main(String[] args) {
		entre[] cincoprimeiros = new entre[5];
		Resposta r;
		// [2,4],[6,8],[8,10],[12,14] e [14,16]
		cincoprimeiros[0] = new entre(2, 4);
		cincoprimeiros[1] = new entre(6, 8);
		cincoprimeiros[2] = new entre(8, 10);
		cincoprimeiros[3] = new entre(12, 14);
		cincoprimeiros[4] = new entre(14, 16);
		/* p a=2 */
		/*
		 * cincoprimeiros[0] = new entre(1, 2); cincoprimeiros[1] = new entre(3,
		 * 4); cincoprimeiros[2] = new entre(4, 5); cincoprimeiros[3] = new
		 * entre(6, 7); cincoprimeiros[4] = new entre(7, 8);
		 */
		func f1 = new func(1);

		Exibir exibir = new Exibir();
		Metodos metodos1 = new Metodos(f1, exibir);
		metodos1.setMax_interacao(1000);
		System.out.println("iniciando");
		for (entre e : cincoprimeiros) {
			exibir.newline();
			exibir.setText("Intervalo " + e.a + " a " + e.b);
			exibir.newline();
			doRes(metodos1.bissecao(e.a, e.b), f1);
			doRes(metodos1.falsa_posicao(e.a, e.b), f1);
			doRes(metodos1.ponto_fixo(e.a), f1);
			doRes(metodos1.nweton_raphson(e.a), f1);
			doRes(metodos1.secante(e.a, e.b), f1);
		}

		File fp = new File("resp.txt");
		try {
			FileWriter fw = new FileWriter(fp);
			fw.write(exibir.getText());
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("done");
	}

	private static void doRes(Resposta r, Funcao f) {
		String text;
		if (r == null) {
			text = "nao convergiu";
		} else {
			text = r.getI() + " & " + r.getX() + " & " + f.f(r.getX()) + " & " + r.getEa();
		}
		System.out.println(text);
	}

}

class entre {
	public double a, b;

	public entre(double a, double b) {
		this.a = a;
		this.b = b;
	}
}

class func extends Funcao {
	private double a;

	public func(double a) {
		this.a = a;
	}

	@Override
	public double f(double x) {
		return (x / a) * Math.tan(a * x);
	}

	@Override
	public double fl(double x) {
		return (a * x * Math.pow(secante(a * x), 2) + Math.tan(a * x)) / a;
	}

	@Override
	public double g(double x) {
		return Math.tan(a * x) / a;
	}

	private double secante(double x) {
		return 1 / Math.cos(x);
	}
}