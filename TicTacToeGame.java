import java.awt.*;
import java.util.Scanner;
import java.util.Random;

public class TicTacToeGame {
    public static void main(String[] args) {

        String[][] board = new String[3][3];

        for (int d = 0; d < board.length; d++) {
            for (int f = 0; f < board.length; f++) {
                if (board[f][d] == null) {
                    board[f][d] = ".";
                }
            }

        }
        Scanner scanner1 = new Scanner(System.in);

        int computerNumber=-1;
        while(computerNumber<0 || computerNumber>2){
            System.out.println("Against how many computers do you want to play?");
            computerNumber = scanner1.nextInt();
        }

        int gameNumber = 0;

        boolean correctInput = false;

        while(!boardFilled(board) && !hasThreeConnected(board)) {

            int playerChoise = 0;
            System.out.println(" ");
            showBoard(board);
            gameNumber += 1;

            if (computerNumber == 2) {
                while (!correctInput) {
                    Point coords= getComputerMove();
                    if (isMovePlayed(coords, board)) {
                        correctInput = false;
                    } else {
                        correctInput = true;
                        board = performMove(coords, gameNumber, board);
                    }
                }
            }
            else if ( computerNumber == 1 && gameNumber % 2 == 0) {
                while (!correctInput) {
                    Point coords1= getComputerMove();
                    if (isMovePlayed(coords1, board)) {
                        correctInput = false;
                    } else {
                        correctInput = true;
                        board = performMove(coords1, gameNumber, board);
                    }
                }
            }
            else {
                while (!correctInput) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("What is your number?");
                    playerChoise = scanner.nextInt();

                    if (playerChoise > 9 || playerChoise < 1) {
                        correctInput = false;
                    } else if (isMovePlayed(getPlayerMove(playerChoise), board)) {
                        correctInput = false;
                    } else {
                        correctInput = true;
                        board = performMove(getPlayerMove(playerChoise), gameNumber, board);
                    }
                }
            }
            correctInput = false;

        }
        System.out.println(" ");
        showBoard(board);
        System.out.println(" ");

        if(gameNumber%2==0 && hasThreeConnected(board)){
            System.out.println("O has won");
        }
        else if (gameNumber%2!=0 && hasThreeConnected(board)) {
            System.out.println("X has won");
        }
        else{
            System.out.println("it is a tie");
        }


    }
    public static void showBoard(String[][] board){
        for(int i=0; i< board.length; i++){
            for(int e=0; e< board.length; e++){
                System.out.print(board[i][e]);

            }
            System.out.println(" ");
        }
    }

    public static Point getPlayerMove(int playerChoise){

        return new Point((playerChoise-1)/3,(playerChoise-1)%3);

    }

    public static Point getComputerMove(){
        int y=0;
        int x=0;

        Random random = new Random();
        int computerMove = random.nextInt(9);


        if(computerMove<3){
            x=computerMove;
        }
        else if (computerMove<6) {
            x=computerMove-3;
            y=1;
        }
        else if (computerMove<9) {
            x=computerMove-6;
            y=2;
        }

        return new Point(y,x);
    }

    public static boolean isMovePlayed(Point coords, String[][] board){
        return board[coords.x][coords.y]!=".";
    }

    public static String[][] performMove(Point coords, int playernumber, String[][] board){

        if(playernumber%2==0){
            board[coords.x][coords.y]="O";
        }
        else{
            board[coords.x][coords.y] = "X";

        }

        return board;
    }

    public static boolean boardFilled(String[][] board){
        for(int q=0; q<3;q++){
            for(int w=0; w<3;w++){
                if(board[q][w]=="."){
                    return false;
                }
            }
        }

        return true;

    }

    public static boolean hasThreeConnected(String[][] board){
        for(int e=0;e<3;e++){
            if(board[e][0]==board[e][1] && board[e][2]==board[e][0] && board[e][0]!="."){
                return true;
            }

            if(board[0][e]==board[1][e] && board[2][e]==board[0][e] && board[0][e]!="."){
                return true;
            }

        }

        if(board[0][0]==board[1][1] && board[0][0]==board[2][2] && board[0][0]!="."){
            return true;
        }

        if(board[2][0]==board[1][1] && board[0][2]==board[2][0] && board[2][0]!="."){
            return true;
        }

        return false;
    }
}
