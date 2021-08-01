package com.stackroute.questionnaireservice.service;

import com.stackroute.questionnaireservice.exception.*;
import com.stackroute.questionnaireservice.model.Question;
import com.stackroute.questionnaireservice.model.ScoreBelt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QuestionnaireService {
    List<Question> getAllQuestions() throws QuestionsNotFoundException;
    List<ScoreBelt> getScoreBelts(HashMap<Integer, String> selectedAnsweredMap, String email, String plan) throws AsweredOptionsMapNotExistsException, ScoreBeltsNotExistsException, UserEmailIsNotFoundException, NegativePercentageValueException;
}

