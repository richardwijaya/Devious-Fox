/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.texture.Texture;

/**
 *
 * @author Ravi
 */
public class Landscape extends ImmobileObject {

    protected Material mainMaterial;

    public Landscape(float scale, String objectName, String texture, AssetManager assetManager, Material material, String path) {
        super(scale, objectName, texture, assetManager, material, path);
        this.spatial = assetManager.loadModel(path);
//        this.mainMaterial=material;
//        this.mainMaterial.setTexture(objectName+"Texture", assetManager.loadTexture(texture));
//        this.spatial.setMaterial(this.mainMaterial);
        this.spatial.setLocalScale(scale);
    }

}
