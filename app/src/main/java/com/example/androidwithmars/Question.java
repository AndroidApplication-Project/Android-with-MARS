package com.example.androidwithmars;

public class Question {
    private String codeQuestion,codeAnswer;
    private boolean expandable;

    @Override
    public String toString() {
        return "Question{" +
                "codeQuestion='" + codeQuestion + '\'' +
                ", codeAnswer='" + codeAnswer + '\'' +
                '}';
    }

    public String getCodeQuestion() {
        return codeQuestion;
    }

    public void setCodeQuestion(String codeQuestion) {
        this.codeQuestion = codeQuestion;
    }

    public String getCodeAnswer() {
        return codeAnswer;
    }

    public void setCodeAnswer(String codeAnswer) {
        this.codeAnswer = codeAnswer;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public Question(String codeQuestion, String codeAnswer) {
        this.codeQuestion = codeQuestion;
        this.codeAnswer = codeAnswer;
        this.expandable= false;
    }
}
