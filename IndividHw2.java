import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

class ReadInput {
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[^a-zA-Z0-9 ?]");
    private static final int MAX_QUESTION_LENGTH = 100;

    public static boolean validateQuestionText(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Error: Question cannot be empty.");
            return false;
        }
        if (text.length() > MAX_QUESTION_LENGTH) {
            System.out.println("Error: Question exceeds max length of " + MAX_QUESTION_LENGTH + " characters.");
            return false;
        }
        if (SPECIAL_CHARACTERS.matcher(text).find()) {
            System.out.println("Error: Question contains special characters.");
            return false;
        }
        return true;
    }
}

class Question {
    private int id;
    private String text;
    
    public Question(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    @Override
    public String toString() {
        return "Question ID: " + id + ", Text: " + text;
    }
}

class QuestionCollection {
    private Map<Integer, Question> questions = new HashMap<>();
    public boolean addQuestion(int id, String text) {
        if (!ReadInput.validateQuestionText(text)) {
            return false;
        }
        if (questions.containsKey(id)) {
            System.out.println("Error: Question ID already exists.");
            return false;
        }
        questions.put(id, new Question(id, text));
        System.out.println("Success: Question added.");
        return true;
    }
    public Question getQuestionID(int id) {
        if (!questions.containsKey(id)) {
            System.out.println("Error: Question ID not found.");
            return null;
        }
        return questions.get(id);
    }
}

public class IndividHw2 {
    public static void main(String[] args) {
        QuestionCollection questionCollection = new QuestionCollection();

        //Test case for there being a question that is actually valid
        System.out.println("\nTest Case #1: Adding a valid question");
        questionCollection.addQuestion(1, "What is 2 + 2?");

        //Test case for actually getting the ID for the question above
        Question studentQuestion = questionCollection.getQuestionID(1);
        if (studentQuestion != null) {
            System.out.println(studentQuestion);
        }

        //Test case for trying to add a question with special characters
        System.out.println("\nTest Case #2: Add in a question that has special characters in it");
        questionCollection.addQuestion(2, "What is this here: @?");

        //test case for trying to add a question with the same ID as one that already exists
        System.out.println("\nTest Case #3: Add in a question with an already existing ID");
        questionCollection.addQuestion(1, "How do you solve 2 + 2?");

        //test case for trying to add in a question that doesn't have any actual content in it
        System.out.println("\nTest Case #4: Add in a question with no content");
        questionCollection.addQuestion(3, "");

        //test case for trying to write in a question that exceeds the character limit
        System.out.println("\nTestCase #6: Add in a question that exceeds the character limit");
        String longQuestion = "This is a question that should very easily exceed the character limit by effortlessly utilising long and useless words to prove that this test case  will work. " + "The programme should reject this via due to it exceeding the character limit length of 100.";
        questionCollection.addQuestion(4, longQuestion);

        //testcase for trying to retrive a question that just doesn't exist anywhere
        System.out.println("\nTestCase #7: Retrieve a question that does not exist");
        questionCollection.getQuestionID(99999);
    }
}

        /* 
        //The following will be to insert whatever question you like and display what is already stored
        System.out.println("\n Enter another question (yes/no)");
        String userResponse = scanner.nextLine().trim().toLowerCase();

        if(response.equals("yes")) {
            System.out.println("Enter your question:");
            String userQuestion = scanner.nextLine();
            int newQuestionId;
            do {
                newQuestionId = random.nextInt(10000) + 5;
            }
            while (questionCollection.getQuestionId(newQuestionId) != null);
            questionCollection.addQuestion(newQuestionId, userQuestion);
            System.out.println("Your question has been added to the map with ID: " + newQuestionId);
        }
        else {
            System.out.println("No new questions have been added to the map.");
        }

        //Display accumulated questions
        System.out.println("\nAll questions accumulated:");
        for(Question storedQuestion : questionCollection.getAllQuestions()) {
            System.out.println(storedQuestion);
        }
        scanner.close();

        }
    }
        */