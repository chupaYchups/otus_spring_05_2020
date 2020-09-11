package ru.chupaYchups.question.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class TestingAttemptServiceImplTest {

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
        int quantity_to_succes = 2;
        testingAttemptService = new TestingAttemptServiceImpl(questionsDao, printOutputService, inputService, quantity_to_succes);
        inOrder = Mockito.inOrder(questionsDao, printOutputService, inputService);
    }

    @Test
    void doTestingAttempt() {
        final String TEST_QUESTION_STRING_1 = "How many fingers do you have";
        final String TEST_QUESTION_ANSWER_1 = "20";
        final String TEST_QUESTION_STRING_2 = "What is the capital of Russia";
        final String TEST_QUESTION_ANSWER_2 = "Moscow";
        final String TEST_QUESTION_STRING_3 = "What is my name";
        final String TEST_QUESTION_ANSWER_3 = "Ivan";

        Student student = new Student("testStudent");
        Question testQuestion1 = new Question(TEST_QUESTION_STRING_1, TEST_QUESTION_ANSWER_1);
        Question testQuestion2 = new Question(TEST_QUESTION_STRING_2, TEST_QUESTION_ANSWER_2);
        Question testQuestion3 = new Question(TEST_QUESTION_STRING_3, TEST_QUESTION_ANSWER_3);

        BDDMockito.given(inputService.getInput()).
            willReturn(student.getUserName()).
            willReturn(TEST_QUESTION_ANSWER_1).
            willReturn(TEST_QUESTION_ANSWER_2).
            willReturn(TEST_QUESTION_ANSWER_3);

        BDDMockito.given(questionsDao.findAllQuestion()).willReturn(Arrays.asList(testQuestion1, testQuestion2, testQuestion3));

        testingAttemptService.doTestingAttempt();

        inOrder.verify(printOutputService, times(1)).print(String.format(TestingAttemptServiceImpl.HELLO_USER_NAME_REQUEST, student.getUserName()));
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(questionsDao, times(1)).findAllQuestion();
        inOrder.verify(printOutputService, times(1)).print(testQuestion1.getQuestionString());
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(printOutputService, times(1)).print(testQuestion2.getQuestionString());
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(printOutputService, times(1)).print(testQuestion3.getQuestionString());
        inOrder.verify(inputService, times(1)).getInput();
        inOrder.verify(printOutputService, times(1)).print(String.format(TestingAttemptServiceImpl.SUCCESS_RESULT_PATTERN, student.getUserName(), "3/3"));

    }
}