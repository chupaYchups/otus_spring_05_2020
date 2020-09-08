package ru.chupaYchups.question.file;

import ru.chupaYchups.question.Question;
import ru.chupaYchups.question.core.dao.QuestionsDao;
import ru.chupaYchups.question.core.service.QuestionConverterService;
import ru.chupaYchups.question.core.exception.QuestionException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class ResourceFileQuestionsDao implements QuestionsDao {

    private Queue<Question> questionQueue;

    private ResourceFileScannerService resourceFileScannerService;

    private QuestionConverterService converterService;

    public ResourceFileQuestionsDao(ResourceFileScannerService resourceFileScannerService, QuestionConverterService converterService) {
        this.resourceFileScannerService = resourceFileScannerService;
        this.converterService = converterService;
    }

    @Override
    public boolean hasNext() {
        if (questionQueue == null) {
            getQuestionsFromFile();
        }
        return !questionQueue.isEmpty();
    }

    @Override
    public Question next() {
        if (questionQueue == null) {
            throw new QuestionException("Question queue not initialized, try to use hasNext method first");
        }
        return questionQueue.poll();
    }

    private void getQuestionsFromFile() {
        questionQueue = new ArrayDeque<>();
        Scanner scanner = resourceFileScannerService.getScanner();
        while(scanner.hasNextLine()){
            String[] questionAndAnswer = scanner.nextLine().split(";");
            questionQueue.add(converterService.covertToQuestion(questionAndAnswer[0],  questionAndAnswer[1]));
        }
    }
}
