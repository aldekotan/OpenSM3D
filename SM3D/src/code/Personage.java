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


   public static void loadPersM3G(byte expLevel, byte modelId) 
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
      if(RenderEngine.StalkersAppearanceGroups[modelId] != null) 
      {
         setDodgeLevel(expLevel, modelId);
         LoadStalkerModelWithThisTextures(expLevel, modelId);
      } 
      else 
      {
         if(RenderEngine.StalkersAppearanceGroups[0] == null) 
         {
            RenderEngine.StalkersAppearanceGroups[0] = new Group();

            Mesh var4;
            byte var5;
            for(var5 = 0; var5 < 12; ++var5) 
            {
               mesh = (Mesh)RenderEngine.persWorld.find(var5 + 1);
               var4 = (Mesh)mesh.duplicate();
               RenderEngine.StalkersAppearanceGroups[0].addChild(var4);
               mesh = null;
               System.gc();
            }

            for(var5 = 30; var5 < 34; ++var5) 
            {
               mesh = (Mesh)RenderEngine.persWorld.find(var5);
               (var4 = (Mesh)mesh.duplicate()).setRenderingEnable(false);
               RenderEngine.StalkersAppearanceGroups[0].addChild(var4);
               mesh = null;
               System.gc();
            }

            light = (Light)RenderEngine.persWorld.find(50);
            Light var7;
            (var7 = (Light)light.duplicate()).setRenderingEnable(false);
            RenderEngine.StalkersAppearanceGroups[0].addChild(var7);
            light = null;
            System.gc();
         } 
         else 
         {
            RenderEngine.StalkersAppearanceGroups[modelId] = (Group)RenderEngine.StalkersAppearanceGroups[0].duplicate();
         }

         RenderEngine.StalkersAppearanceGroups[modelId].setRenderingEnable(false);
         RenderEngine.gameWorld.addChild(RenderEngine.StalkersAppearanceGroups[modelId]);
         setDodgeLevel(expLevel, modelId);
         LoadStalkerModelWithThisTextures(expLevel, modelId);
      }
   }

   private static void setDodgeLevel(byte expLevel, byte modelId) 
   {
      byte var2;
      if(expLevel == 0) 
      {
         var2 = 22;
         enemyDodgeLevels[modelId] = (byte)(expLevel - 0);
      } 
      else if(expLevel > 0 && expLevel < 4) //bandit
      {
         var2 = (byte)(expLevel + 21);
         enemyDodgeLevels[modelId] = (byte)(expLevel - 0);
      } 
      else if(expLevel >= 4 && expLevel < 7) //killer
      {
         var2 = (byte)(expLevel + 18);
         enemyDodgeLevels[modelId] = (byte)(expLevel - 3);
      } 
      else if(expLevel >= 7 && expLevel < 10) //stalker
      {
         var2 = (byte)(expLevel + 15);
         enemyDodgeLevels[modelId] = (byte)(expLevel - 6);
      } 
      else //soldier
      {
         var2 = (byte)(expLevel + 12);
         enemyDodgeLevels[modelId] = (byte)(expLevel - 9);
      }

      mesh = (Mesh)RenderEngine.persWorld.find(var2);
      Mesh newMesh = (Mesh)mesh.duplicate();
      RenderEngine.StalkersAppearanceGroups[modelId].addChild(newMesh);
      mesh = null;
   }

   private static void LoadStalkerModelWithThisTextures(byte enemy_level, byte number_of_group) 
   {
      CompositingMode compos_mode = new CompositingMode();
      String texture_name;
      if(enemy_level >= 0 && enemy_level < 4) 
      {
         texture_name = "pbandit.png"; //бандит
      } 
      else if(enemy_level >= 4 && enemy_level < 7) 
      {
         texture_name = "pkiller.png"; //наёмник
      } 
      else if(enemy_level >= 7 && enemy_level < 10) 
      {
         texture_name = "pstalker.png"; //сталкер
      } 
      else 
      {
         texture_name = "psoldier.png"; //солдат
      }

      Appearance new_appearance;
      (new_appearance = new Appearance()).setTexture(0, ResourceLoader.getTexture(texture_name));
      compos_mode.setBlending(68);
      Material new_material = new Material();
      new_appearance.setCompositingMode(compos_mode);
      new_appearance.setMaterial(new_material);

      for(byte number = 0; number < 12; ++number) 
      {
         ((Mesh)RenderEngine.StalkersAppearanceGroups[number_of_group].find(number + 1)).setAppearance(0, new_appearance);
         System.gc();
      }

   }

   public static byte getExpLevelOfEnemy(byte modelId) 
   {
      return enemyExpLevels[modelId];
   }

   private static void LoadThis3DSpriteWithTextures(byte number_in_group, byte var1) 
   {
      CompositingMode cmps_mode = new CompositingMode();
      String texture_name = RenderEngine.objectTextureName[var1];
      Appearance aprnc;
      (aprnc = new Appearance()).setTexture(0, ResourceLoader.getTexture(texture_name));
      cmps_mode.setBlending(68);
      Material new_mtrl = new Material();
      aprnc.setCompositingMode(cmps_mode);
      aprnc.setMaterial(new_mtrl);

      for(byte number = 0; number < 12; ++number) 
      {
         ((Mesh)RenderEngine.group_massive_2st[number_in_group].find(number + 1)).setAppearance(0, aprnc);
         System.gc();
      }
   }

   public static void DuplicatePersonageWithThisPose(byte var0, byte pose_number) 
   {
      if(RenderEngine.StalkersAppearanceGroups[0] != null) 
      {
         RenderEngine.group_massive_2st[var0] = (Group)RenderEngine.StalkersAppearanceGroups[0].duplicate();
      }

      RenderEngine.group_massive_2st[var0].setRenderingEnable(true);
      RenderEngine.gameWorld.addChild(RenderEngine.group_massive_2st[var0]);
      byte var3 = RenderEngine.objectPersId[pose_number];
      if(var3 != 0) 
      {
         mesh = (Mesh)RenderEngine.persWorld.find(var3);
         Mesh new_mesh = (Mesh)mesh.duplicate();
         RenderEngine.group_massive_2st[var0].addChild(new_mesh);
         mesh = null;
      }

      LoadThis3DSpriteWithTextures(var0, pose_number);
      int var4 = RenderEngine.objectPersAnimTime[pose_number] * 100;
      RenderEngine.group_massive_2st[var0].animate(var4);
      
      System.gc();
   }

}
