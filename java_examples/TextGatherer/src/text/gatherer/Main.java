package text.gatherer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	private static final String PATH="/home/mlu/work/issues/PROCESSED_IDS";
	public static void main(String args[]) throws IOException{
		/**
		 * custom filter that selects .pdf files for inclusion among attachments
		 */
		
		final Pattern filePattern = Pattern.compile("(\\d+).pending");
				
		File dir = new File(PATH);
		if(!dir.exists() || !dir.isDirectory()){
			System.out.println("Directory " + PATH + " doesn't exist");
		}
		File outputFile = new File("/home/mlu/work/issues/out.txt");
		FileWriter fw = new FileWriter(outputFile);
		int counter = 0;
		for(String fname : dir.list()){
			try {
			Matcher matcher = filePattern.matcher(fname);
			if(!matcher.matches()){
				continue;
			}
			String repaymentIdentification = matcher.group(1);
			System.out.print("Processing " + ++counter + ". " + fname);
			File file = new File(dir.getAbsoluteFile() + "/" + fname);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			File file2 = new File(dir.getAbsoluteFile() + "/" + repaymentIdentification +".tmpguid");
			FileReader fr2;
			
			fr2 = new FileReader(file2);
			BufferedReader br2 = new BufferedReader(fr2);
			fw.append(repaymentIdentification + " " + br.readLine() + " " + br2.readLine() + "\n");
			fw.flush();
			br.close();
			br2.close();
			System.out.println(" - PROCESSED");
			} catch (FileNotFoundException e) {
				System.out.println(" - File not found");
			}
		}
		fw.close();
		
	}
	
	
}
