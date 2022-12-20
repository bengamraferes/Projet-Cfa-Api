package fr.dawan.cfa2022.tools;

public class UserInput {

	 public static class TextInput {
	      String value = "";
	      
	      public String getValue(){
	        return this.value;
	      }
	      public void add(char c){
	        this.value += c ;
	      }
	    }

	    public static class NumericInput extends TextInput {
	      @Override
	       public void add(char c){
	         
	         if(c <= '9' && c >= '0' ){
	            this.value += c ;

	         }
	      }
	    }

	    public static void main(String[] args) {
	        TextInput input = new NumericInput();
	        input.add('1');
	        input.add('a');
	        input.add('0');
	        System.out.println(input.getValue());
	    }
}
