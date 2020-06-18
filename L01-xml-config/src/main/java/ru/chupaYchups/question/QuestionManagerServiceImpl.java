package ru.chupaYchups.question;

import ru.chupaYchups.question.core.dao.QuestionsDao;
import ru.chupaYchups.question.core.service.QuestionManagerService;
import ru.chupaYchups.question.core.service.QuestionPrinterService;

public class QuestionManagerServiceImpl implements QuestionManagerService {

    private QuestionsDao dao;

    private QuestionPrinterService printerService;

    public QuestionManagerServiceImpl(QuestionsDao questionsDao, QuestionPrinterService printerService) {
        this.dao = questionsDao;
        this.printerService = printerService;
    }

    @Override
    public void printAllQuestions() {
        while (dao.hasNext()){
            printerService.printQuestion(dao.next());
        }
    }

    @Override
    public void answerTheQuestions() {
    }
}
