/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;

/**
 *
 * @author Ravi
 */
public class CharacterController extends SimpleApplication implements ActionListener{

    
    private CharacterControl mainCharacterControl;
    private ActionListener acc;
            
    public CharacterController(CharacterControl mainCharacterControl,ActionListener acc) {
        this.acc=acc;
        this.mainCharacterControl = mainCharacterControl;
    }

    @Override
    public void simpleInitApp() {
        setupKeys();
    }
    
    public void setupKeys(){
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this.acc, "Jump");
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if(name.equals("Jump")){
            mainCharacterControl.jump();
        }
    }

    public CharacterControl getMainCharacterControl() {
        return mainCharacterControl;
    }
    
    
    
}
