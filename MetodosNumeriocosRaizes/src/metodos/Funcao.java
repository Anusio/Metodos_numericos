/**
 * @author anusio
 */
package metodos;

public abstract class Funcao {
	/**
	 * a funcao de x f(x)
	 * @param x
	 * @return
	 */
	public abstract double f(double x);
	/**
	 * a derivada primeira de f(x)
	 * @param x
	 * @return
	 */
	public abstract double fl(double x);
	/**
	 * uma funcao de x f(x) = g(x) onde a solucao de g(x) é tambem a solucao de f(x)
	 * @param x
	 * @return
	 */
	public abstract double g(double x);
}
