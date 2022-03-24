import java.util.Scanner;
import java.util.Arrays;

public class cmsc401 {
    static int n;
    //public static transmuteObject[] adjacencyList;
   // public static int[]
    public static MinHeap minheap;
    public static int[] mappingArray = new int[100];
    public static int[][] adjacencyMatrix;
    public static int[] distanceArray;
    public static boolean[] visitedArray;
    public static int[] positionToMetal;
    private static final Scanner scanner = new Scanner(System.in);
    public static int lastArrPos =0;
    public static HeapItem initalS =null;

    //notes from class
    //use dijstra algo, use an array from 0-100 to store positions, similar to last assigment with ids, prob use matrix for the hw since it was on vid

    public static void main(String[] args) {
        Arrays.fill(mappingArray, -1);
        n = readInt();
        scanner.nextLine();
        adjacencyMatrix = new int[n+1][n+1]; // maybe need to be just lastArrPos
        distanceArray = new int[n+1];
        visitedArray = new boolean[n+1];
        positionToMetal= new int[n+1];
        minheap = new MinHeap(101);
        Arrays.fill(visitedArray,true);
        Arrays.fill(distanceArray,Integer.MAX_VALUE);
        for (int[] row : adjacencyMatrix)
            Arrays.fill(row, Integer.MAX_VALUE);
        readNextInput(n);
    }
    public static void readNextInput(int inCount){
        String response = readString();
        String[] parsedResponse = response.split(" ");

            int metalX = Integer.parseInt(parsedResponse[0]);
            int metalY = Integer.parseInt(parsedResponse[1]);
            int costXY = Integer.parseInt(parsedResponse[2]);
            if(metalX == 47) {
                minheap.insert(47,0,metalY);
            }else{
                minheap.insert(metalX,Integer.MAX_VALUE,metalY);
            }

            if(mappingArray[metalX]==-1 && mappingArray[metalY]==-1) {
                mappingArray[metalX] = lastArrPos;
                positionToMetal[lastArrPos++]=metalX;
                mappingArray[metalY] = lastArrPos;
                positionToMetal[lastArrPos++]=metalY;
                adjacencyMatrix[mappingArray[metalX]][mappingArray[metalY]] = costXY;
            }
            else if(mappingArray[metalX]!=-1 && mappingArray[metalY]==-1){
                mappingArray[metalY]=lastArrPos;
                positionToMetal[lastArrPos++]=metalY;
                adjacencyMatrix[mappingArray[metalX]][mappingArray[metalY]] = costXY;
            }
            else if(mappingArray[metalX]==-1 && mappingArray[metalY]!=-1){
                mappingArray[metalX]=lastArrPos;
                positionToMetal[lastArrPos++]=metalX;
                adjacencyMatrix[mappingArray[metalX]][mappingArray[metalY]] = costXY;
            }
            if(inCount>1)
                readNextInput(inCount-1);
            else{
                System.out.println(dijkstra());
            }
    }

    public static int dijkstra() {
        int shortest = 0;
        //int count = minheap.currentSize;
        distanceArray[0]=0;
        while (!minheap.isEmpty()) {
            HeapItem u = minheap.extractMin();
                        // 1 1 1 1 1 1
            shortest = u.getPriority();
            //int count = n;
            //int pos = mappingArray[u.getSource()];
            for(int i=0;i<lastArrPos;i++) {
                //relax(u.getSource(),u.getDest(),adjacencyMatrix[mappingArray[u.getSource()]][i]);
                if(adjacencyMatrix[mappingArray[u.getId()]][i]!=Integer.MAX_VALUE) {
                    //relax(u.getId(), adjacencyMatrix[mappingArray[u.getId()]][i],u.getPriority());
                    relax(mappingArray[u.getId()],i,adjacencyMatrix[mappingArray[u.getId()]][i]);
                }
            }
            visitedArray[mappingArray[u.getId()]] = false;

        }
        return shortest;
    }

    public static void relax(int distPos, int distPos2, int costXY){
        //System.out.println(distanceArray[mappingArray[metalX]]+costXY +"hi");
//        if(distanceArray[mappingArray[metalY]] > distanceArray[mappingArray[metalX]]+costXY){//adjacencyMatrix[mappingArray[metalY]][mappingArray[metalX]]
//            distanceArray[mappingArray[metalY]] = distanceArray[mappingArray[metalX]]+costXY;
//            minheap.decreaseKey(mappingArray[metalY],distanceArray[mappingArray[metalY]]);// new priority I get from matrix?
//        }
        if(visitedArray[distPos]==true && distanceArray[distPos]>distanceArray[distPos2]+costXY){
            distanceArray[distPos]=distanceArray[distPos2]+costXY;
            System.out.println(positionToMetal[distPos]);
            minheap.decreaseKey(positionToMetal[distPos],distanceArray[distPos]);
        }
    }
    private static Integer readInt() {
        return scanner.nextInt();
    }

    private static String readString() {
        return scanner.nextLine();
    }

}

// @author: Touhid
 class HeapItem {
    private int id, priority,metalY;
    public HeapItem(int id, int priority,int metalY) {
        this.id = id;
        this.priority = priority;
        this.metalY = metalY;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getMetalY() {
        return metalY;
    }
    public void setMetalY(int metalY) {
        this.metalY = metalY;
    }
    @Override
    public String toString() {
        return "HeapItem { id= " + id + ", priority=" + priority + '}';
    }
//    private int id, source, dest, priority;
//
//    public HeapItem(int source, int dest, int priority) {
//        this.priority = priority;
//        this.source = source;
//        this.dest = dest;
//    }
//
//    public int getPriority() {
//        return priority;
//    }
//
//    public void setPriority(int priority) {
//        this.priority = priority;
//    }
//
//
//    public int getSource() {
//        return source;
//    }
//
//    public void setSource(int source) {
//        this.source = source;
//    }

//    public int getDest() {
//        return dest;
//    }
//
//    public void setDest(int dest) {
//        this.dest = dest;
//    }
}
 class MinHeap {
    public final int[] idToPositionMap;
    public int lastId = 0;
    public final HeapItem[] myMinHeapArray;
    public int currentSize;

    public MinHeap(int size) {
        this.idToPositionMap = new int[size];
        this.myMinHeapArray = new HeapItem[size];
        this.currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize <= 0;
    }

    public void insert(int id, int priority,int metalY) {
        //myMinHeapArray[currentSize] = new HeapItem(source, destination, priority);
        myMinHeapArray[currentSize] = new HeapItem(id,priority,metalY);
        idToPositionMap[lastId] = currentSize++;
        decreaseKey(lastId++, priority);
    }

    public void decreaseKey(int id, int newPriority) {
        int i = idToPositionMap[id];
        myMinHeapArray[i].setPriority(newPriority);

        int parentI = parent(i);
        while (i > 0 && myMinHeapArray[parentI].getPriority() > myMinHeapArray[i].getPriority()) {
            swap(i, parentI);
            i = parentI;
            parentI = parent(i);
        }
    }

    public HeapItem extractMin() {
        HeapItem minItem = myMinHeapArray[0];

        myMinHeapArray[0] = myMinHeapArray[--currentSize];
        minHeapify(0); // to restore heap property for the recently updated root element

        return minItem;
    }

    private int parent(int childPosition) {
        return (int) Math.floor((childPosition - 1) / 2.0);
    }

    public static int left(int parentPosition) {
        // For 1-indexed array implementation, the formula would be (2 * parentPosition).
        // But, here +1 is needed as we're using 0-indexed array system.
        return 2 * parentPosition + 1;
    }

    public static int right(int parentPosition) {
        // For 1-indexed array implementation, the formula would be (2 * parentPosition + 1).
        // But, here +2 is needed as we're using 0-indexed array system.
        return 2 * parentPosition + 2;
    }

    private void minHeapify(int i) {
        int left = left(i);
        int right = right(i);

        // We're to identify min. item among the i'th element & its 2 children (left & right).
        // Firstly, we're looking at left:
        int minPos = (left <= currentSize && myMinHeapArray[left].getPriority() < myMinHeapArray[i].getPriority()) ?
                left : i;
        // Then, we're looking at right:
        if (right <= currentSize && myMinHeapArray[right].getPriority() < myMinHeapArray[minPos].getPriority())
            minPos = right;

        // If any of the left or right child has lower priority than the i'th element, we're to swap those two &
        // then we should min-heapify the lower subtree.
        if (minPos != i) {
            swap(i, minPos);
            minHeapify(minPos);
        }
    }

    // TODO : Provide source & dest. for node-1 & node-2
    private void swap(int pos1, int pos2) {
        HeapItem node1 = myMinHeapArray[pos1];
        HeapItem node2 = myMinHeapArray[pos2];
        myMinHeapArray[pos1] = node2;
        myMinHeapArray[pos2] = node1;
        idToPositionMap[node1.getId()] = pos2;
        idToPositionMap[node2.getId()] = pos1;
//        idToPositionMap[node1.getSource()] = node2.getSource();
//        idToPositionMap[node2.getSource()] = node1.getSource();
//
        idToPositionMap[node1.getMetalY()] = node2.getMetalY();
        idToPositionMap[node2.getMetalY()] = node1.getMetalY();
    }
}