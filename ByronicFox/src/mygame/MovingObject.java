/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;

/**
 *
 * @author Ravi
 */
public class MovingObject extends ImmobileObject {
    
    public MovingObject(float scale, String objectName, String texture, AssetManager assetManager, Material material, String path) {
        super(scale, objectName, texture, assetManager, material, path);
    }

    @Override
    protected void controlUpdate(float tpf) {
        //super.controlUpdate(tpf); //To change body of generated methods, choose Tools | Templates.
        this.spatial.move(new Vector3f(0,0,-30*tpf));
    }
    
    
    
}
