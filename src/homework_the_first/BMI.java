package homework_the_first;

public class BMI implements Comparable<BMI> {
	private double w;
	private double h;
	private double index = 0.0;

	public BMI() {
		super();
	}

	public BMI(double h, double w) {
		super();
		setData(h, w);
	}

	public boolean setData(double h, double w) {
		if (!checkData(h, w)) {
			return false;
		} else {
			this.h = h;
			this.w = w;
			calculate();
			return true;
		}
	}

	private boolean checkData(double h, double w) {
		if (h < 0 || w < 0 || h > 2.5 || w > 450) {
			return false;
		} else {
			return true;
		}
	}

	private void calculate() {

		if (!checkData(h, w))
			index = 0.0;

		index = w / (h * h);
		index = Math.round(index * 100) / 100;
	}

	public double getIndex() {
		return index;
	}

	public String getStatus() {

		if (index == 0)
			return "status nieznany";
		if (index < 16) {
			return "wyg³odzenie";
		} else if (index < 17) {
			return "wychudzenie";
		} else if (index < 18.5) {
			return "niedowaga";
		} else if (index < 25) {
			return "prawid³owo";
		} else if (index < 30) {
			return "nadwaga";
		} else if (index < 35) {
			return "I stopieñ oty³oœci";
		} else if (index < 40) {
			return "II stopieñ oty³oœci";
		} else {
			return "III stopieñ oty³oœci";
		}
	}

	public String getAdvice() {
		if (index == 0)
			return "rzucam wyj¹tkowo";
		if (index < 18.5) {
			return "Nale¿y wiêcej jeœæ.";
		} else if (index < 25) {
			return "Nale¿y mniej myœleæ o ciele.";
		} else {
			return "Mniej ¿reæ.";
		}
	}

	public String getComplexMessage() {
		String msg;

		if (index == 0)
			return "";

		msg = "Twój indeks BMI wynosi " + String.format("%.02f", index) + ". Daje Ci to status: " + getStatus()
				+ ".\nW zwi¹zku z tym zalecenie pokontrolne brzmi:\n" + getAdvice();

		return msg;
	}

	@Override
	public String toString() {
		return "BMI [w=" + w + ", h=" + h + ", index=" + index + "]";
	}

	public int compareTo(BMI other) {
		return Double.compare(index, other.index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(h);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(index);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(w);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BMI other = (BMI) obj;
		if (Double.doubleToLongBits(h) != Double.doubleToLongBits(other.h))
			return false;
		if (Double.doubleToLongBits(index) != Double.doubleToLongBits(other.index))
			return false;
		if (Double.doubleToLongBits(w) != Double.doubleToLongBits(other.w))
			return false;
		return true;
	}

}
