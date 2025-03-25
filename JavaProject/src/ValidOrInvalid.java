import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ValidOrInvalid {
    public static void main(String [] args){
       List<Integer> list= Arrays.asList(12,2,15,18,98,70);
       int SecMax= Integer.MIN_VALUE;
       int max=  Integer.MIN_VALUE;
        for(int i=1; i<=list.size()-1;i++){
             if(list.get(i) > max){
                SecMax = max;
                max = list.get(i);
            }else  if( list.get(i) > SecMax && list.get(i) !=max){
                 SecMax = list.get(i);
          }
        }
        System.out.println(SecMax);
    }
}
