package ru.chupaYchups.question;

import ru.chupaYchups.question.core.dao.QuestionsDao;
import ru.chupaYchups.question.core.service.QuestionInputService;
import ru.chupaYchups.question.core.service.QuestionManagerService;
import ru.chupaYchups.question.core.service.QuestionOutputService;

public class QuestionManagerServiceImpl implements QuestionManagerService {

    private QuestionsDao dao;
    private QuestionOutputService questionOutputService;
    private QuestionInputService questionInputService;

    public QuestionManagerServiceImpl(QuestionsDao questionsDao, QuestionOutputService printerService, QuestionInputService inputService) {
        this.dao = questionsDao;
        this.questionOutputService = printerService;
        this.questionInputService = inputService;
    }

    @Override
    public void printAllQuestions() {
        while (dao.hasNext()){
            questionOutputService.printQuestion(dao.next());
        }
    }

    @Override
    public void answerTheQuestions(final int qtyToSuccess) {
        int sucessCounter = 0;
        boolean testResultOk = false;
        while (!testResultOk && dao.hasNext()) {
            Question question = dao.next();
            questionOutputService.printQuestion(question);
            String answer = questionInputService.getInput();
            if (question.checkAnswer(answer)) {
                sucessCounter++;
                if (sucessCounter >= qtyToSuccess) {
                    testResultOk = true;
                }
            }
        }
        questionOutputService.printTestResult(testResultOk);
    }
}
