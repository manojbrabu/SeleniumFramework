package OtherJavaTests;

public class Recursive {
    public static void main(String[] a){

        System.out.println(factorial(5));
        System.out.println(fib(6));
    }
    //Factorial
    public static int factorial(int num){

        if(num==0 || num ==1){
            return 1;
        }
        else{
            System.out.println(num);
            return num * factorial(num-1);
        }
    }
    //Fifonasi
    public static int fib(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

}

//5 = 0,1,2

