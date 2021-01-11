package com.mywardrop.trivaquiz.model;

public class QuizQuestion
{
    private String questionText;
    private boolean trueAnswer;

    public QuizQuestion(String questionText, boolean trueAnswer) {
        this.questionText = questionText;
        this.trueAnswer = trueAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean getTrueAnswer() {
        return trueAnswer;
    }


}
