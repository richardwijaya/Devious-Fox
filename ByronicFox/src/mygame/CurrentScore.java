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
public class CurrentScore extends Score{
  
  public Score(){
    score = 0;
  }
  
  public int getScore(){
    score++;
  }
  
  public int updateScore(){
    score++;
  }
  
}
