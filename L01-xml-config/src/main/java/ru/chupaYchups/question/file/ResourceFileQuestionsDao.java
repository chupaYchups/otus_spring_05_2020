package ru.chupaYchups.question.file;

import ru.chupaYchups.question.Question;
import ru.chupaYchups.question.core.dao.QuestionsDao;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class ResourceFileQuestionsDao implements QuestionsDao {

    private Queue<Question> questionQueue;

    public ResourceFileQuestionsDao(String fileName) {
        questionQueue = new ArrayDeque<>();
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName))) {
            while(scanner.hasNextLine()){
                String[] questionAndAnswer = scanner.nextLine().split(";");
                questionQueue.add(new Question(questionAndAnswer[0],  questionAndAnswer[1]));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !questionQueue.isEmpty();
    }

    @Override
    public Question next() {
        return questionQueue.poll();
    }
}
