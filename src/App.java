import java.util.HashSet;



public class App {
    public static void main(String[] args) throws Exception {
        int[][] board = {
            {0, 0, 0, 0, 0, 0, 0, 1, 2},
            {0, 0, 0, 0, 3, 5, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 6, 0, 0},
        
            {0, 1, 0, 0, 0, 0, 0, 7, 0},
            {5, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 9, 0, 0, 0, 0, 0, 2, 0},
        
            {0, 0, 7, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 4, 7, 0, 0, 0, 0},
            {4, 2, 0, 0, 0, 0, 0, 0, 0}
        };
        
        System.out.println("incomplete board: ");
        printBoard(board);
        if(isValidSudokuBoard(board))
            solve(board);
        else{
            System.out.println("this sudoku board is invalid");
            return;
        }
            
        System.out.println("complete board: ");
        printBoard(board);
        
        
        
        
        

    }

    public static boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    int[] possibleValues = PossibleEntries(board, row, col);
                    for (int num : possibleValues) {
                        board[row][col] = num;
    
                        if (solve(board)) {
                            return true;
                        }
    
                        board[row][col] = 0; // Backtrack
                    }
    
                    return false; // No valid number worked, backtrack
                }
            }
        }
    
        return true; // Solved
    }

    

    public static int[] PossibleEntries(int[][] board, int row, int col){
        int box = GetBox(row, col);

        HashSet<Integer> possibleNums = new HashSet<Integer>(9);
        for(int i = 1; i <= 9; i++)
            possibleNums.add(i);

        HashSet<Integer> fullSet = new HashSet<Integer>();
        fullSet.addAll(ArrayToHashSet(BoxToArray(board, box)));
        fullSet.addAll(ArrayToHashSet(RowToArray(board, row)));
        fullSet.addAll(ArrayToHashSet(ColumnToArray(board, col)));

        

        possibleNums.removeAll(fullSet);

        int[] possibilities = new int[possibleNums.size()];

        int i = 0;
        for (Integer val : possibleNums) {
            possibilities[i++] = val;
        }

        return possibilities;


    }

    public static int[] RowToArray(int[][] board, int row){
        int[] newArray = new int[9];
        //HashSet<Integer> tempSet = new HashSet<Integer>(9);
        for(int i = 0; i < 9; i++){
            newArray[i] = board[row][i];
                
        }


        return newArray;
    }

    public static int[] ColumnToArray(int[][] board, int col){
        int[] newArray = new int[9];
        for(int i = 0; i < 9; i++){
            newArray[i] = board[i][col];
                
        }


        return newArray;
    }

    public static int[] BoxToArray(int[][] board, int box){
        int[] newArray = new int[9];
        for(int i = 0; i < 9; i++){
            newArray[i] = board[i/3 + (box / 3)*3][i%3 + (box % 3)*3];
            
        }
        


        return newArray;
    }

    public static HashSet<Integer> ArrayToHashSet(int[] arr){
        HashSet<Integer> newSet = new HashSet<>();
        for(int i = 0; i < 9; i++){
            if(arr[i] != 0){
                newSet.add(arr[i]);
            }
        }

        return newSet;
    }


    public static int GetBox(int row, int col){
        return (row / 3) * 3 + (col / 3);
    }

    private static void printBoard(int[][] board){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static boolean isValidSudokuBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isValidGroup(RowToArray(board, i))) return false;
            if (!isValidGroup(ColumnToArray(board, i))) return false;
            if (!isValidGroup(BoxToArray(board, i))) return false;
        }
        return true;
    }

    public static boolean isValidGroup(int[] group) {
        boolean[] seen = new boolean[9]; // index 0 represents number 1, index 8 is number 9
    
        for (int num : group) {
            if (num == 0) continue; // skip empty cells
            if (num < 1 || num > 9) return false;
            if (seen[num - 1]) return false; // duplicate detected
            seen[num - 1] = true;
        }
    
        return true;
    }
    
    
    
    
}
