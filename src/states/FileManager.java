package states;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
* Skapar och hanterar filen highscore.txt som lagrar tidigare highscores
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class FileManager {

	public FileManager() {
	}


	public void intIntoFile(int i) {
		try {
			Files.writeString(Paths.get("highscore.txt"), i +"", StandardOpenOption.APPEND);
			Files.writeString(Paths.get("highscore.txt"), "\n", StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	


	// används för att sortera filen med highscores och returner den plats i filen
	// som önskas
	public int getIntegerArray(int i) {

		Scanner file = null;
		ArrayList<Integer> list = new ArrayList<Integer>();

		try {
			file = new Scanner(new File("highscore.txt"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (file.hasNext()) {
			if (file.hasNextInt()) {
				list.add(file.nextInt());
			} else {
				file.next();
			}
			Collections.sort(list);
			Collections.reverse(list);
		}
		return list.get(i);
	}

}
