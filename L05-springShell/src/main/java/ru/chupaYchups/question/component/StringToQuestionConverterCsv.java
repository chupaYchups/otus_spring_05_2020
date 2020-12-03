package ru.chupaYchups.question.component;

import org.springframework.stereotype.Component;
import ru.chupaYchups.question.config.QuestionsFileProps;
import ru.chupaYchups.question.model.Question;

@Component
public class StringToQuestionConverterCsv implements StringToQuestionConverter {

    public final QuestionsFileProps questionsFileProps;

    public StringToQuestionConverterCsv(QuestionsFileProps questionsFileProps) {
        this.questionsFileProps = questionsFileProps;
    }

    @Override
    public Question convertToQuestion(String oneLineString) {
        String[] questionAndAnswer = oneLineString.split(questionsFileProps.getDelimiter());
        return new Question(questionAndAnswer[0], questionAndAnswer[1]);
    }
}
