/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import static com.jme3.scene.plugins.fbx.mesh.FbxLayerElement.Type.Texture;
import com.jme3.texture.Texture;

/**
 *
 * @author Ravi
 */
public class ImmobileObject extends AbstractControl {
    
    protected float scale;
    protected String objectName;
    
    /*
     *constructor abstrak untuk membuat objek
     * @param scale  skala objek
     * @param objectName nama objek
     * @param assetManager asset manager suatu objek(perlu penjelasan)
     * @param textrue
     * @param material
     * @param path alamat model objek yang akan di-load
    */
    
    public ImmobileObject(float scale,String objectName,String texture, AssetManager assetManager, Material material, String path){
        this.scale=scale;
        this.objectName=objectName;
        this.spatial=assetManager.loadModel(path);
        this.spatial.setMaterial(material);
    }

    @Override
    protected void controlUpdate(float tpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
