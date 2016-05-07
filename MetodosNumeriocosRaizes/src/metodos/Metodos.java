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

	public double bissecao(double a, double b) {
		double x0,x1,ea;
		x1 = a; 
		putInfo("Metodo Bisecção:");
		for (int i = 0; i < max_interacao ; i++) {
			x0 = x1;
			x1 = (a+b)/2;
			ea = Math.abs((x1 - x0)/x1)*100;
			if(funcao.f(a)*funcao.f(b) < 0){
				b = x1;
			}else{
				a = x1;
			}
			if(ea > tol){
				break;
			}
		}
		return x1;
	}

	public void falsa_posicao() {

	}

	public void ponto_fixo() {

	}

	public void nweton_raphson() {

	}

	public void secante() {

	}

}
