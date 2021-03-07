package QuestionConstructor;

public class QuizOption {
    private String textOption;
    private boolean isRightOption;

    public QuizOption(String textOption, boolean isRightOption) {
        this.textOption = textOption;
        this.isRightOption = isRightOption;
    }

    public QuizOption(String textOption, String booleanText) {
        this.textOption = textOption;
        if (booleanText.isEmpty()) {
            throw new IllegalArgumentException("BooleanText should not be empty!");
        }
        switch (booleanText.toLowerCase()) {
            case "true":
                this.isRightOption = true;
                break;
            case "false":
                this.isRightOption = false;
                break;
            default:
                break;
        }
    }

    public String getTextOption() {
        return textOption;
    }

    public void setTextOption(String textOption) {
        this.textOption = textOption;
    }

    public boolean isRightOption() {
        return isRightOption;
    }

    public void setRightOption(boolean rightOption) {
        isRightOption = rightOption;
    }
}
