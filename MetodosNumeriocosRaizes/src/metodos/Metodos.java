/**
 * @author anusio
 */
package metodos;

import java.text.DecimalFormat;

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

	private void putInfo(double a) {
		DecimalFormat df = new DecimalFormat("    00.00000000");
		if (exibir != null)
			exibir.setText(df.format(a) + "|");
	}

	private void putInfo(int a) {
		if (exibir != null)
			exibir.setText(String.format("%8d |", a));
	}

	private void new_line() {
		if (exibir != null)
			exibir.newline();
	}

	public Resposta bissecao(double a, double b) {

		double x0, x1, ea;
		ea = 100;
		// extra dany
		double fc;
		x1 = a;
		new_line();
		new_line();
		putInfo("Metodo Bisecção:");
		new_line();
		if (funcao.f(a) * funcao.f(b) > 0) {
			putInfo("Nao coverge, intervalo mal definido");
			return  null;
		}
		int i;
		putInfo("interação|      a       |      b       |      x1      |      ea     |     f(x1)   |");
		for (i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = (a + b) / 2;
			ea = Math.abs((x1 - x0) / x1) * 100;
			fc = funcao.f(x1);
			// extra tbm
			if (funcao.f(a) * fc < 0) {
				b = x1;
			} else if (funcao.f(b) * fc < 0) {
				a = x1;
			} else {
				putInfo("Erro Nao coverge a=" + a + " b=" + b);
				return  null;
			}
			new_line();
			putInfo(i);
			putInfo(a);
			putInfo(b);
			putInfo(x1);
			putInfo(ea);
			putInfo(funcao.f(x1));

			if (Math.abs(funcao.f(x1)) < tol) {
				break;
			}
		}
		return new Resposta(x1, ea, i);
	}

	public Resposta falsa_posicao(double a, double b) {
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
		new_line();
		if (funcao.f(a) * funcao.f(b) > 0) {
			putInfo("Nao coverge, intervalo mal definido");
			return  null;
		}
		int i;
		putInfo("interação|      a       |      b       |      x1      |      ea     |     f(x1)   |");
		for (i = 0; i < max_interacao; i++) {
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
			putInfo(i);
			putInfo(a);
			putInfo(b);
			putInfo(x1);
			putInfo(ea);
			putInfo(funcao.f(x1));
			if (Math.abs(funcao.f(x1)) < tol) {
				break;
			}
		}

		return new Resposta(x1, ea, i);
	}

	public Resposta ponto_fixo(double xa) {
		double x1, x0, ea;
		int i;
		x1 = xa;
		ea = 100;
		new_line();
		new_line();
		putInfo("Ponto fixo");
		new_line();
		putInfo("interação|      x0      |      x1      |      ea      |      f(x1)   |");
		for (i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = funcao.g(x0);
			if (x1 != 0) {
				ea = Math.abs((x1 - x0) / x1) * 100;
			}

			new_line();
			putInfo(i);
			putInfo(x0);
			putInfo(x1);
			putInfo(ea);
			putInfo(funcao.f(x1));
			// quase certeza que ea <= tol ou while(ea>tol), da no mesmo, nao
			// funciona
			if (Math.abs(funcao.f(x1)) < tol) {
				break;
			}
		}
		return new Resposta(x1, ea, i);
	}

	public Resposta nweton_raphson(double xa) {
		double ea = 100;
		double x0, x1;
		x1 = xa;
		new_line();
		new_line();
		putInfo("Nweton raphson");
		new_line();
		int i;
		putInfo("interação|      x0      |      x1      |      ea      |      f(x1)   |");
		for (i = 0; i < max_interacao; i++) {
			x0 = x1;
			x1 = x0 - funcao.f(x0) / funcao.fl(x0);
			if (x1 != 0) {
				ea = Math.abs((x1 - x0) / x1) * 100;
			}

			new_line();
			putInfo(i);
			putInfo(x0);
			putInfo(x1);
			putInfo(ea);
			putInfo(funcao.f(x1));
			if (Math.abs(funcao.f(x1)) < tol) {
				break;
			}
		}
		return new Resposta(x1, ea, i);
	}

	public Resposta secante(double xa, double xb) {
		double x1, x0, fa, fb;
		double ea = 100;
		x1 = xa;
		new_line();
		new_line();
		putInfo("Secante");
		new_line();
		if (funcao.f(xa) * funcao.f(xb) > 0) {
			putInfo("Nao coverge, intervalo mal definido");
			return  null;
		}
		int i;
		putInfo("interação|      x0      |      x1      |      xa      |      xb      |      ea      |      f(x1)   |");
		for (i = 0; i < max_interacao; i++) {
			fa = funcao.f(xa);
			fb = funcao.f(xb);
			// nao tem x0
			x0 = x1;
			// rescrever a funcao funcionou
			x1 = xb - (fb * (xb - xa) / (fb - fa));
			if (x1 != 0) {
				ea = Math.abs((x1 - x0) / x1) * 100;
			}

			new_line();
			putInfo(i);
			putInfo(x0);
			putInfo(x1);
			putInfo(xa);
			putInfo(xb);
			putInfo(ea);
			putInfo(funcao.f(x1));
			if (Math.abs(funcao.f(x1)) < tol) {
				break;
			}
			xb = xa;
			xa = x1;
		}
		return new Resposta(x1, ea, i);
	}

}
