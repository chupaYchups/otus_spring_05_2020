package ru.chupaYchups.question.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.chupaYchups.question.core.dao.QuestionsDao;
import ru.chupaYchups.question.core.model.Question;
import ru.chupaYchups.question.core.model.Student;

import java.util.Arrays;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование того что программа корректно работает (порог успешности 2) при результате теста : ")
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

    @Mock
    private QuestionsDao questionsDao;
    @Mock
    private PrintOutputService printOutputService;
    @Mock
    private InputService inputService;

    private TestingAttemptService testingAttemptService;
    private InOrder inOrder;

    @BeforeEach
    void setUp() {
        testingAttemptService = new TestingAttemptServiceImpl(questionsDao, printOutputService, inputService, QUANTITY_TO_SUCCESS);
        inOrder = Mockito.inOrder(questionsDao, printOutputService, inputService);
    }

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

        inOrder.verify(printOutputService, times(1)).
            print(String.format(TestingAttemptServiceImpl.HELLO_USER_NAME_REQUEST,
                    student.getUserName()));
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(questionsDao, times(1)).findAllQuestions();
        inOrder.verify(printOutputService, times(1)).print(testQuestion1.getQuestionString());
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(printOutputService, times(1)).print(testQuestion2.getQuestionString());
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(printOutputService, times(1)).print(testQuestion3.getQuestionString());
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(printOutputService, times(1)).
            print(String.format(qtyOfSuccess >= QUANTITY_TO_SUCCESS ?
                    TestingAttemptServiceImpl.SUCCESS_RESULT_PATTERN :
                    TestingAttemptServiceImpl.FAILED_RESULT_PATTERN,
                    student.getUserName(),
                    qtyOfSuccess + "/" + QUANTITY_OF_QUESTIONS));

    }
}