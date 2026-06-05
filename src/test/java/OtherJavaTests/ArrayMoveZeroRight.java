package OtherJavaTests;

import java.util.Arrays;

public class ArrayMoveZeroRight
{
    public static void main(String[] a){
        int num[] = {2,0,0,0,4,8,0,5};
        int j=0;

        //Move Sero to Right
        /*for(int i = 0;i<num.length;i++){
            if(num[i]!=0){
                int temp = num[i];
                num[i]=num[j];
                num[j]=temp;
                j++;
            }
        }*/

        //Move Zero to Left
        /*j = num.length-1;
        for(int i =num.length-1;i>=0;i--){
            if(num[i]!=0){
                int temp = num[i];
                num[i]=num[j];
                num[j]=temp;
                j--;
            }
        }*/

        //Sorting
        /*for(int i = 0;i<num.length-1;i++){
            for(j=i+1;j<num.length;j++){
                if(num[i]<num[j]){
                    int temp = num[i];
                    num[i]=num[j];
                    num[j]=temp;
                }
            }

        }*/

        //Sum of Array
        int sum = 0;
        for(int i =0;i<num.length;i++){
            sum += num[i];
        }
        System.out.println(sum);

        //Find Second Largest Number
        int firstLarge = 0;
        int secondLarge =0;
        for(int i =0; i<num.length;i++){
            if(num[i]<num[i+1]){
                firstLarge = num[j];
                secondLarge = num[i];
            }
        }

        Arrays.stream(num).forEach(System.out::print);
    }
}
