/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author Ravi
 */
public class Score{
  private int score;
  
  public Score(int score){
    this.score = score;
  }
  
  public void updateScore(){
    score++;
  }
}
