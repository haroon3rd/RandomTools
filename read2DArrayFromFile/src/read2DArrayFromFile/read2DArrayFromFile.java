package read2DArrayFromFile;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class read2DArrayFromFile {
    
	private static BufferedReader readIn = null;
    private static int rows = 0;
    private static int columns = 0;
    private static int [][] taskMatrix = null;
    private static int [][] devMatrix = null;
    private static int [] devStatus = null;
    private static int [] devId = null;
    private static int [] taskToDevIndex = {0,1,3,1,2,3,2,3};
    
    public static void main(String []args) throws Exception {
        //Reading from file to an array.
    	try {
            String filepath = "/home/amran/sample.txt";
        	int lineNum = 0;
            int row=0;
            readIn = new BufferedReader(new FileReader(filepath));
            String line = null;
            System.out.println("   T0 T1 T2 T3 T4 T5 T6 T7");
            System.out.println("--------------------------");
            while((line=readIn.readLine()) !=null) {
                lineNum++;
                if(lineNum==1) {
                    rows = Integer.parseInt(line);
                } else if(lineNum==2) {
                    columns = Integer.parseInt(line);
                    taskMatrix = new int[rows][columns];
                } else {
                    String [] delimiter = line.split(" ");
                    if(row<rows)
                    	System.out.print("T" + row);
                    for (int j=0; j<delimiter.length; j++) {
                        taskMatrix[row][j] = Integer.parseInt(delimiter[j]);
                        System.out.print("  ");
                        System.out.print(taskMatrix[row][j]);
                    }
                    System.out.print("\n");
                    row++;
                }
            }
        } catch (Exception ex) {
            System.out.println("The code throws an exception");
            System.out.println(ex.getMessage());
        } finally {
            if (readIn!=null) readIn.close();
        }
        Scanner reader = new Scanner(System.in);
        devStatus = new int[4];        
        System.out.println("Enter cloud status (1 for in cloud, 0 for not in cloud) for nodes.");
        for(int i=0;i<4;i++) {
        	  // Reading from System.in
        	System.out.println("Enter status of node M"+ i + ": ");
            devStatus[i] = reader.nextInt(); // Scans the next token of the input as an int.
        }
        reader.close();
        
        devId = new int[] {0000,1111,2222,3333,4444};
        HashMap<Integer, Integer> hashMap = new HashMap<>(); 
        
//        print(map); 
        hashMap.put(0,0); 
        hashMap.put(1,1); 
        hashMap.put(2,3); 
        hashMap.put(3,1);
        hashMap.put(4,2);
        hashMap.put(5,3);
        hashMap.put(6,2);
        hashMap.put(7,3);
        int hashMapSize = hashMap.size();  
        System.out.println("Size of map is: " + hashMapSize); 
      
        print(hashMap); 

        devMatrix = new int[4][4];
        for (int i=0; i<hashMapSize; i++) {
        	for (int j=0; j<hashMapSize; j++) {
        		 	
        		if(taskMatrix[i][j] == 1) {
        			if(devStatus[taskToDevIndex[i]] ==0 && devMatrix[taskToDevIndex[j]][taskToDevIndex[i]] != 1) {
        				devMatrix[taskToDevIndex[i]][taskToDevIndex[j]] = 1;
        			}else {
        				devMatrix[taskToDevIndex[j]][taskToDevIndex[i]] = 1;
        			}
        		}
        	}
        }
        
        System.out.println();
        System.out.println("   M0 M1 M2 M3");
        System.out.println("--------------");
        for (int i=0; i<4; i++) {
        	System.out.print("M" + i);
        	for (int j=0; j<4; j++) {
        		System.out.print("  ");
        		System.out.print(devMatrix[i][j]);
        		}
        	System.out.println();
        	} 
    
    }
    
    public static void print(Map<Integer, Integer> map)  
    { 
        if (map.isEmpty())  
        { 
            System.out.println("map is empty"); 
        } 
          
        else
        { 
            System.out.println(map); 
        } 
    } 
}
