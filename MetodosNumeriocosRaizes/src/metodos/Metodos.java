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
		x1 = a;
		putInfo("Metodo Bisec��o:");
		for (int i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = (a + b) / 2;
			ea = Math.abs((x1 - x0) / x1) * 100;
			if (funcao.f(a) * funcao.f(b) < 0) {
				b = x1;
			} else {
				a = x1;
			}
			new_line();
			putInfo("intera��o " + i);
			putInfo(" a= " + a);
			putInfo(" b= " + b);
			putInfo(" x1= " + x1);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));

			if (ea < tol) {
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
		putInfo("Falsa posi��o:");
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
			putInfo("intera��o " + i);
			putInfo(" a= " + a);
			putInfo(" b= " + b);
			putInfo(" x1= " + x1);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));
			if (ea < tol) {
				break;
			}
		}

		return x1;
	}

	public double ponto_fixo(double xa) {
		double x1, x0, ea;
		x1 = xa;
		ea = 100;
		putInfo("Ponto fixo");
		for (int i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = funcao.g(x0);
			if (x1 != 0) {
				ea = Math.abs((x1 - x0) / x1) * 100;
			}

			new_line();
			putInfo("intera��o " + i);
			putInfo(" x0= " + x0);
			putInfo(" x1= " + x1);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));
			if (ea < tol) {
				break;
			}
		}
		return x1;
	}

	public double nweton_raphson(double xa) {
		double ea = 100;
		double x0, x1;
		x1 = xa;
		putInfo("Nweton raphson");
		for (int i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = x0 - funcao.f(x0) / funcao.fl(x0);
			if (x1 != 0) {
				ea = Math.abs((x1 - x0) / x1) * 100;
			}

			new_line();
			putInfo("intera��o " + i);
			putInfo(" x0= " + x0);
			putInfo(" x1= " + x1);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));
			if (ea < tol) {
				break;
			}
		}
		return x1;
	}

	public double secante(double xa, double xb) {
		double x1, x0, f0, f1;
		double ea = 100;
		x1 = xa;
		putInfo("Secante");
		for (int i = 0; i < max_interacao; i++) {
			f0 = funcao.f(xa);
			f1 = funcao.f(xb);
			x0 = x1;
			x1 = xa - f1*(xa-xb)/(f0-f1);
			if(x1 != 0){
				ea = Math.abs((x1-x0)/x1)*100;
			}
			xa = xb;
			xb = x1;
			
			new_line();
			putInfo("intera��o " + i);
			putInfo(" x0= " + x0);
			putInfo(" x1= " + x1);
			putInfo(" xa= " + xa);
			putInfo(" xb= " + xb);
			putInfo(" ea " + ea);
			putInfo(" f(x1)= " + funcao.f(x1));
			if(ea <= tol){
				break;
			}
		}
		return x1;
	}

}
