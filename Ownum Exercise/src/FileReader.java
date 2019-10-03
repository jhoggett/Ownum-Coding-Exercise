import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader 
{
	//Gets the file path
	public static void main(String[] args)
	{
		String filePath = "C:\\Coding Exercises\\FileToRead\\passage.txt";	
		
		StringBuilder contentBuilder = new StringBuilder();
		
		// Reads the file path contents line by line and then converts its contents to a string
		try(Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8))
		{
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		String content = contentBuilder.toString();
		
		System.out.println( getWordCount( content ) );
		System.out.println( getTopTenWords( content ) );
		System.out.println( getLastSentence( content ) );
	}
	

	private static String getWordCount(String content)
	{	
		// Put content into a string array to allow splitting by spaces, then gets the arrays length(AKA word count)
		String[] words = content.split("\\s+");
		int wordCount = words.length;
		
		String countOutput = "The file's word count is " + wordCount;
		
		return countOutput;
	}
	
	public static List<String> getTopTenWords(String content)
	{
		Map<String, Integer> wordCountMap = new HashMap<>();
		
		String[] words = content.toLowerCase().split("\\s+");
		
		for (String word : words)
		{
			Integer count = wordCountMap.get(word);
			if(count == null)
			{
				wordCountMap.put(word, 1);
			}
			else
			{
				wordCountMap.put(word, count + 1);
			}
		}
		
		List<String>[] topWords = new List[words.length + 1];
		
		for (int i = 0; i < topWords.length; i++) topWords[i] = new ArrayList();
		for (String key : wordCountMap.keySet())
		{
			topWords[wordCountMap.get(key)].add(key);
		}
		int limit = 10;
		List<String> output = new ArrayList();
		for (int i = topWords.length - 1; i >= 0; i--)
		{
			output.addAll(topWords[i]);
			if (output.size() >= limit) break;
		}
		System.out.println("Top ten words");
		return output;		
	}
	
	public static String getLastSentence(String content)
	{
		String[] sentences = content.split("\\.");
		
		String outputSentence = "";
		
		for(int i = 0; i < sentences.length; i++)
		{
			
			String[] sentencesWithMostUsedWord = new String[sentences.length];
			if(sentences[i].contains("the"))
			{
				sentencesWithMostUsedWord[i] = sentences[i];
				outputSentence = sentencesWithMostUsedWord[sentencesWithMostUsedWord.length - 3];
			}
		}
		
		String output = "The last sentence with the most used word is:" + outputSentence;
		
		return output;
	}
}
