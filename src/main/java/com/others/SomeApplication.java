package com.others;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.primitives.Ints;
import com.microservices.rest.restfulwebservices.user.User;
import com.microservices.rest.restfulwebservices.user.UserDaoService;


public class SomeApplication {
	
	@Autowired
	UserDaoService userDaoService;

	public SomeApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void displayAllUsers() {
		List<User> users = userDaoService.findAll();
		System.out.println(users.size());
	}
	
	

	private static int sol(int[] A) {
		int minAdjValue = -2;
		

		for(int i = 0; i < A.length; i ++) {
			System.out.println(A[i]);
		}
		Arrays.sort(A);
		for(int i = 0; i < A.length; i ++) {
			System.out.println(A[i]);
		}
		
		
		
		return minAdjValue;
		
	}
	
	private static int mysolution(int[] A) {
		int minAdjValue = 100000000;
		

		Arrays.sort(A);
		for(int i = 0; i < (A.length - 1); i ++) {
			//next adjacent element
			int j = i + 1;
			int adjValue = Math.abs(A[j] - A[i]);
			
			if (adjValue <= minAdjValue)
				minAdjValue = adjValue;
			else
				minAdjValue = -1;			
		}
		
		
		
		return minAdjValue;
		
	}
	
	private static int solution(int[] A) {
        int currentSmallPos = 1;
        int pos = 1;
        
        while (pos > 0)  {
        	currentSmallPos = pos;
        	
        	Arrays.sort(A);
        	//check if the current smallest positive number is in the array
        	if ( Arrays.binarySearch(A, pos) >= 0){
        		pos++;
        	}else {
        		break;
        	}
        }
		
        return currentSmallPos;
	}
	
	private static int solutionOtherVersion(int[] A) {
        int currentSmallPos = 1;
        int pos = 1;
        List<int[]> list = Arrays.asList(A);
        
        while (pos > 0)  {
        	currentSmallPos = pos;
        	
        	Arrays.sort(A);
        	if ( list.contains(pos)){
        		pos++;
        	}else {
        		break;
        	}
        }
		
        return currentSmallPos;
	}
	
	private int returnInteger(int i) {
		int j = i + 1;
		int k = i++;
		System.out.println(j);
		System.out.println(k);
        /*for (Map.Entry<Integer, Boolean> entry : onBulbs.entrySet())
        {	
        	System.out.println(entry.getKey().intValue()) ;
        	System.out.println(entry.getValue().booleanValue()) ;

        }*/
		
		return j;
		
	}
	
	public static void main(String[] args) {
		SomeApplication some = new SomeApplication();
		//some.displayAllUsers();
		
		/*System.out.println("Sample");
		System.out.println(some.returnInteger(1));*/
		
	    /*int[] integers1 = new int[] { 3, 2, 4, 1, 5 };
	    int[] integers2 = new int[] { 2 , 3, 4, 1, 5 };
	    int[] integers3 = new int[] { 1 , 3, 4, 2, 5 };
	    int[] integers4 = new int[] { 1 , 2, 3, 4, 5 };
	    int[] integers5 = new int[] { 3 , 2, 4, 5, 1 };*/
	    
	    System.out.println(stringProcessor("00-44  48 5555 8361"));
	    System.out.println(stringProcessor("555372654"));
	    System.out.println(stringProcessor("0 - 22 1985--324"));

	}
	//022-198-532-4
	
	public static String stringProcessor(String S) {
		
		//trim spaces and dashes
		S = S.replaceAll("\\s","").replace("-", "");
		int size = S.length();
		StringBuilder formattedNumber = new StringBuilder();
		
		for(int i = 0; i < size ; i++) { 
			int blockIdentifier = i + 1;
			formattedNumber.append( S.charAt(i));
			
			if((blockIdentifier%3) == 0)
				if(i != (S.length() - 1))
					formattedNumber.append("-");
			

			
		    //char c = S.charAt(i); 
		    //System.out.println(c);
		}

		String finalChars = formattedNumber.toString();
		System.out.println(finalChars.substring(finalChars.length() - 5));
		

		/*
		 * special case when final block has size 1
		 * modulo 3 plus 4 numbers e.g. 53-24
		 */	
		if( ((size - 4)% 3) == 0 ) {
			StringBuilder specialFormattedNumber = new StringBuilder();
			specialFormattedNumber
			.append(S.substring(size - 4, size - 3))
			.append("-")
			.append(S.substring(size - 2, size - 1));
			
			System.out.println(specialFormattedNumber.toString());
		}
		
		return formattedNumber.toString();
	}
	
    public static int lightsOn(int[] A) {
        // write your code in Java SE 8
    	
    	Map<Integer, Boolean> onBulbs = new HashMap<Integer, Boolean>();
    	int countMomentAllBulbsShine = 0;
    	boolean isThisTheMoment = true;
    	
    	initializeBulbStatus( A, onBulbs);
    	
    	//commence turning on bulbs based on sequence
    	for (int i = 0; i < A.length; i++) {
    		onBulbs.put(Integer.valueOf(A[i]), true);
    		isThisTheMoment = true;
    		
    		//check previous bulbs if ON
    		for (int j = A[i] - 1; j >= 1; j--) {
    			if( false == onBulbs.get(Integer.valueOf(j))) {
    				isThisTheMoment = false;
    				break;
    			}  			
    		}
    		
    		System.out.println(isThisTheMoment);
    		//count the moments when every turned ON bulbs shine
    		if (isThisTheMoment)
    			countMomentAllBulbsShine++;

    	}
    		
        return countMomentAllBulbsShine;
    }
    
    /*
     * Initialize All Bullbs Status to turn OFF and NO shine
     */
    private static void initializeBulbStatus(
    		int[] A,
    		Map<Integer, Boolean> onBulbs) {
    	
    	for (int i = 0; i < A.length; i++) {
    		onBulbs.put(A[i], false);
    	}
    }
    
    
    

	
	
	

}
