import java.util.Random;
import java.util.Scanner;
import java.util.function.BiConsumer;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class App {

	private static final String[] names = new String[] {"c", "c#", "db", "d", "d#", "eb", "e", "f", "f#", "gb", "g", "g#","ab", "a","a#","bb", "b"};
	private static final int[] numbers = new int[] {     0,    1,    1,   2,    3,    3,   4,   5,   6,    6,    7,   8,    8,   9,  10,  10,   11};

	private static final Random r = new Random();
	private final Scanner in = new Scanner(System.in);

	private static final int NUM_QS = 10;
	private int totCorrect = 0;

	public static void main(String[] args) {
		App app = new App();
		
		for(int x = 0; x < NUM_QS; x++) {
			//Randomize questions
			int rand = r.nextInt(3 +1);
			switch(rand) {
			case 0 : app.testNames(); break;
			case 1 : app.testNumbers(); break;
			case 2 : app.testICsVis(); break;
			case 3 : app.testICsAur(); break;
			default : System.out.println("WTF? " + rand);
			}
			//			app.testICsAur();
		}
		System.out.println("FIN. " + app.totCorrect + " out of " + NUM_QS);
	}

	private void testICsVis() {
		testICs((Integer a, Integer b) -> System.out.println(names[a] + " : " + names[b]));
	}

	private void testICsAur() {
		testICs((Integer a, Integer b) -> {
			System.out.println("Playing ...");
			Player player = new Player();
			Pattern p1 = new Pattern((numbers[a] + 60) + "w").setVoice(0);
			Pattern p2 = new Pattern((numbers[b] + 60) + "w").setVoice(1);
			player.play(p1, p2);
		});
	}
	private void testICs(BiConsumer<Integer, Integer> ico) {
		int pc1 = r.nextInt(16 +1);
		int pc2 = r.nextInt(16 +1);
		ico.accept(pc1, pc2);

		int ans = in.nextInt(); 
		int ic = Math.abs(numbers[pc2] - numbers[pc1]);
		if(ic > 6) {
			ic = 12 - ic;
		}
		if(ans == ic) {
			System.out.println("Correct!");
			totCorrect++;
		} else {
			System.out.println("Wrong. Should be : " + ic);
		}
	}

	private void testNames() {
		int ran = r.nextInt(16 +1);
		System.out.println(names[ran]);

		int z = in.nextInt();
		if(numbers[ran] == z) {
			System.out.println("correct!");
			totCorrect++;
		} else {
			System.out.println("WRONG! Should be :" + numbers[ran]);
		}
	}

	private void testNumbers() {
		int ran = numbers[r.nextInt(16 +1)];
		System.out.println(ran);

		String s = in.next();
		checkNumber(s, ran);
	}

	private void checkNumber(String s, int ran) {
		for(int y = 0; y<names.length;y++) {
			if(names[y].equals(s) && numbers[y] == ran) {
				System.out.println("correct!");
				totCorrect++;
				return;
			}
		}
		System.out.println("WRONG!");
	}
}

