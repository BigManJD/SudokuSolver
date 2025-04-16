package objs;

public class cell {
    boolean solved = false;
    int number = 0;
    int[] possibilities;

    public cell(int num, int[] poss){
        number = num;
        if(number == 0){
            solved = false;
        }else{
            solved = true;
        }
        possibilities = poss;
    }

}
