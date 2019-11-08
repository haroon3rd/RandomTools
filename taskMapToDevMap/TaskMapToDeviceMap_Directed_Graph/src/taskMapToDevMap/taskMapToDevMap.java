/* Amran Haroon August 05 2019, LENSS-LAB, CSE, TAMU 
 * For MStorm implementation on Cloud
 * Version 1.1
 */

package taskMapToDevMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class taskMapToDevMap {
    
    private static int [][] taskMatrix = null;
    private static int [][] devMatrix = null;
    private static int [] devStatus = null;
    private static int [] taskToDevIndex = {0,1,1,1,1,2,2,3,4,2,2,3,4,4,2,3,3,4,5,5};
    
    public static void main(String []args) throws Exception {
    	//Hard coded file path.
    	String filePath = "/home/amran/sample.txt";
    	
    	//reads the task matrix from text file
    	taskMatrix = getTaskMap(filePath);
        
        //creates a hashMap to replicate the actual hashMap
        HashMap<Integer, Integer> hashMap = new HashMap<>();  
        hashMap.put(0,0); 
        hashMap.put(1,1); 
        hashMap.put(2,1); 
        hashMap.put(3,1);
        hashMap.put(4,1);
        hashMap.put(5,2);
        hashMap.put(6,2);
        hashMap.put(7,3);
        hashMap.put(8,4); 
        hashMap.put(9,2); 
        hashMap.put(10,2); 
        hashMap.put(11,3);
        hashMap.put(12,4);
        hashMap.put(13,4);
        hashMap.put(14,2);
        hashMap.put(15,3);
        hashMap.put(16,3); 
        hashMap.put(17,4); 
        hashMap.put(18,5); 
        hashMap.put(19,5);
        
        int hashMapSize = hashMap.size();  
        System.out.println("Size of map is: " + hashMapSize);
        int deviceCount = new HashSet<Integer>(hashMap.values()).size();
        System.out.println("Number of Devices Connected: " + deviceCount);
    	//reads user input for device cloud status and stores in an array.
    	Scanner reader = new Scanner(System.in);
        devStatus = new int[deviceCount];        
        System.out.println("Enter cloud status (1 for in cloud, 0 for not in cloud) for nodes.");
        
        for(int i=0;i<deviceCount;i++) {
        	  // Reading from System.in
        	System.out.println("Enter status of node M"+ i + ": ");
            devStatus[i] = reader.nextInt(); // Scans the next token of the input as an integer.
        }
        reader.close();
        //prints HashMap
        printMap(hashMap); 

        //converts Task Matrix to Device Map for connection buildup
        devMatrix = getDevMap(taskMatrix, devStatus, taskToDevIndex, hashMapSize, deviceCount);
        
        //prints the final Device Map
        printMatrix(devMatrix, deviceCount);
    }
    
    
    //Reading from file to an array. Takes file path, returns 2D Array.
    public static int[][] getTaskMap(String fileName) throws IOException{
    	BufferedReader readIn = null;
    	int [][] readTaskMatrix = null;
        int rows = 0;
        int columns = 0;
    	try {
            String filepath = fileName;
        	int lineNum = 0;
            int row=0;
            readIn = new BufferedReader(new FileReader(filepath));            
            String line = null;
            while((line=readIn.readLine()) !=null) {
                lineNum++;
                if(lineNum==1) {
                    rows = Integer.parseInt(line);
                } else if(lineNum==2) {
                    columns = Integer.parseInt(line);
                    readTaskMatrix = new int[rows][columns];
                } else {
                    String [] delimiter = line.split(" ");
                    if(row<rows)
                    for (int j=0; j<delimiter.length; j++) {
                    	readTaskMatrix[row][j] = Integer.parseInt(delimiter[j]);
                    }
                    System.out.print("\n");
                    row++;
                }
            }
            
            System.out.print("   ");
            for (int j=0; j<columns; j++) {
        		if(j<11) {
        			System.out.print(" T"+j);
        		}
        		else {
        			System.out.print("T"+j);
        		}
        	}
            System.out.println();
            System.out.print("---");
            for (int j=0; j<columns; j++) {
        		System.out.print("---");
        	}
            System.out.println();
            for (int i=0; i<rows; i++) {
        		if(i<10) {
        			System.out.print("T"+ i + " ");
        		}
        		else {
        			System.out.print("T"+ i);
        		}
            	for (int j=0; j<columns; j++) {
            		System.out.print("  ");
            		System.out.print(readTaskMatrix[i][j]);
            	}
            	System.out.println();
            } 
        } catch (Exception ex) {
            System.out.println("The code throws an exception");
            System.out.println(ex.getMessage());
        } finally {
            if (readIn!=null) readIn.close();
        }
    	return readTaskMatrix;
    }
    
    //Takes task map as 2D Array, devise cloud status as 1D Array, Task to Device Mapping (HashMap) as 1D Array
    //size of the Hash Map as integer, returns device connect map as 2D Array.
    public static int[][] getDevMap(int[][] getTaskMatrix, int[] getDevStatus, int[] getTaskToDevIndex, int getHashMapSize, int count) {
    	int [][] pushDevMatrix = null;
    	pushDevMatrix = new int [count][count];
    	
    	for(int i=0;i<getHashMapSize;i++) {
	    	for (int j=0; j<getHashMapSize; j++) {
			 	
	    		if(getTaskMatrix[i][j] == 1) {
	    			if(getDevStatus[getTaskToDevIndex[i]] ==0 && pushDevMatrix[getTaskToDevIndex[j]][getTaskToDevIndex[i]] != 1) {
	    				pushDevMatrix[getTaskToDevIndex[i]][getTaskToDevIndex[j]] = 1;
	    			}else {
	    				pushDevMatrix[getTaskToDevIndex[j]][getTaskToDevIndex[i]] = 1;
	    			}
	    		}
	    	}
    	}
    	return pushDevMatrix;
    }
    
    //Takes 2D Array input and prints it. Designed for 4 node maps only.
    public static void printMatrix(int[][] printMatrix, int count)  
    { 
        System.out.println();
        System.out.print("  ");
        for (int j=0; j<count; j++) {
    		System.out.print(" M"+j);
    	}
        System.out.println();
        System.out.print("--");
        for (int j=0; j<count; j++) {
    		System.out.print("---");
    	}
        System.out.println();
        for (int i=0; i<count; i++) {
        	System.out.print("M" + i);
        	for (int j=0; j<count; j++) {
        		System.out.print("  ");
        		System.out.print(printMatrix[i][j]);
        	}
        	System.out.println();
        } 
    } 
    
    //Prints the hash map.
    public static void printMap(Map<Integer, Integer> map)  
    { 
        if (map.isEmpty()) 
            System.out.println("map is empty");
        else
            System.out.println(map);
    } 
}
