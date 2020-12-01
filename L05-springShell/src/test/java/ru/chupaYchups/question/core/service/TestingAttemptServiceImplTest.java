package ru.chupaYchups.question.core.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.chupaYchups.question.dao.QuestionsDao;
import ru.chupaYchups.question.model.Question;
import ru.chupaYchups.question.model.Student;
import ru.chupaYchups.question.service.*;
import java.util.Arrays;
import static org.mockito.Mockito.times;


@SpringBootTest
@DisplayName("Тестирование того что сервис тестирования корректно отрабатывает (порог успешности 2)")
class TestingAttemptServiceImplTest {

    private final static String TEST_QUESTION_STRING_1 = "How many fingers do you have";
    private final static String TEST_QUESTION_ANSWER_1 = "20";
    private final static String TEST_QUESTION_STRING_2 = "What is the capital of Russia";
    private final static String TEST_QUESTION_STRING_3 = "What is my name";

    private final static String TEST_QUESTION_ANSWER_3_CORRECT = "Ivan";
    private final static String TEST_QUESTION_ANSWER_2_CORRECT = "Moscow";
    private final static String TEST_QUESTION_ANSWER_2_INCORRECT = "London";
    private final static String TEST_QUESTION_ANSWER_3_INCORRECT = "Vovan";

    private final Question testQuestion1 = new Question(TEST_QUESTION_STRING_1, TEST_QUESTION_ANSWER_1);
    private final Question testQuestion2 = new Question(TEST_QUESTION_STRING_2, TEST_QUESTION_ANSWER_2_CORRECT);
    private final Question testQuestion3 = new Question(TEST_QUESTION_STRING_3, TEST_QUESTION_ANSWER_3_CORRECT);

    private static final int QUANTITY_TO_SUCCESS = 2;
    private static final int QUANTITY_OF_QUESTIONS = 3;

    @MockBean
    private QuestionsDao questionsDao;
    @MockBean
    private QuestionOutputService questionOutputService;
    @MockBean
    private InputService inputService;

    @Autowired
    private TestingAttemptService testingAttemptService;

    private InOrder inOrder;

    @DisplayName("При результате теста : ")
    @ParameterizedTest(name="{2} правильно из " + QUANTITY_OF_QUESTIONS)
    @CsvSource({TEST_QUESTION_ANSWER_2_CORRECT + "," + TEST_QUESTION_ANSWER_3_CORRECT + ",3",
        TEST_QUESTION_ANSWER_2_INCORRECT + "," + TEST_QUESTION_ANSWER_3_INCORRECT + ",1",
        TEST_QUESTION_ANSWER_2_CORRECT + "," + TEST_QUESTION_ANSWER_3_INCORRECT + ",2"})
    void thatTestingCorrectWorks(String question2Answer, String question3Answer, int qtyOfSuccess) {

        Student student = new Student("testStudent");

        BDDMockito.given(inputService.getInput()).
            willReturn(student.getUserName()).
            willReturn(TEST_QUESTION_ANSWER_1).
            willReturn(question2Answer).
            willReturn(question3Answer);

        BDDMockito.given(questionsDao.findAllQuestions()).willReturn(Arrays.asList(testQuestion1, testQuestion2, testQuestion3));

        testingAttemptService.doTestingAttempt();

        inOrder = Mockito.inOrder(questionsDao, questionOutputService, inputService);
        inOrder.verify(questionOutputService, times(1)).
            printLocalized(TestingAttemptServiceImpl.HELLO_USER_MSG);
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(questionsDao, times(1)).findAllQuestions();
        inOrder.verify(questionOutputService, times(1)).print(testQuestion1.getQuestionString());
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(questionOutputService, times(1)).print(testQuestion2.getQuestionString());
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(questionOutputService, times(1)).print(testQuestion3.getQuestionString());
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(questionOutputService, times(1)).
            printLocalized(qtyOfSuccess >= QUANTITY_TO_SUCCESS ?
                TestingAttemptServiceImpl.SUCCESS_RESULT_MSG :
                TestingAttemptServiceImpl.FAILED_RESULT_MSG,
                student.getUserName(),
                qtyOfSuccess + "/" + QUANTITY_OF_QUESTIONS);
    }
}