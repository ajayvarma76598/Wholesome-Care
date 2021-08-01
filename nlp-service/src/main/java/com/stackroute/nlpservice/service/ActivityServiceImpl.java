package com.stackroute.nlpservice.service;

import com.stackroute.nlpservice.exception.*;
import com.stackroute.nlpservice.model.Activity;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//creating a service class implements ActivityService
@Service
public class ActivityServiceImpl implements ActivityService{
    //activity data set csv file path
    /*@Value("${activity.csv.filepath}")
    private String csvFilePath;*/
    private StanfordCoreNLP stanfordCoreNLP;
    @Autowired
    public ActivityServiceImpl(StanfordCoreNLP stanfordCoreNLP){
     this.stanfordCoreNLP = stanfordCoreNLP;
    }
    //create a method that takes activityDetails of user in String then tokenize it using stanfordCoreNLP
    //return list of coreLabels
    public List<CoreLabel> getCoreLables(String activityDetail){
        CoreDocument coreDocument = new CoreDocument(activityDetail);
        stanfordCoreNLP.annotate(coreDocument);
        return coreDocument.tokens();
    }
    //create a method that takes activityDetails of user in String
    //then extract the time duration number
    //return list of time duration numbers
    public List<Double> getListOfDurationInteger(String activityDetail){
        List<CoreLabel> coreLabels = getCoreLables(activityDetail);
        return coreLabels.stream().filter(coreLabel -> coreLabel.get(CoreAnnotations
                .PartOfSpeechAnnotation.class).equals("CD"))
                .map(coreLabel->Double.parseDouble(coreLabel.originalText().toString()))
                .collect(Collectors.toList());
    }
    //create a method that takes activityDetails of user in String
    //then extract the time unit
    //return list of time unit
    public List<String> getListOfDurationTime(String activityDetail){
        List<CoreLabel> coreLabels = getCoreLables(activityDetail);
        List<String> enumTime = Arrays.asList(DurationTime.values())
                .stream().map(enumActivity->enumActivity.toString().toLowerCase())
                .collect(Collectors.toList());
        List<String> coreLabelTime = coreLabels.stream()
                .map(coreLabel->coreLabel.originalText().toLowerCase())
                .collect(Collectors.toList());
        coreLabelTime.retainAll(enumTime);
        return coreLabelTime;
    }
    //create a method that takes activityDetails of user in String
    //then extract the activity name by reading the csv file
    //return list of Activity entity class
    public List<Activity> getListOfActivities(String activityDetail) throws NoTimeUnitGivenException, NoTimeGivenException, NoTimeUnitGivenException{
        //taking the list of corelabels that user provides string(tokenize the string)
        List<CoreLabel> coreLabels = getCoreLables(activityDetail);
        List<Activity> activityList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("./assets/activitiesCalories.csv"))){
            bufferedReader.readLine();
            String line;
            while((line = bufferedReader.readLine())!=null){
                String[] data = line.split(",");
                //add the csv data in activityList
                activityList.add(new Activity(data[0], Double.parseDouble(data[1])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        //create a list of string from list of corelabels
        List<String> coreLabelsActivities = coreLabels.stream()
                .map(coreLabel->coreLabel.originalText().toLowerCase()).collect(Collectors.toList());
        //create a list of all activities that are in csv class
        List<String> activityCsvStringList = activityList.stream()
                .map(activityCsv -> activityCsv.getActivity()).collect(Collectors.toList());
        //create a list of common activity from csv activity list and user provide activity
        List<Activity> result = activityList.stream()
                .distinct()
                .filter(a->coreLabelsActivities.contains(a.getActivity()))
                .collect(Collectors.toList());

        //if user not provide any time duration number then throw exception
        List<Double> listOfDurationInteger = getListOfDurationInteger(activityDetail);
        if(listOfDurationInteger.isEmpty()){
            throw new NoTimeGivenException();
        }
        //if user not provide any time unit then throw exception
        List<String> listOfDurationTime = getListOfDurationTime(activityDetail);
        if(listOfDurationTime.isEmpty()){
            throw new NoTimeUnitGivenException();
        }
        //if no activiy match that user provides then throw exception
        coreLabelsActivities.retainAll(activityCsvStringList);
        if(coreLabelsActivities.isEmpty()){
            throw new NoActivityMatchWithDatSetException();
        }
        for(int i=0; i<result.size(); i++){
            result.get(i).setActivity(coreLabelsActivities.get(i));
            result.get(i).setDuration(listOfDurationInteger.get(i));
            result.get(i).setTimeDuration(listOfDurationTime.get(i));
        }
        return result;
    }
    //create a method that takes activityDetails of user in String
    //then set the calorieBurnt in list of Activity
    //by calculating total calorieBurnt by reading csv file
    //return list of Activity entity class
    @Override
    public List<Activity> getCalorieBurnt(String activityDetail) throws NoTimeUnitGivenException, NoTimeGivenException, NoActivityMatchWithDatSetException{
        List<Activity> listOfActivities = getListOfActivities(activityDetail);
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("./assets/activitiesCalories.csv"))){
            bufferedReader.readLine();
            String line;
            List<Activity> activities = new ArrayList<>();
            while((line = bufferedReader.readLine())!=null){
                String[] data = line.split(",");
                    Activity dataMatch = listOfActivities.stream().filter(d->d.getActivity().equalsIgnoreCase(data[0])).findFirst().orElse(null);
                    //matching the suitable time unit, calculate the calorie burnt and set in list of Activity
                    if(dataMatch!=null){
                        if(dataMatch.getTimeDuration().equalsIgnoreCase("sec") || dataMatch.getTimeDuration().equalsIgnoreCase("second") || dataMatch.getTimeDuration().equalsIgnoreCase("seconds")){
                            dataMatch.setCalorieBurnt((Double.parseDouble(data[1])*dataMatch.getDuration())/60);
                        }else if(dataMatch.getTimeDuration().equalsIgnoreCase("min") || dataMatch.getTimeDuration().equalsIgnoreCase("minute") || dataMatch.getTimeDuration().equalsIgnoreCase("minutes")){
                            dataMatch.setCalorieBurnt(Double.parseDouble(data[1])*dataMatch.getDuration());
                        }else if(dataMatch.getTimeDuration().equalsIgnoreCase("hour") || dataMatch.getTimeDuration().equalsIgnoreCase("hr") || dataMatch.getTimeDuration().equalsIgnoreCase("hours")){
                            dataMatch.setCalorieBurnt(Double.parseDouble(data[1])*dataMatch.getDuration()*60);
                        }
                        activities.add(dataMatch);
                    }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return listOfActivities;
    }
}
