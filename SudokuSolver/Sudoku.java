
public class Sudoku {
    private static Node[][] puzzle;
    int counter = 0;

    public Sudoku(Node[][] grid) {
        puzzle = grid;

    }

    public boolean solvePuzzle() {
        counter += 1;
        if (findEmpty() == null) {
            return true;
        }
        Node empty = findEmpty();
        for (int num = 1; num < 10; num++) {
            if (isSafe(empty, num)) {
                empty.val = num;
                if (solvePuzzle()) {
                    return true;
                } else {
                    empty.val = 0;
                }
            }
        }
        return false;
    }



    private boolean isSafe(Node n, int v) {
        return checkRow(n, v) && checkCol(n, v) && checkSquare(n, v);
    }

    private Node findEmpty() {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j ++) {
                Node temp = puzzle[i][j];
                if (temp.val == 0) {
                    return temp;
                }
            }
        }
        return null;
    }

    private boolean checkRow(Node n, int v) {
        for (int i = 0; i < puzzle[0].length; i ++) {
            if (puzzle[i][n.col].val == v) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol(Node n, int v) {
        for (int i = 0; i < puzzle.length; i ++) {
            if (puzzle[n.row][i].val == v) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSquare(Node n, int v) {
        int row = (n.row / 3) * 3;
        int col = (n.col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[row + i][col + j].val == v) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printPuzzle() {
        for (int i = 0; i < puzzle.length; i ++) {
            for (int j = 0; j < puzzle[0].length; j ++) {
                int value = puzzle[i][j].val;
                if (value == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(value + " ");
                }

            }

            System.out.println("");
        }
    }



    static class Node {
        public int row;
        public int col;
        public int val;


        public Node(int r, int c, int v) {
            row = r;
            col = c;
            val = v;
        }

    }

    public static void main(String[] args) {

        int[] easy = new int[] {7, 9, 0, 0, 0, 0, 3, 0, 0,
                                0, 0, 0, 0, 0, 6, 9, 0, 0,
                                8, 0, 0, 0, 3, 0, 0, 7, 6,
                                0, 0, 0, 0, 0, 5, 0, 0, 2,
                                0, 0, 5, 4, 1, 8, 7, 0, 0,
                                4, 0, 0, 7, 0, 0, 0, 0, 0,
                                6, 1, 0, 0, 9, 0, 0, 0, 8,
                                0, 0, 2, 3, 0, 0, 0, 0, 0,
                                0, 0, 9, 0, 0, 0, 0, 5, 4};
        int[] hard = new int[] {2, 0, 0, 3, 0, 0, 0, 0, 0,
                                8, 0, 4, 0, 6, 2, 0, 0, 3,
                                0, 1, 3, 8, 0, 0, 2, 0, 0,
                                0, 0, 0, 0, 2, 0, 3, 9, 0,
                                5, 0, 7, 0, 0, 0, 6, 2, 1,
                                0, 3, 2, 0, 0, 6, 0, 0, 0,
                                0, 2, 0, 0, 0, 9, 1, 4, 0,
                                6, 0, 1, 2, 5, 0, 8, 0, 9,
                                0, 0, 0, 0, 0, 1, 0, 0, 2};
        int[] extreme = new int[] {0, 2, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 6, 0, 0, 0, 0, 3,
                0, 7, 4, 0, 8, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 3, 0, 0, 2,
                0, 8, 0, 0, 4, 0, 0, 1, 0,
                6, 0, 0, 5, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 0, 7, 8, 0,
                5, 0, 0, 0, 0, 9, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 4, 0};
        Node[][] tester = new Node[9][9];
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Node temp = new Node(i, j, extreme [count]);
                tester[i][j] = temp;
                count += 1;
            }
        }
        Sudoku sudoku = new Sudoku(tester);
        sudoku.printPuzzle();
        sudoku.solvePuzzle();
        sudoku.printPuzzle();
        System.out.println(sudoku.counter);


    }
}
