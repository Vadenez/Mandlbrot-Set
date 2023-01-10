public class ComplexNumber {

    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber times(ComplexNumber number){

        double a = this.real*number.real;
        double b = this.imaginary*number.real;
        double c = this.real*number.imaginary;
        double d = this.imaginary*number.imaginary*-1;

        double newReal = a+d;
        double newImaginary = b+c;


        ComplexNumber newComplexNumber = new ComplexNumber(newReal, newImaginary);
        return newComplexNumber;
    }


    public ComplexNumber add(ComplexNumber number){

        double newReal = this.real+number.real;
        double newImaginary = this.imaginary+number.imaginary;

        return new ComplexNumber(newReal, newImaginary);

    }

    public double magnitude(){
        double newReal = this.getReal();
        double newImaginary = this.getImaginary();

        return Math.sqrt(newReal*newReal + newImaginary*newImaginary);
    }

    public double getReal() {
        return this.real;
    }

    public double getImaginary() {
        return this.imaginary;
    }
}