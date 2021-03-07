package QuizGameController;

import QuestionConstructor.QuizOption;
import QuestionConstructor.QuizQuestion;
import QuestionConstructor.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuizGame implements ActionListener {

    private final List<QuizQuestion> questionList;
    private int index;
    private int countCorrectGuesses;
    private final int questionsLength;

    private final JFrame frame = new JFrame();
    private final JTextField textField = new JTextField();
    private final JTextArea textArea = new JTextArea();
    private final List<JButton> answerButton = new ArrayList<>();
    private final List<JLabel> answerLabel = new ArrayList<>();
    private final JButton nextQuestionButton = new JButton();
    private final JTextField numberRight = new JTextField();
    private final JTextField percentage = new JTextField();

    private void createButtonsAndLabels(QuizQuestion question) {
        char letter = 'A';
        int yPosition = 170;
        for (QuizOption option : question.getOptionList()) {
            JButton button = new JButton();
            button.setBounds(10, yPosition, 75, 75);
            button.setFont(new Font("Times New Roman", Font.BOLD, 24));
            button.setFocusable(false);
            button.addActionListener(this);
            button.setText("" + letter);
            button.setBackground(new Color(230, 255, 255));
            answerButton.add(button);

            JLabel label = new JLabel();
            label.setBounds(100, yPosition, 1050, 75);
            label.setBackground(new Color(50, 50, 50));
            label.setForeground(new Color(215, 255, 255));
            label.setFont(new Font("Times New Roman", Font.PLAIN, 24));
            label.setText(option.getTextOption());
            answerLabel.add(label);

            letter++;
            yPosition += 85;
        }
    }

    public QuizGame(String nameOfFile) {
        this.questionList = new ArrayList<>();
        XMLParser.parseFromXMLFile(nameOfFile, this.questionList);
        questionsLength = questionList.size();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 700);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(0, 0, 1366, 50);
        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(215, 255, 255));
        textField.setFont(new Font("Times New Roman", Font.BOLD, 35));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setText("Въпрос 1");

        textArea.setBounds(0, 60, 1366, 100);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Times New Roman", Font.BOLD, 24));
        textArea.setBackground(new Color(25, 25, 25));
        textArea.setForeground(new Color(215, 255, 255));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);
        textArea.setText(questionList.get(0).getQuestionText());

        createButtonsAndLabels(questionList.get(0));

        nextQuestionButton.setBounds(1065, 575, 250, 50);
        nextQuestionButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nextQuestionButton.setFocusable(false);
        nextQuestionButton.addActionListener(this);
        nextQuestionButton.setText("Следващ въпрос");
        nextQuestionButton.setBackground(new Color(230, 255, 255));
        nextQuestionButton.setEnabled(false);

        numberRight.setBounds(580, 205, 200, 100);
        numberRight.setBackground(new Color(25, 25, 25));
        numberRight.setForeground(new Color(215, 255, 255));
        numberRight.setFont(new Font("Times New Roman", Font.BOLD, 50));
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setEditable(false);

        percentage.setBounds(580, 275, 200, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(215, 255, 255));
        percentage.setFont(new Font("Times New Roman", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        answerLabel.forEach(frame::add);
        answerButton.forEach(frame::add);
        frame.add(nextQuestionButton);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);
    }

    public List<QuizQuestion> getQuestionList() {
        return questionList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextQuestionButton) {
            answerButton.forEach(button -> button.setEnabled(true));
            nextQuestionButton.setEnabled(false);
            nextQuestion();
        } else {
            answerButton.forEach(button -> button.setEnabled(false));
            nextQuestionButton.setEnabled(true);
            for (int i = 0; i < answerButton.size(); i++) {
                if (e.getSource() == answerButton.get(i)) {
                    if (questionList.get(index).getOptionList().
                            get(i).isRightOption()) {
                        countCorrectGuesses++;
                    }
                    break;
                }
            }
            displayAnswer();
        }
    }

    public void displayAnswer() {
        for (int i = 0; i < answerLabel.size(); i++) {
            if (questionList.get(index).getOptionList().
                    get(i).isRightOption()) {
                answerLabel.get(i).setForeground(new Color(0, 255, 0));
            } else {
                answerLabel.get(i).setForeground(new Color(255, 0, 0));
            }
        }
    }

    public void nextQuestion() {
        index++;
        if (index >= questionsLength) {
            results();
        } else {
            textField.setText("Въпрос " + (index + 1) + " от " + questionsLength);
            textArea.setText(this.questionList.get(index).getQuestionText());
            for (int i = 0; i < answerLabel.size(); i++) {
                answerLabel.get(i).setText(questionList.get(index).
                        getOptionList().get(i).getTextOption());
                answerLabel.get(i).setForeground(new Color(215, 255, 255));
            }
        }
    }

    public void results() {
        answerButton.forEach(button -> button.setEnabled(false));
        nextQuestionButton.setEnabled(false);
        answerLabel.forEach(label -> label.setText(""));
        textArea.setText("");

        int result = (int) ((countCorrectGuesses / (double) questionsLength) * 100);
        textField.setText("КРАЕН РЕЗУЛТАТ!");
        numberRight.setText("(" + countCorrectGuesses + "/" + questionsLength + ")");
        percentage.setText(result + "%");

        frame.add(percentage);
        frame.add(numberRight);
    }
}
