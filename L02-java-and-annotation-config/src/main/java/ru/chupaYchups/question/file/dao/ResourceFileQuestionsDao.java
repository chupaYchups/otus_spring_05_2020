package ru.chupaYchups.question.file.dao;

import org.springframework.stereotype.Repository;
import ru.chupaYchups.question.core.dao.QuestionsDao;
import ru.chupaYchups.question.core.model.Question;
import ru.chupaYchups.question.file.service.ResourceFileScannerService;

import java.util.*;

@Repository
public class ResourceFileQuestionsDao implements QuestionsDao {

    private final ResourceFileScannerService resourceFileScannerService;
    private final QuestionConverterService converterService;

    public ResourceFileQuestionsDao(ResourceFileScannerService resourceFileScannerService, QuestionConverterService converterService) {
        this.resourceFileScannerService = resourceFileScannerService;
        this.converterService = converterService;
    }

    @Override
    public List<Question> findAllQuestion() {
        List<Question> questionList = new ArrayList<>();
        Scanner scanner = resourceFileScannerService.getScanner();
        while(scanner.hasNextLine()){
            questionList.add(converterService.covertToQuestion(scanner.nextLine()));
        }
        return questionList;
    }
}
