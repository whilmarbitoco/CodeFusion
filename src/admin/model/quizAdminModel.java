/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.model;

import Core.Quiz;
import java.util.ArrayList;

/**
 *
 * @author wb2c0
 */
public class quizAdminModel {

    private ArrayList<Quiz> quizes;

    public quizAdminModel() {
        this.quizes = new ArrayList<>();
        quizes.add(new Quiz(10, "What is 2+2", "4", "Enumeration", 0));

        Object[] test = {"Laguna", "Davao", "Cavite", "Manila"};
        quizes.add(new Quiz(10, "What is the capital of the Philippines", "Manila", "Multiple Choice", 1));

    }
    
    public void addQuiz(Quiz quiz) {
        quizes.add(quiz);
    }
    
    public int getTotal() {
        return this.quizes.size();
    }
    
    public  ArrayList<Quiz> getQuizes() {
        return this.quizes;
        
    }
    
    public void delete(int id) {
        
        for (Quiz q : this.quizes) {
            if (q.id == id) {
                this.quizes.remove(q);
                break;
            }
        }
    }

}
