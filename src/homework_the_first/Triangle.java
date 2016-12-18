package homework_the_first;

public class Triangle {
	private double a, b, c;
	private double p = 0;
	private double area = 0;

	public Triangle() {
		super();
	}

	public Triangle(double a, double b, double c) {
		if (setSides(a, b, c)) {
			calculate();
		}
	}

	public double getArea() {
		return area;
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getC() {
		return c;
	}

	private void calculate() {
		if (checkSides(a, b, c)) {
			p = (a + b + c) / 2;
			area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
		}
	}

	public boolean setSides(double a, double b, double c) {
		if (!checkSides(a, b, c))
			return false;
		this.a = a;
		this.b = b;
		this.c = c;
		calculate();
		return true;
	}

	private boolean checkSides(double a, double b, double c) {
		if (a > b + c || b > a + c || c > b + a)
			return false;
		return true;
	}

}
