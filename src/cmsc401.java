import java.util.Scanner;
public class cmsc401 {
    static int n;
    public static transmuteObject[] adjacencyList;
    private static final Scanner scanner = new Scanner(System.in);
    public static int lastArrPos =-1;

    // in position 0-n of adjacency list, do we assign each metal an id or have another array keeping track of all positions? or neither
    // in position 0 we store the array of neighbors for the first atomic number in our case silver, is silvers atomic number stored in 0 of the array of neighbors?
    // how do we store an array of objects inside an array?

    //notes from class
    //use dijstra algo, use an array from 0-100 to store positions, similar to last assigment with ids, prob use matrix for the hw since it was on vid

    public static void main(String[] args) {
    // do we store the objects into a matrix or adjacency list for this project, is it directed, undirected etc, what algo should we use?
        n = readInt();
        adjacencyList = new transmuteObject[n];
        scanner.nextLine();
        readNextInput();
    }
    public static void readNextInput(){
        String response = readString();
        String[] parsedResponse = response.split(" ");
        // if using adjacency list will need checks here to check if one metal goes into another, if yes add it to the adjacency list
        if(lastArrPos+1!=n){
            adjacencyList[lastArrPos] = new transmuteObject(Integer.parseInt(parsedResponse[0]),Integer.parseInt(parsedResponse[1]),Integer.parseInt(parsedResponse[2]));
            lastArrPos++;
            readNextInput();
        }
    }
    private static Integer readInt() {
        return scanner.nextInt();
    }

    private static String readString() {
        return scanner.nextLine();
    }
}

class transmuteObject {
    int inputNum;
    int outputNum;
    int cost;

    public transmuteObject(int inputNum, int outputNum, int cost) {
        this.inputNum = inputNum;
        this.outputNum = outputNum;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "transmuteObject{" +
                "inputNum=" + inputNum +
                ", cost=" + cost +
                ", outputNum=" + outputNum +
                '}';
    }
}
