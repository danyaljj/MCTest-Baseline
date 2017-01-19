package edu.illinois.cs.cogcomp.McTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MCTestReader {
	public static List<MCTestInstance> mcAll = new ArrayList<MCTestInstance>(); 

	public static void main(String[] args) { 
		readAll(); 
	}
	
	public static void readAll() { 
		System.out.println("Starting to read the files! "); 
		String initials = "data/";
		readTSV(initials + "mc160.train.tsv");
		//readTSV(initials + "mc160.test.tsv");
		//readTSV(initials + "mc160.dev.tsv"); 

		//readTSV(initials + "mc500.train.tsv");
		//readTSV(initials + "mc500.test.tsv");
		//readTSV(initials + "mc500.dev.tsv"); 
		System.out.println("Done with reading the files! "); 
	}

	public static void readTSV(String f) { 
		String ansF = f.replace(".tsv", ".ans"); 
		
		List<String[]> answers = new ArrayList<String[]>(); 
		List<int[]> answersInt = new ArrayList<int[]>(); 
		
		
		try{
			InputStream ips=new FileInputStream(ansF); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line;
			while ((line=br.readLine())!=null){
				//System.out.println(line); 
				String[] optSplit = line.split("\t"); 
				answers.add(optSplit); 
				int[] optSplitInt = new int[4]; 
				for( int i = 0; i < 4; i++) {  
					if( optSplit[i].equals("A") )
						optSplitInt[i] = 0; 
					else if( optSplit[i].equals("B") )
						optSplitInt[i] = 1; 
					else if( optSplit[i].equals("C") )
						optSplitInt[i] = 2; 
					else if( optSplit[i].equals("D") )
						optSplitInt[i] = 3; 
					else { 
						System.out.println("optSplit[i] = >"+optSplit[i] + "<"); 
						System.out.println("********* ERROR IN OPTIONS!! ************ ");
					}
				}
				answersInt.add(optSplitInt); 
			} 
			br.close();	
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		int it = 0; 
		try{
			InputStream ips=new FileInputStream(f); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line;
			while ((line=br.readLine())!=null){
				//System.out.println(line);
				String[] split = line.split("\t"); 
				//System.out.println(split.length); 
				MCTestInstance ins = new MCTestInstance(); 
				ins.instanceInfo = split[0]; 
				ins.turkInfo = split[1]; 
				ins.story = split[2]; 
				
				//System.out.println("1"); 
				//ins.questions = new String[4]; 
				int ind = 0; 
				int ind2 = 0; 
				String[] opts = new String[4];  
				for(int j = 2; j < split.length; j++) { 
					//System.out.println("j = " + j + " ----> "+ split[j]);
					//System.out.println("2"); 
					if( (j - 3)%5 == 0 ) { 
						ind = (j - 3)/5;
						ind2 = j; 
						//System.out.println("ind = " + ind); 
						//System.out.println("ind2 = " + ind2); 
						if( split[j].contains("one: ") ) { 
							ins.questions[ind] = split[j].replace("one: ", "");	
							ins.NeedsMultipleSentences[ind] = false; 
						}
						else if( split[j].contains("multiple: ") ) { 
							ins.questions[ind] = split[j].replace("multiple: ", "");
							ins.NeedsMultipleSentences[ind] = true; 
						}
						else 
							System.out.println("********* ERROR: NOT VALID QUESTION **********"); 
						//ins.questions[ind] = split[j]; 
						//System.out.println("split[j] = " + split[j]); 
						
						opts = new String[4];
					} 
					else {
						//System.out.println("---> j - ind2-1 = " + (j - ind2-1)  + "  j = " + j);
						opts[j - ind2-1] =  split[j];
						//System.out.println("opts[" + (j - ind2-1) + "] = " + opts[j - ind2-1] );
					}
					ins.options[ind] = opts; 
				}
				//System.out.println("it = " + it + " answers.size() = " + answers.size()); 
				//System.out.println( ins.toString() ); 
				ins.correctAnswers = answers.get(it);
				ins.correctAnswersInt = answersInt.get(it);
				
				it++;
				mcAll.add(ins);
			}
			br.close();
		} 
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
