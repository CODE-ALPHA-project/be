package com.codealpha.resoft_be.domain.message.dto;
public class LawReference {
    private String law;
    private String chapter;
    private String title;

    // Getters and Setters
    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // toString() for easy logging/debugging
    @Override
    public String toString() {
        return "LawReference{" +
                "law='" + law + '\'' +
                ", chapter='" + chapter + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
