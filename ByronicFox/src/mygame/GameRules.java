/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.ChaseCamera;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Ravi
 */
public class GameRules extends AbstractAppState {

    //atribut-atribut inisialisasi game
    private Node rootNode;
    private Node localRootNode = new Node("placeholder");
    private AssetManager assetManager;
    private BulletAppState bulletAppState;

    //atribut-atribut main character
    private MainCharacter mainCharacter;//ibi
    private CharacterControl mainCharacterControl;
    private Spatial mainCharacterSpatial;
    private InputManager inputManager;

    //atribut-atribut lantai
    private Spatial ground, ground2;
    private RigidBodyControl groundControl, ground2Control;
    private LinkedList<Spatial> listOfGrounds;
    private LinkedList<Spatial> listOfObstacle;
    private LinkedList<Spatial> listOfEnemies;
    private Spatial landscapeSpatial;
    private Landscape landscape;

    //atribut-atribut obstacle
    private Spatial obstacleSpatial;
    private Obstacle obstacle;
    private Spatial enemySpatial;
    private Enemy enemy;

    private Material mainMaterial;

    //atribut-atribut kamera
    private FlyByCamera flyCamera;
    private Camera camera;
    private ChaseCamera chaseCamera;
    private Vector3f playerWalkDirection = Vector3f.ZERO;
    private CameraNode cameraNode;
    private CharacterController mcc;
    //action listener
    private ActionListener mainActionListener = new ActionListener() {
        public void setUpKeys() {
            inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
            inputManager.addListener(mainActionListener, "Jump");
        }

        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
               if(name.equals("Jump" )&& !isPressed){
                mainCharacterControl.jump();
            }
        }
    };

    public GameRules(SimpleApplication gameApp) {
        rootNode = gameApp.getRootNode();
        assetManager = gameApp.getAssetManager();
        inputManager = gameApp.getInputManager();
        flyCamera = gameApp.getFlyByCamera();
        camera = gameApp.getCamera();

        Material foxMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        mainMaterial = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");

        //load main character
        mainCharacter = new MainCharacter(0.05f, "fox", "", assetManager, mainMaterial, "Models/animations/animations.j3o");
        mainCharacterSpatial = mainCharacter.getSpatial();
        //mainCharacterSpatial.setMaterial(mainMaterial);
        //load scenes
        landscape = new Landscape(100f, "ground", "", assetManager, mainMaterial, "Scenes/MainScene.j3o");
        landscapeSpatial = assetManager.loadModel("Scenes/MainScene.j3o");
        landscapeSpatial.setMaterial(mainMaterial);
        //load obstacle
        obstacle = new Obstacle(2f, "tree", "", assetManager, mainMaterial, "Models/Tree/Tree.j3o");
        obstacleSpatial = obstacle.getSpatial();
        //obstacleSpatial.setMaterial(mainMaterial);
        //load Enemy
        enemy = new Enemy(0.25f, "falcone", "", assetManager, mainMaterial, "Models/falcone/falcone.j3o");
        enemySpatial = enemy.getSpatial();

        listOfObstacle = new LinkedList<>();
        listOfGrounds = new LinkedList<>();
        listOfEnemies = new LinkedList<>();
        
        //mcc=new CharacterController(mainCharacterControl, mainActionListener);
       // mainActionListener.onAction(name, initialized, 0);
    }

    @Override
    public void cleanup() {
        rootNode.detachChild(localRootNode);
        super.cleanup();

    }

    @Override
    public void update(float tpf) {
        //        super.update(tpf); //To change body of generated methods, choose Tools | Templates.
        Vector3f v2 = mainCharacterSpatial.getLocalTranslation();
        Iterator<Spatial> orbit = listOfObstacle.iterator();
        while (orbit.hasNext()) {
            Spatial newSpatial = orbit.next();
            Vector3f v1 = newSpatial.getLocalTranslation();
            float x = Math.abs(v1.x - v2.x);
            float y = Math.abs(v1.y - v2.y);
            float z = Math.abs(v1.z - v2.z);
            if (x <= 0.5 && y <= 1.6 && z <= 0.5) {
//                System.out.println("lose");
            }
            newSpatial.move(0, 0, tpf * -20);
            if (newSpatial.getLocalTranslation().getZ() <= -5) {
                newSpatial.getLocalTranslation().setZ(50);
            }
        }

//        Vector3f v3 = mainCharacterSpatial.getLocalTranslation();
//        Vector3f subsV3 = v3.subtractLocal(0, 0, 5f);
        Iterator<Spatial> orbit2 = listOfEnemies.iterator();
        while (orbit2.hasNext()) {
            Spatial newSpatial = orbit2.next();
            Vector3f v1 = newSpatial.getLocalTranslation();
            float x = Math.abs(v1.x - v2.x);
            float y = Math.abs(v1.y - v2.y);
            float z = Math.abs(v1.z - v2.z);
            if (x <= 0.5 && y <= 1.6 && z <= 0.5) {
//                System.out.println("lose");
            }
            newSpatial.move(0, 0, tpf * -30);
            if (newSpatial.getLocalTranslation().getZ() <= -5) {
                newSpatial.getLocalTranslation().setZ(50);
            }
        }

        Iterator<Spatial> it = listOfGrounds.iterator();
        while (it.hasNext()) {
            Spatial n = it.next();
            n.move(0, 0, -20 * tpf);
            if (n.getLocalTranslation().getZ() <= -100) {
                n.getLocalTranslation().setZ(n.getLocalTranslation().getZ() + 200);
            }
        }

//        mainCharacterControl.jump();
//        mainCharacterControl.setGravity(30);
//        mainCharacterControl.setFallSpeed(30);
//        mainCharacterControl.setJumpSpeed(20);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        bulletAppState = new BulletAppState();
        bulletAppState.setDebugEnabled(true);
        stateManager.attach(bulletAppState);
        rootNode.attachChild(localRootNode);

        localRootNode.attachChild(landscapeSpatial);

        //set main character
        localRootNode.attachChild(mainCharacterSpatial);

        //set obstacle
        localRootNode.attachChild(obstacleSpatial);

        //setEnemy
        enemySpatial.setLocalTranslation(0, 5f, 0);
        localRootNode.attachChild(enemySpatial);

        //set list of ground, obstacle and enemy
        ground = landscapeSpatial;
        ground2 = landscapeSpatial;
        listOfGrounds.addFirst(ground);
        listOfGrounds.addFirst(ground2);
        listOfObstacle.addFirst(obstacleSpatial);
        listOfEnemies.addFirst(enemySpatial);

        //buat nempel
        CollisionShape groundShape = CollisionShapeFactory.createMeshShape(ground);
        groundControl = new RigidBodyControl(groundShape, 0);
        ground.addControl(groundControl);
        ground2.addControl(groundControl);

        bulletAppState.getPhysicsSpace().add(ground);
        bulletAppState.getPhysicsSpace().add(ground2);
        bulletAppState.getPhysicsSpace().add(obstacleSpatial);
        bulletAppState.getPhysicsSpace().add(enemySpatial);

        //set main character bounding
        BoundingBox bb = (BoundingBox) mainCharacterSpatial.getWorldBound();
        float radius = bb.getXExtent();
        float height = bb.getYExtent();
        CapsuleCollisionShape mainCharacterShape = new CapsuleCollisionShape(radius, height);

        //main character control
        mainCharacterControl = new CharacterControl(mainCharacterShape, 1.0f);
        //CharacterController cc = new CharacterController(mainCharacterControl);
        mainCharacterSpatial.addControl(mainCharacterControl);
        bulletAppState.getPhysicsSpace().add(mainCharacterControl);

        //camera work
        flyCamera.setMoveSpeed(100f);
        chaseCamera = new ChaseCamera(camera, mainCharacterSpatial, inputManager);
        chaseCamera.setDefaultHorizontalRotation(-3.1f);
        chaseCamera.setDefaultVerticalRotation(0.2f);
        setUpLight();
        rootNode.attachChild(localRootNode);

    }

    private void setUpLight() {
        // We add light so we see the scene
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);

        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);
    }

}
