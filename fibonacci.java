package Test;

import java.util.HashSet;
import java.util.Set;

public class fibonacci {
	public static void main(String[] args){
		for(int i = 1; i < 200; i++){
			System.out.println(i+":"+fibonacci_of(i));
		}
	}
	public static int fibonacci_of(int n){
		if(n <= 1)return n;
		return fibonacci_of(n-1)+fibonacci_of(n-2);
	}

}
