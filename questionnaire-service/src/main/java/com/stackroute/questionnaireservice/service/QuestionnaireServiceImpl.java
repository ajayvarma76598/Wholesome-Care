package com.stackroute.questionnaireservice.service;

import com.stackroute.questionnaireservice.exception.*;
import com.stackroute.questionnaireservice.model.OptionPercentage;
import com.stackroute.questionnaireservice.model.Question;
import com.stackroute.questionnaireservice.model.ScoreBelt;
import com.stackroute.questionnaireservice.model.ScoreBeltsWithID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{
    private RabbitMQSender rabbitMQSender;
    /*@Value("${questionnaire.csv.filepath.questionnaires}")
    private String csvFilePathQuestions;
    @Value("${questionnaire.csv.filepath.optionsPercentage}")
    private String csvFilePathOptionsPercentage;*/
    public QuestionnaireServiceImpl(){}
    @Autowired
    public QuestionnaireServiceImpl(RabbitMQSender rabbitMQSender){
        this.rabbitMQSender = rabbitMQSender;
    }
    @Override
    public List<Question> getAllQuestions() throws QuestionsNotFoundException {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./assets/questionnaires.csv"))){
            bufferedReader.readLine();
            String line;
            while((line = bufferedReader.readLine())!=null){
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
                questions.add(new Question(Integer.parseInt(data[0]), data[1], data[2], new ArrayList<String>( Arrays.asList(data[3], data[4], data[5], data[6], data[7], data[8]))));
            }
            if(questions==null || questions.isEmpty()){
                throw new QuestionsNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }
    public List<OptionPercentage> readCSVGettingListOptionPercentages(){
        List<OptionPercentage> optionPercentages = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./assets/optionsPercentage.csv"))) {
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
                optionPercentages.add(new OptionPercentage(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2])));
            }
            return optionPercentages;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<OptionPercentage> getListFinalOptions(HashMap<Integer, String> selectedAnsweredMap) throws AsweredOptionsMapNotExistsException {
        List<OptionPercentage> optionPercentages = readCSVGettingListOptionPercentages();
        int questionId=0;
        int questionTwo = 0;
        List <OptionPercentage> finaloption =new ArrayList<OptionPercentage>();
        if(selectedAnsweredMap==null){
            throw new AsweredOptionsMapNotExistsException();
        }
        for(int i=0;i< optionPercentages.size();i++){
            String option=selectedAnsweredMap.get(questionId);
            String optionCSV=optionPercentages.get(i).getOptions();
            int optionCSVquestionID=optionPercentages.get(i).getQuestionId();
            if(optionCSVquestionID == questionId){
                if(optionCSV.equalsIgnoreCase(option)) {
                    finaloption.add(optionPercentages.get(i));
                    questionId++;
                }
//            if(optionCSVquestionID == questionId && questionId!=2){
//                if(optionCSV.equalsIgnoreCase(option)) {
//                    finaloption.add(optionPercentages.get(i));
//                    questionId++;
//                }
//            } else if(optionCSVquestionID==2 && questionId==2){
//
//                String[] data = option.split(", ");
//                for(int j=0; j< data.length; j++){
//                    if(data[j].equalsIgnoreCase(optionCSV)){
//                        finaloption.add(optionPercentages.get(i));
//                    }
//                }
//                if(questionTwo==5){
//                    questionId++;
//                }
//                questionTwo++;
            }
            if(questionId > 19)
                break;
        }
        return finaloption;
    }
    public String getColourBeltByPercentage(Double percentage) throws NegativePercentageValueException {
        if(percentage<0){
            throw new NegativePercentageValueException();
        }
        if(percentage>=0 && percentage<25){
            return "Red";
        } else if(percentage>=25 && percentage<50){
            return "Yellow";
        } else if(percentage>=50 && percentage<75){
            return "Blue";
        } else if(percentage>=75 && percentage<90){
            return "Orange";
        }
        return "Green";
    }
    public Double getOverallPhysicalPercentage(HashMap<Integer, String> selectedAnsweredMap) throws AsweredOptionsMapNotExistsException {
        List<OptionPercentage> finalOption = getListFinalOptions(selectedAnsweredMap);
        long physicalTotal = finalOption.stream().filter(option->(option.getQuestionId()>=0 && option.getQuestionId()<=6))
                .count();
        int physicalTotalPercentage = finalOption.stream().filter(option->(option.getQuestionId()>=0 && option.getQuestionId()<=6))
                .map(option->option.getPercentage())
                .reduce(0,(percentage1, percentage2) -> percentage1 + percentage2);
        double overallPhysicalPercentage = (physicalTotalPercentage/physicalTotal);
        return overallPhysicalPercentage;
    }
    public Double getOverallMentalPercentage(HashMap<Integer, String> selectedAnsweredMap) throws AsweredOptionsMapNotExistsException {
        List<OptionPercentage> finalOption = getListFinalOptions(selectedAnsweredMap);
        int mentalTotalPercentage = finalOption.stream().filter(option->(option.getQuestionId()>=7 && option.getQuestionId()<=12))
                .map(option->option.getPercentage())
                .reduce(0,(percentage1, percentage2) -> percentage1 + percentage2);
        double overallMentalPercentage = (mentalTotalPercentage/6);
        return overallMentalPercentage;
    }
    public Double getOverallDietPercentage(HashMap<Integer, String> selectedAnsweredMap) throws AsweredOptionsMapNotExistsException {
        List<OptionPercentage> finalOption = getListFinalOptions(selectedAnsweredMap);
        int dietTotalPercentage = finalOption.stream().filter(option->(option.getQuestionId()>=13 && option.getQuestionId()<=18))
                .map(option->option.getPercentage())
                .reduce(0,(percentage1, percentage2) -> percentage1 + percentage2);
        double overallDietPercentage = (dietTotalPercentage/6);
        return overallDietPercentage;
    }
    @Override
    public List<ScoreBelt> getScoreBelts(HashMap<Integer, String> selectedAnsweredMap, String email, String plan) throws AsweredOptionsMapNotExistsException, ScoreBeltsNotExistsException, UserEmailIsNotFoundException, NegativePercentageValueException {
        List<ScoreBelt> scoreBelts = new ArrayList<>();
        if(email==null || email==""){
            throw new UserEmailIsNotFoundException();
        }
        Double overallPhysicalPercentage = getOverallPhysicalPercentage(selectedAnsweredMap);
        String finalPhysicalColourBelt = getColourBeltByPercentage(overallPhysicalPercentage);
        scoreBelts.add(new ScoreBelt("Physical Wellness", overallPhysicalPercentage, finalPhysicalColourBelt));
        Double overallMentalPercentage = getOverallMentalPercentage(selectedAnsweredMap);
        String finalMentalColourBelt = getColourBeltByPercentage(overallMentalPercentage);
        scoreBelts.add(new ScoreBelt("Mental Wellness", overallMentalPercentage, finalMentalColourBelt));
        Double overallDietPercentage = getOverallDietPercentage(selectedAnsweredMap);
        String finalDietColourBelt = getColourBeltByPercentage(overallDietPercentage);
        scoreBelts.add(new ScoreBelt("Diet", overallDietPercentage, finalDietColourBelt));
        if(scoreBelts==null || scoreBelts.isEmpty()){
            throw new ScoreBeltsNotExistsException();
        }
        ScoreBeltsWithID scoreBeltsWithID = new ScoreBeltsWithID(email, plan, scoreBelts);
        rabbitMQSender.send(scoreBeltsWithID);
        return scoreBelts;
    }
}
