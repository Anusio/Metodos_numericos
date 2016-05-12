/**
 * @author anusio
 */
package metodos;

public class Metodos {
	private Funcao funcao;
	private Exibir exibir;
	private double tol = .0000001;
	private int max_interacao = 100;

	public Metodos(Funcao fun) {
		funcao = fun;
	}

	public Metodos(Funcao fun, Exibir e) {
		funcao = fun;
		exibir = e;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public void setExibir(Exibir exibir) {
		this.exibir = exibir;
	}

	public void setTol(double tol) {
		this.tol = tol;
	}

	public void setMax_interacao(int max_interacao) {
		this.max_interacao = max_interacao;
	}

	private void putInfo(String text) {
		if (exibir != null)
			exibir.setText(text);
	}

	private void new_line() {
		if (exibir != null)
			exibir.newline();
	}

	public double bissecao(double a, double b) {
		double x0, x1, ea;
		//extra dany
		double fc;
		x1 = a;
		new_line();
		new_line();
		putInfo("Metodo Bisecção:");
		for (int i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = (a + b) / 2;
			ea = Math.abs((x1 - x0) / x1) * 100;
			fc = funcao.f(x1);
			//extra tbm
			if (funcao.f(a) * fc < 0) {
				b = x1;
			} else if(funcao.f(b) * fc < 0){
				a = x1;
			}
			new_line();
			putInfo("interação " + i);
			putInfo(" a= " + a);
			putInfo(" b= " + b);
			putInfo(" x1= " + x1);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));

			if (ea <= tol || Math.abs(funcao.f(x1))< tol) {
				break;
			}
		}
		return x1;
	}

	public double falsa_posicao(double a, double b) {
		double x0, x1, fl, fu, fx, ea;
		ea = 100;
		fl = funcao.f(a);
		fu = funcao.f(b);
		if (fl < fu) {
			x1 = a;
		} else {
			x1 = b;
		}
		new_line();
		new_line();
		putInfo("Falsa posição:");
		for (int i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = b + (fu * (a - b)) / (fu - fl);
			fx = funcao.f(x1);
			if (x1 != 0) {
				ea = Math.abs((x1 - x0) / x1) * 100;
			}
			if (fl < fu) {
				a = x1;
				fl = fx;
			} else {
				b = x1;
				fu = fx;
			}
			new_line();
			putInfo("interação " + i);
			putInfo(" a= " + a);
			putInfo(" b= " + b);
			putInfo(" x1= " + x1);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));
			if (ea <= tol || Math.abs(funcao.f(x1))< tol) {
				break;
			}
		}

		return x1;
	}

	public double ponto_fixo(double xa) {
		double x1, x0, ea;
		x1 = xa;
		ea = 100;
		new_line();
		new_line();
		putInfo("Ponto fixo");
		for (int i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = funcao.g(x0);
			if (x1 != 0) {
				ea = Math.abs((x1 - x0) / x1) * 100;
			}

			new_line();
			putInfo("interação " + i);
			putInfo(" x0= " + x0);
			putInfo(" x1= " + x1);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));
			//quase certeza que ea <= tol ou while(ea>tol), da no mesmo, nao funciona
			if (ea <= tol || Math.abs(funcao.f(x1))< tol) {
				break;
			}
		}
		return x1;
	}

	public double nweton_raphson(double xa) {
		double ea = 100;
		double x0, x1;
		x1 = xa;
		new_line();
		new_line();
		putInfo("Nweton raphson");
		for (int i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = x0 - funcao.f(x0) / funcao.fl(x0);
			if (x1 != 0) {
				ea = Math.abs((x1 - x0) / x1) * 100;
			}

			new_line();
			putInfo("interação " + i);
			putInfo(" x0= " + x0);
			putInfo(" x1= " + x1);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));
			if (ea <= tol || Math.abs(funcao.f(x1))< tol) {
				break;
			}
		}
		return x1;
	}

	public double secante(double xa, double xb) {
		double x1, x0, fa, fb;
		double ea = 100;
		x1 = xa;
		new_line();
		new_line();
		putInfo("Secante");
		for (int i = 0; i < max_interacao; i++) {
			fa = funcao.f(xa);
			fb = funcao.f(xb);
			//nao tem x0
			x0 = x1;
			//rescrever a funcao funcionou
			x1 = xb - (fb*(xb-xa)/(fb-fa));
			if(x1 != 0){
				ea = Math.abs((x1-x0)/x1)*100;
			}
			
			new_line();
			putInfo("interação " + i);
			putInfo(" x0= " + x0);
			putInfo(" x1= " + x1);
			putInfo(" xa= " + xa);
			putInfo(" xb= " + xb);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));
			if(ea <= tol || Math.abs(funcao.f(x1))< tol){
				break;
			}
			xb = xa;
			xa = x1;
		}
		return x1;
	}

}
