package QuestionConstructor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLParser {

    public static void parseFromXMLFile(String nameOfFile, List<QuizQuestion> questions) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(nameOfFile));
            document.getDocumentElement().normalize();
            NodeList questionList = document.getElementsByTagName("quizquestion");

            for (int i = 0; i < questionList.getLength(); i++) {
                Node quizQuestion = questionList.item(i);

                if (quizQuestion.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList quizQuestionDetails = quizQuestion.getChildNodes();
                    QuizQuestion newQuizQuestion = new QuizQuestion();

                    for (int j = 0; j < quizQuestionDetails.getLength(); j++) {
                        Node detail = quizQuestionDetails.item(j);

                        if (detail.getNodeType() == Node.ELEMENT_NODE) {
                            Element detailElement = (Element) detail;

                            if (detailElement.getTagName().equals("question")) {
                                newQuizQuestion.setQuestionText(detailElement.getTextContent());
                            } else if (detailElement.getTagName().equals("option")) {
                                newQuizQuestion.addOption(new QuizOption(detailElement.getTextContent(),
                                        detailElement.getAttribute("isTrue")));
                            }
                        }
                    }
                    questions.add(newQuizQuestion);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}
