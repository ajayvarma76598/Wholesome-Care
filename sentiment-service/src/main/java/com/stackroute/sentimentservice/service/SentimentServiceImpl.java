package com.stackroute.sentimentservice.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.stackroute.sentimentservice.model.CSVDetail;
import com.stackroute.sentimentservice.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Service
public class SentimentServiceImpl implements SentimentService {

	/*@Value("${sentiment.csv.filepath}")
	private String csvFilePath;*/
	private StanfordCoreNLP stanfordCoreNLP = Pipeline.getInstance();
	private String NEUTRAL = "Neutral";
	private String POSITIVE = "Positive";
	private String NEGATIVE = "Negative";

	@Autowired
	public SentimentServiceImpl(StanfordCoreNLP stanfordCoreNLP) {
		this.stanfordCoreNLP = stanfordCoreNLP;
	}

	@Override
	public ArrayList<String> getResource(String text) {
		int negativeCounter = 0;
		int positiveCounter = 0;
		int neutralCounter = 0;
		String result="";
		ArrayList<String> sentimentList =new ArrayList<String>();
		ArrayList<String> resources = new ArrayList<String>();

		sentimentList = getSentiments(text);
		for(String str: sentimentList) {
			if(str.equalsIgnoreCase(NEGATIVE) || str.equalsIgnoreCase("very negative")) {
				negativeCounter++;
			}
			else if(str.equalsIgnoreCase(POSITIVE) || str.equalsIgnoreCase("very positive")) {
				positiveCounter++;
			}
			else
				neutralCounter++;
		}
		if(negativeCounter >= positiveCounter && negativeCounter >= neutralCounter) {
			resources.add("Negative");
			result = "negative";

		}
		else if(neutralCounter >= negativeCounter && neutralCounter>=positiveCounter) {
			resources.add("Neutral");
			result="Neutral";

		}
		else {
			resources.add("Positive");
			result="Positive";
		}

		resources.addAll(getListOfResources(result));
		return resources;
	}

	private ArrayList<String> getListOfResources(String result) {
		ArrayList<CSVDetail> csvDetailList = readCsvFile();
		ArrayList<String> resourceKeywords = new ArrayList<String>();
		for(CSVDetail c : csvDetailList) {
			System.out.println(c.getName());
			if(c.getName().equalsIgnoreCase(result)) {
				resourceKeywords.add(c.getResource());
				System.out.println(c.getResource());
			}
		}
		return resourceKeywords;
	}

	private ArrayList<CSVDetail> readCsvFile() {
		ArrayList<CSVDetail> csvDetail = new ArrayList<CSVDetail>();
		try (BufferedReader bufferedReader = new BufferedReader(
				new FileReader("./assets/sentiments.csv"))){
			String line;
			while((line = bufferedReader.readLine())!=null){
				String[] data = line.split(",");
				CSVDetail c = new CSVDetail();
				c.setName(data[0]);
				c.setResource(data[1]);

				csvDetail.add(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		}

		return csvDetail;
	}


	public ArrayList<String> getSentiments(String text) {
		CoreDocument coreDocument = new CoreDocument(text);
		stanfordCoreNLP.annotate(coreDocument);
		List<CoreSentence> sentences = coreDocument.sentences();
		ArrayList<String> sentimentList = new ArrayList<String>();
		for (CoreSentence sentence : sentences)
			sentimentList.add(sentence.sentiment());
		return sentimentList;

	}
}