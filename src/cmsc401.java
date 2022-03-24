import java.util.Scanner;
import java.util.Arrays;

public class cmsc401 {
    static int n;
    static int count;
    public static int[] mappingArray = new int[100];
    public static int[][] adjacencyMatrix;
    public static int[] distanceArray;
    public static boolean[] visitedArray;
    private static final Scanner scanner = new Scanner(System.in);
    public static int lastArrPos =0;
    public static int initalS =-1;
    public static int goldPos =-1;

    public static void main(String[] args) {
        Arrays.fill(mappingArray, -1);
        n = readInt();
        count=n;
        scanner.nextLine();
        adjacencyMatrix = new int[n+1][n+1];
        distanceArray = new int[n+1];
        visitedArray = new boolean[n+1];
        Arrays.fill(visitedArray,true);
        Arrays.fill(distanceArray,10000000);
        for (int[] row : adjacencyMatrix)
            Arrays.fill(row, 10000000);
        readNextInput(n);
    }
    public static void readNextInput(int inCount){
        String response = readString();
        String[] parsedResponse = response.split(" ");

            int metalX = Integer.parseInt(parsedResponse[0]);
            int metalY = Integer.parseInt(parsedResponse[1]);
            int costXY = Integer.parseInt(parsedResponse[2]);

            if(mappingArray[metalX]==-1 && mappingArray[metalY]==-1) {
                if(metalX == 47&&initalS ==-1) {
                    initalS = lastArrPos;
                }
                mappingArray[metalX] = lastArrPos++;
                if(metalY== 79 &goldPos==-1) goldPos= lastArrPos;
                mappingArray[metalY] = lastArrPos++;
                adjacencyMatrix[mappingArray[metalX]][mappingArray[metalY]] = costXY;
            }
            else if(mappingArray[metalX]!=-1 && mappingArray[metalY]==-1){
                if(metalY== 79 &goldPos==-1) goldPos= lastArrPos;
                mappingArray[metalY]=lastArrPos++;
                adjacencyMatrix[mappingArray[metalX]][mappingArray[metalY]] = costXY;
            }
            else if(mappingArray[metalX]==-1 && mappingArray[metalY]!=-1){
                if(metalX == 47&&initalS ==-1) {
                    initalS = lastArrPos;
                }
                mappingArray[metalX++]=lastArrPos;
                adjacencyMatrix[mappingArray[metalX]][mappingArray[metalY]] = costXY;
            }
            else if(mappingArray[metalX]!=-1 && mappingArray[metalY]!=-1){
                adjacencyMatrix[mappingArray[metalX]][mappingArray[metalY]] = costXY;
            }
            if(inCount>1)
                readNextInput(inCount-1);
            else{
                System.out.println(dijkstra(mappingArray[47]));
            }
    }

    public static int dijkstra(int start) {
        distanceArray[start]=0;
        int u = start;

        while(count>0){
            u = otherExtractMin(u);
            for(int v=0;v<=n;v++){
                if(adjacencyMatrix[u][v]!=10000000){
                        relax(u, v, adjacencyMatrix[u][v]);
                }
            }
        }
       return distanceArray[goldPos];
    }

    public static void relax(int distPos, int distPos2, int costXY){
    if(distanceArray[distPos2]>distanceArray[distPos]+costXY){
        distanceArray[distPos2]=distanceArray[distPos]+costXY;
    }

    }
    public static int otherExtractMin(int u){
        int min_dist =10000000;
        int min_dist_v =-1;// if statement if its not -1 update visited array
        for(int v=0;v<=n;v++){
            if(visitedArray[v]==true) {
                if (distanceArray[v] <= min_dist) {
                    min_dist = distanceArray[v];
                    distanceArray[v]=min_dist;
                    min_dist_v=v;
                }
            }
        }
        visitedArray[min_dist_v]=false;
        count--;
        if(min_dist_v==-1) return u;
        return min_dist_v;
    }
    private static Integer readInt() {
        return scanner.nextInt();
    }

    private static String readString() {
        return scanner.nextLine();
    }
}