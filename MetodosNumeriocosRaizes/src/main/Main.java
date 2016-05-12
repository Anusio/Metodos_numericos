/**
 * @author anusio
 */
package main;

import metodos.Exibir;
import metodos.Funcao;
import metodos.Metodos;

public class Main {

	public static void main(String[] args) {
		Funcao f = new Funcao() {
			
			@Override
			public double g(double x) {
				//escolha outra
				return 1/((x) - 10/x);
			}
						
			@Override
			public double fl(double x) {
				return 2*x;
			}
			
			@Override
			public double f(double x) {
				return (x*x)-10;
			}
		};
		
		Exibir exibir = new Exibir();
		Metodos metodos = new Metodos(f,exibir);
		System.out.println("iniciando");
		metodos.bissecao(-2, 10);
		metodos.falsa_posicao(-2, 10);
		metodos.ponto_fixo(2);
		metodos.nweton_raphson(3);
		metodos.secante(-2,10);
		
		
		System.out.println(exibir.getText());
	}

}
