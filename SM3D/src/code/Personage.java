package code;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.CompositingMode;
import javax.microedition.m3g.Group;
import javax.microedition.m3g.Light;
import javax.microedition.m3g.Loader;
import javax.microedition.m3g.Material;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Object3D;
import javax.microedition.m3g.World;

public final class Personage //Персонажи, модели, освещение, спрайты...
{
   //Уровень уворотов для каждого врага, от 0 до 3. 
   //Сами вероятности см. в NPCShootPlayer
   public static byte[] enemyDodgeLevels = new byte[10];
   
   private static Mesh mesh;
   private static Light light;
   
   //Уровень опытности каждого добавленного на уровень противника (от 0 до 12)
   //По нему вычисляется количество получаемого опыта за убийство
   private static final byte[] enemyExpLevels = new byte[10];


   public static void createMovablePers(byte expLevel, byte modelId) 
   {
      if(RenderEngine.persWorld == null) //?
      {
         String path = "/pers.m3g";

         try  {
            Object3D[] objs = Loader.load(path);
            ModChanges.updateM3DModels(objs, path);

            for(int i = 0; i < objs.length; ++i) 
            {
               if(objs[i] instanceof World)  {
                  RenderEngine.persWorld = (World)objs[i];
				  //break should be added?
               }
            }
			
         } catch (IOException e) {
            System.out.println("Error at loading model pers.m3g");
            e.printStackTrace();
         }
      }

      enemyExpLevels[modelId] = expLevel;
      if(RenderEngine.movablePersonages[modelId] != null) 
      {
         setDodgeLevel(expLevel, modelId);
         setMovablePersAppearance(expLevel, modelId);
      } 
      else 
      {
         if(RenderEngine.movablePersonages[0] == null) 
         {
            RenderEngine.movablePersonages[0] = new Group();

            Mesh newMesh;
            byte var5;
            for(var5 = 0; var5 < 12; ++var5) 
            {
               mesh = (Mesh)RenderEngine.persWorld.find(var5 + 1);
               newMesh = (Mesh)mesh.duplicate();
               RenderEngine.movablePersonages[0].addChild(newMesh);
               mesh = null;
               System.gc();
            }

            for(var5 = 30; var5 < 34; ++var5) 
            {
               mesh = (Mesh)RenderEngine.persWorld.find(var5);
               (newMesh = (Mesh)mesh.duplicate()).setRenderingEnable(false);
               RenderEngine.movablePersonages[0].addChild(newMesh);
               mesh = null;
               System.gc();
            }

            light = (Light)RenderEngine.persWorld.find(50);
            Light newLight;
            (newLight = (Light)light.duplicate()).setRenderingEnable(false);
            RenderEngine.movablePersonages[0].addChild(newLight);
            light = null;
            System.gc();
         } 
         else 
         {
            RenderEngine.movablePersonages[modelId] = (Group)RenderEngine.movablePersonages[0].duplicate();
         }

         RenderEngine.movablePersonages[modelId].setRenderingEnable(false);
         RenderEngine.gameWorld.addChild(RenderEngine.movablePersonages[modelId]);
         setDodgeLevel(expLevel, modelId);
         setMovablePersAppearance(expLevel, modelId);
      }
   }

   private static void setDodgeLevel(byte expLevel, byte modelId) 
   {
      byte userID;
      if(expLevel == 0)  //tutorial bandits
      {
         userID = 22;
         enemyDodgeLevels[modelId] = (byte)(expLevel - 0);
      } 
      else if(expLevel > 0 && expLevel < 4) //bandit
      {
         userID = (byte)(expLevel + 21);
         enemyDodgeLevels[modelId] = (byte)(expLevel - 0);
      } 
      else if(expLevel >= 4 && expLevel < 7) //killer
      {
         userID = (byte)(expLevel + 18);
         enemyDodgeLevels[modelId] = (byte)(expLevel - 3);
      } 
      else if(expLevel >= 7 && expLevel < 10) //stalker
      {
         userID = (byte)(expLevel + 15);
         enemyDodgeLevels[modelId] = (byte)(expLevel - 6);
      } 
      else //soldier
      {
         userID = (byte)(expLevel + 12);
         enemyDodgeLevels[modelId] = (byte)(expLevel - 9);
      }

      mesh = (Mesh)RenderEngine.persWorld.find(userID);
      Mesh newMesh = (Mesh)mesh.duplicate();
      RenderEngine.movablePersonages[modelId].addChild(newMesh);
      mesh = null;
   }

   private static void setMovablePersAppearance(byte expLevel, byte modelId) 
   {
      CompositingMode compos_mode = new CompositingMode();
      String textureName;
      if(expLevel >= 0 && expLevel < 4) 
      {
         textureName = "pbandit.png"; //бандит
      } 
      else if(expLevel >= 4 && expLevel < 7) 
      {
         textureName = "pkiller.png"; //наёмник
      } 
      else if(expLevel >= 7 && expLevel < 10) 
      {
         textureName = "pstalker.png"; //сталкер
      } 
      else 
      {
         textureName = "psoldier.png"; //солдат
      }

      Appearance new_appearance;
      (new_appearance = new Appearance()).setTexture(0, ResourceLoader.getTexture(textureName));
      compos_mode.setBlending(68);
      Material new_material = new Material();
      new_appearance.setCompositingMode(compos_mode);
      new_appearance.setMaterial(new_material);

      for(byte i = 0; i < 12; ++i) 
      {
         ((Mesh)RenderEngine.movablePersonages[modelId].find(i + 1)).setAppearance(0, new_appearance);
         System.gc();
      }

   }

   public static byte getExpLevelOfEnemy(byte modelId) 
   {
      return enemyExpLevels[modelId];
   }

   private static void setStationaryPersAppearance(byte IdInsideGroup, byte textureId) 
   {
      CompositingMode cmps_mode = new CompositingMode();
      String textureName = RenderEngine.objectTextureName[textureId];
      Appearance aprnc;
      (aprnc = new Appearance()).setTexture(0, ResourceLoader.getTexture(textureName));
      cmps_mode.setBlending(68);
      Material new_mtrl = new Material();
      aprnc.setCompositingMode(cmps_mode);
      aprnc.setMaterial(new_mtrl);

      for(byte number = 0; number < 12; ++number) 
      {
         ((Mesh)RenderEngine.stationaryPersonages[IdInsideGroup].find(number + 1)).setAppearance(0, aprnc);
         System.gc();
      }
   }

   public static void createStationaryPers(byte idInsideGroup, byte persId) 
   {
      if(RenderEngine.movablePersonages[0] != null) 
      {
         RenderEngine.stationaryPersonages[idInsideGroup] = (Group)RenderEngine.movablePersonages[0].duplicate();
      }

      RenderEngine.stationaryPersonages[idInsideGroup].setRenderingEnable(true);
      RenderEngine.gameWorld.addChild(RenderEngine.stationaryPersonages[idInsideGroup]);
      byte userId = RenderEngine.objectPersId[persId];
      if(userId != 0) 
      {
         mesh = (Mesh)RenderEngine.persWorld.find(userId);
         Mesh new_mesh = (Mesh)mesh.duplicate();
         RenderEngine.stationaryPersonages[idInsideGroup].addChild(new_mesh);
         mesh = null;
      }

      setStationaryPersAppearance(idInsideGroup, persId);
      int time = RenderEngine.objectPersAnimTime[persId] * 100;
      RenderEngine.stationaryPersonages[idInsideGroup].animate(time);
      
      System.gc();
   }

}
