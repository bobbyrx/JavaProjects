package QuestionConstructor;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion {
    private String questionText;
    private List<QuizOption> optionList;

    public QuizQuestion(String questionTest, List<QuizOption> optionList) {
        this.questionText = questionTest;
        this.optionList = optionList;
    }

    public void addOption(QuizOption option) {
        if (option == null) {
            throw new IllegalArgumentException("Option is null!");
        }
        this.optionList.add(option);
    }

    public QuizQuestion() {
        this.optionList = new ArrayList<>();
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<QuizOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<QuizOption> optionList) {
        this.optionList = optionList;
    }

    public void printQuestion() {
        System.out.println(this.questionText);
        this.optionList.
                forEach(n -> System.out.println(n.getTextOption() + " is " + n.isRightOption()));
        System.out.println();
    }

}
