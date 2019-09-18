package edu.mum.cs.lab.repository;

import edu.mum.cs.lab.domain.Answer;
import edu.mum.cs.lab.domain.Question;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz implements Serializable {

    private List<Answer> answerList() {
        Answer[] array = {
                new Answer(1, 9),
                new Answer(2, 8),
                new Answer(3, 36),
                new Answer(4, 13),
                new Answer(5, 32)
        };
        return Arrays.asList(array);
    }

    private Answer findAnswerById(int id) {
        return answerList().stream()
                .filter(ans -> ans.getId() == id)
                .findAny()
                .orElse(null);
    }

    public List<Question> questionList(boolean newQuiz) {
        Question[] array = {
                new Question(1, "3, 1, 4, 1, 5", findAnswerById(1)),
                new Question(2, "1, 1, 2, 3, 5", findAnswerById(2)),
                new Question(3, "1, 4, 9, 16, 25", findAnswerById(3)),
                new Question(4, "2, 3, 5, 7, 11", findAnswerById(4)),
                new Question(5, "1, 2, 4, 8, 16", findAnswerById(5))
        };
        List<Question> questionList = Arrays.asList(array);
        if(newQuiz) {
            Collections.shuffle(questionList);
        }
        return questionList;
    }

    public Question findQuestionByIndex(List<Question> questionList, int index){
        return questionList.get(index);
    }

}
