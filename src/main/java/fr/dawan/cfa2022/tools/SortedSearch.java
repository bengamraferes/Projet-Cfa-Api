package fr.dawan.cfa2022.tools;

public class SortedSearch {
	  public static int countNumbers(int[] sortedArray, int lessThan) {
		  
		  int min = 0;
		  int max = sortedArray.length-1  ;
		  int milieu = 0;
		  while ( min != max) {
			 milieu = ( min + max ) /2 ;
			  if (lessThan > sortedArray[milieu]  ) {
					
				  max = milieu -1;
				 if( sortedArray[milieu +1 ] >= lessThan ) {
					 return milieu +1;
				 }
			}
			  else if(lessThan <= sortedArray[milieu]) {
				min = milieu +1;
			}
			
		}
		  return milieu  ;
		  
	
	       
	    }
	    
	    public static void main(String[] args) {
	        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
	    }
}
