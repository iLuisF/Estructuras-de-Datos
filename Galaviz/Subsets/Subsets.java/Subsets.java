
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Armando Ballinas
 * @version 2.0
 */
public class Subsets<E> {
 
    private ArrayList<E> list;
    
    /**
    *Método que genera los subconjuntos a partir de un arreglo de enteros.
    *@param array arreglo de enteros de los cuales se quiere obtener su conjunto potencia
    *@return Un arreglo de arreglos de enteros (Integer) que contiene el conjunto potencia del arreglo original.
    */
    public static Integer[][] powerSet(int[] array){

     ArrayList<Integer> list = new ArrayList<>();
        for (int x : array)
            list.add(x);
        Set<Integer> set = new HashSet<Integer>(list);
        Set<Set<Integer>> s = Subsets.InterPowerSet(set);
        ArrayList<Integer[]> subs = new ArrayList<>();
        int[] ar;
        for(Set<Integer> sub:s){
            subs.add(sub.toArray(new Integer[0]));
        } 
        Integer[][] res = subs.toArray(new Integer[0][0]);
        return res;
    }

    /**
    *Método interno que calcula el conjunto potencia de un conjunto de enteros.
    */
    private static Set<Set<Integer>> InterPowerSet(Set<Integer> originalSet) {
        Set<Set<Integer>> sets = new HashSet<Set<Integer>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<Integer>());
            return sets;
        }
        List<Integer> list = new ArrayList<Integer>(originalSet);
        Integer head = list.get(0);
        Set<Integer> rest = new HashSet<Integer>(list.subList(1, list.size()));
        for (Set<Integer> set : InterPowerSet(rest)) {
            Set<Integer> newSet = new HashSet<Integer>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
    

    public static void main(String[] args){
        int num = new Integer(args[0]).intValue();
        int [] array = new int[num];
        for(int i = 0;i<num;i++){
            array[i] = i;
        }
        Integer[][] subsets = Subsets.powerSet(array);
        System.out.println("[");
        for(int i = 0 ; i <subsets.length; i++){
            Integer[] x = subsets[i];
            System.out.print("[");
            for(int j =0 ; j< x.length; j++){
                System.out.print(x[j]+",");
            }
            System.out.print("]\n");
        }
        System.out.println("]");
    }
}
