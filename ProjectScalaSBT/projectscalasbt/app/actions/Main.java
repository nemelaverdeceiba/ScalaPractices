package actions;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		String str = "qwerty-1qwerty-2 455 f0gfg 4";
		str = str.replaceAll("[^1-9]+", "");
		System.out.println(str);
	}

}
