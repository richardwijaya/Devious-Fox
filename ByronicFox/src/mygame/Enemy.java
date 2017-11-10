/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;

/**
 *
 * @author Ravi
 */
public class Enemy extends MovingObject {
    
    public Enemy(float scale, String objectName, String texture, AssetManager assetManager, Material material, String path) {
        super(scale, objectName, texture, assetManager, material, path);
        this.spatial=assetManager.loadModel(path);
        //this.spatial.setMaterial(material);
        this.spatial.setLocalScale(scale);
    }

    @Override
    protected void controlUpdate(float tpf) {
        super.controlUpdate(tpf); //To change body of generated methods, choose Tools | Templates.
    }

    
  
    
    
}
