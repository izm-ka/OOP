package ru.nsu.izmailova.polynom;

import java.util.Arrays;

public class Polynomial {
    private int[] coefficients;

    public Polynomial(int[] coefficients) {
        if (coefficients.length == 0) {
            this.coefficients = new int[]{0};
        } else {
            this.coefficients = coefficients;
        }
    }

    public int evaluate(int x) {
        int result = 0;
        int power = coefficients.length - 1;

        for (int coefficient : coefficients) {
            result += coefficient * (int) Math.pow(x, power);
            power--;
        }

        return result;
    }

    public int length() {
        return this.coefficients.length;
    }

    public int[] getCoefficients() {
        return coefficients;
    }

    public Polynomial add(Polynomial other) {
        int[] newCoeffs;

        if (this.length() < other.length()) {
            newCoeffs = other.coefficients;
            for (int i = 0; i < this.length(); i++) {
                newCoeffs[i] += this.coefficients[i];
            }
        } else {
            newCoeffs = this.coefficients;
            for (int i = 0; i < other.length(); i++) {
                newCoeffs[i] += other.coefficients[i];
            }
        }
        return new Polynomial(newCoeffs);
    }

    public Polynomial subtract(Polynomial other) {
        int[] negativeCoeffs = new int[other.length()];
        for (int i = 0; i < other.length(); i++) {
            negativeCoeffs[i] = other.coefficients[i] * -1;
        }
        return this.add(new Polynomial(negativeCoeffs));
    }

    public Polynomial multiply(Polynomial other) {
        int newSize = this.length() + other.length() - 1;
        int[] newCoeffs = new int[newSize];
        for (int i = 0; i < other.length(); i++) {
            for (int j = 0; j < this.length(); j++) {
                newCoeffs[i + j] += (other.coefficients[i] * this.coefficients[j]);
            }
        }
        return new Polynomial(newCoeffs);
    }

    public  Polynomial differentiate(int order) {
        if (order <= 0) {
            return this;
        }

        if (this.length() - order <= 0) {
            return new Polynomial(new int[]{0});
        }

        int[] differentiateCoeffs = new int[coefficients.length - 1];

        for (int i = 0; i < differentiateCoeffs.length; i++) {
            differentiateCoeffs[i] = coefficients[i] * (coefficients.length - 1 - i);
        }

        Polynomial differentiatePolynomial = new Polynomial(differentiateCoeffs);

        return differentiatePolynomial.differentiate(order - 1);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Polynomial result = (Polynomial) other;
        return Arrays.equals(coefficients, result.coefficients);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coefficients);
    }

    @Override
    public String toString() {
        int power = this.length();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.length(); i++) {
            if (this.coefficients[i] != 0) {
                if (power != this.length()) {
                    if (this.coefficients[i] > 0) {
                        result.append(" + ");
                    } else {
                        result.append(" - ");
                    }
                }
                result.append(Math.abs(this.coefficients[i]));
                power--;
                if (power > 1) {
                    result.append("x^");
                    result.append(power);
                } else if (power == 1) {
                    result.append("x");
                }
            } else {
                power--;
            }
        }
        return result.toString();
    }
}