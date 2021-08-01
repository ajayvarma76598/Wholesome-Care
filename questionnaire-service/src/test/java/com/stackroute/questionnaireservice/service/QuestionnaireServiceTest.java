package com.stackroute.questionnaireservice.service;

import com.stackroute.questionnaireservice.exception.*;
import com.stackroute.questionnaireservice.model.OptionPercentage;
import com.stackroute.questionnaireservice.model.Question;
import com.stackroute.questionnaireservice.model.ScoreBelt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
@ExtendWith(MockitoExtension.class)
public class QuestionnaireServiceTest {
    @Mock
    QuestionnaireServiceImpl questionnaireService;
    QuestionnaireServiceImpl questionnaireService1;
    private List<Question> questions;
    List<OptionPercentage> optionPercentages;
    HashMap<Integer, String> selectedAnsweredMap;

    @BeforeEach
    public void setUp() throws Exception {
        questionnaireService1 = new QuestionnaireServiceImpl();
        questions = new ArrayList<>();
        questions.add(new Question(1, "Physical Wellness", "\"I generally feel very good about my physical health (1 being strongly disagree to 5 being strongly agree)\"", new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "0"))));
        questions.add(new Question(2, "Physical Wellness", "\"I exercise at least three times a week (1 being strongly disagree to 5 being strongly agree)\"", new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "0"))));
        optionPercentages = new ArrayList<>();
        optionPercentages.add(new OptionPercentage(1, "2", 40));
        optionPercentages.add(new OptionPercentage(1, "3", 60));
        selectedAnsweredMap = new HashMap<>();
        selectedAnsweredMap.put(1, "2");
    }

    @Test
    public void getAllQuestionsSuccess() throws QuestionsNotFoundException {
        doReturn(questions).when(questionnaireService).getAllQuestions();
        List<Question> questionList = questionnaireService.getAllQuestions();
        Assertions.assertEquals(questions, questionList);
        verify(questionnaireService, times(1)).getAllQuestions();
    }

    @Test
    public void readCSVGettingListOptionPercentagesSuccess() {
        doReturn(optionPercentages).when(questionnaireService).readCSVGettingListOptionPercentages();
        List<OptionPercentage> optionPercentageList = questionnaireService.readCSVGettingListOptionPercentages();
        Assertions.assertEquals(40, optionPercentageList.get(0).getPercentage());
        verify(questionnaireService, times(1)).readCSVGettingListOptionPercentages();
    }

    @Test
    public void getListFinalOptions() throws AsweredOptionsMapNotExistsException {
        List<OptionPercentage> finaloption = optionPercentages.stream().filter(option -> option.getOptions().equalsIgnoreCase("2")).collect(Collectors.toList());
        doReturn(finaloption).when(questionnaireService).getListFinalOptions(selectedAnsweredMap);
        List<OptionPercentage> optionPercentageList = questionnaireService.getListFinalOptions(selectedAnsweredMap);
        Assertions.assertEquals(finaloption, optionPercentageList);
        verify(questionnaireService, times(1)).getListFinalOptions(selectedAnsweredMap);
    }

//    @Test
//    public void getListFinalOptionsFailure() {
//        HashMap<Integer, String> map = null;
//        Assertions.assertThrows(AsweredOptionsMapNotExistsException.class, () -> {
//            questionnaireService1.getListFinalOptions(map);
//        });
//    }

    @Test
    public void getColourBeltByPercentageSuccess() throws NegativePercentageValueException {
        doReturn("Violet").when(questionnaireService).getColourBeltByPercentage(65.0);
        String colour = questionnaireService.getColourBeltByPercentage(65.0);
        Assertions.assertEquals("Violet", colour);
        verify(questionnaireService, times(1)).getColourBeltByPercentage(65.0);
    }

    @Test
    public void getColourBeltByPercentageFailure() {
        Double percentage = -2.0;
        Assertions.assertThrows(NegativePercentageValueException.class, () -> {
            questionnaireService1.getColourBeltByPercentage(percentage);
        });
    }

    @Test
    public void getScoreBeltsSuccess() throws AsweredOptionsMapNotExistsException, NegativePercentageValueException, ScoreBeltsNotExistsException, UserEmailIsNotFoundException {
        List<ScoreBelt> scoreBelts = new ArrayList<>();
        scoreBelts.add(new ScoreBelt("Physical Wellness", 55.0, "Violet"));
        scoreBelts.add(new ScoreBelt("Mental Wellness", 76.0, "Brown"));
        scoreBelts.add(new ScoreBelt("Diet", 45.0, "Orange"));
        doReturn(scoreBelts).when(questionnaireService).getScoreBelts(selectedAnsweredMap, "a@gmail.com", "gold");
        List<ScoreBelt> scoreBeltList = questionnaireService.getScoreBelts(selectedAnsweredMap, "a@gmail.com", "gold");
        Assertions.assertEquals(scoreBelts, scoreBeltList);
        verify(questionnaireService, times(1)).getScoreBelts(selectedAnsweredMap, "a@gmail.com", "gold");
    }
    @Test
    public void getScoreBeltsFailure() {
        String email = "";
        Assertions.assertThrows(UserEmailIsNotFoundException.class, () -> {
            questionnaireService1.getScoreBelts(selectedAnsweredMap, email, "gold");
        });
    }
}