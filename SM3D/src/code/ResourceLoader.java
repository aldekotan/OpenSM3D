package code;


import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import javax.microedition.m3g.*;

public final class ResourceLoader {

    public static int texturesCount = 0;
    public static Texture2D[] textures;
    private static String[] texturesNames;
    public static boolean[] texturesUsedTwice;

    public static void clearTextures() {
        for(byte i = 0; i < 100; ++i) {
            if(!texturesUsedTwice[i]) {
                textures[i] = null;
                texturesNames[i] = "";
            }
        }

        System.gc();
        texturesCount = 0;
    }

    public static void initTexturesMassive() {
        texturesCount = 0;
        textures = new Texture2D[100];
        texturesNames = new String[100];
        texturesUsedTwice = new boolean[100];
        System.gc();
    }

    private static int findTextureId(String path) {
        for(byte i = 0; i < 100; i++) {
            if(path.equals(texturesNames[i]) && !path.equals("")) {
                return i;
            }
        }

        return -1;
    }

    public static Mesh loadZ1Model(String path) throws IOException {
        DataInputStream dis = new DataInputStream(
                Main.main.getClass().getResourceAsStream("/gamedata/meshes" + path)
        );

        dis.readLong();
        dis.readLong();

        byte submeshesCount = dis.readByte();
        String[] submeshTexture = new String[submeshesCount];
        float[] submeshAlphaTest = new float[submeshesCount];
        byte[] submeshBlendingMode = new byte[submeshesCount];
        byte[] submeshDoublesided = new byte[submeshesCount];

        for(int i = 0; i < submeshTexture.length; ++i) {
            char[] texNameChars = new char[dis.readByte()];

            for(int i2 = 0; i2 < texNameChars.length; ++i2) {
                texNameChars[i2] = (char) dis.readByte();
            }

            submeshTexture[i] = String.valueOf(texNameChars);
            submeshAlphaTest[i] = dis.readByte();
            submeshBlendingMode[i] = dis.readByte();
            submeshDoublesided[i] = dis.readByte();
        }

        short[] allPositions = new short[dis.readShort() * 3];

        for(int i = 0; i < allPositions.length; ++i) {
            allPositions[i] = (short) dis.readByte();
        }

        short[] allNormals = new short[dis.readShort() * 3];

        for(int i = 0; i < allNormals.length; ++i) {
            allNormals[i] = (short) dis.readByte();
        }

        int uvXAdd = dis.readInt();
        int uvYAdd = dis.readInt();
        int uvScale = dis.readInt();
        short[] allUVs = new short[dis.readShort() * 2];

        for(int i = 0; i < allUVs.length; i+=2) {
            allUVs[i] = (short) (dis.readUnsignedByte() + uvXAdd);
            allUVs[i + 1] = (short) (dis.readUnsignedByte() + uvYAdd);
        }

        short polygonsCount = dis.readShort();
        int[] positionsIds = new int[polygonsCount * 3];
        int[] normalsIds = new int[polygonsCount * 3];
        short[] vertexUVsID = new short[polygonsCount * 3];
        short[] polygonsSubmeshIds = new short[polygonsCount];
        byte[] stripMassive = new byte[polygonsCount];

        for(int i = 0; i < polygonsCount; ++i) {
            polygonsSubmeshIds[i] = dis.readByte();
            stripMassive[i] = dis.readByte();
        }

        for(int i = 0; i < polygonsCount * 3; i++) {
            positionsIds[i] = dis.readShort();
        }

        for(int i = 0; i < polygonsCount * 3; i++) {
            normalsIds[i] = dis.readShort();
        }

        for(int i = 0; i < polygonsCount * 3; i++) {
            vertexUVsID[i] = dis.readShort();
        }
        dis.close();

        short[] positions = new short[polygonsCount * 3 * 3];
        short[] normals = new short[polygonsCount * 3 * 3];

        for(int i = 0; i < polygonsCount * 3; ++i) {
            int posId = positionsIds[i];
            int normId = normalsIds[i];

            for(int x = 0; x < 3; ++x) {
                positions[i * 3 + x] = allPositions[posId * 3 + x];
                normals[i * 3 + x] = allNormals[normId * 3 + x];
            }
        }

        Vector uvsVector = new Vector();

        for(int i = 0; i < polygonsCount * 3; ++i) {
            short uvId = vertexUVsID[i];

            Short u = new Short(allUVs[uvId * 2 + 0]);
            uvsVector.addElement(u);

            Short v = new Short(allUVs[uvId * 2 + 1]);
            uvsVector.addElement(v);
        }

        uvsVector.trimToSize();
        short[] uvsMassive = new short[uvsVector.capacity()];

        for(int i = 0; i < uvsMassive.length; ++i) {
            Short uv = (Short) uvsVector.elementAt(i);
            uvsMassive[i] = uv.shortValue();
        }
        
        VertexArray vertexPositions = new VertexArray(positions.length / 3, 3, 2);
        vertexPositions.set(0, positions.length / 3, positions);

        VertexArray vertexUVS = new VertexArray(uvsMassive.length / 2, 2, 2);
        vertexUVS.set(0, uvsMassive.length / 2, uvsMassive);
        
        VertexArray vertexNormals = new VertexArray(positions.length / 3, 3, 2);
        vertexNormals.set(0, positions.length / 3, normals);
        
        VertexBuffer vb = new VertexBuffer();
        vb.setPositions(vertexPositions, 1, null);
        vb.setNormals(vertexNormals);
        vb.setTexCoords(0, vertexUVS, uvScale / 1000000f, null);
        
        IndexBuffer[] indexBufferMassive = new IndexBuffer[submeshesCount];
        Appearance[] appearanceMassive = new Appearance[submeshesCount];

        for(int sbMesh = 0; sbMesh < submeshesCount; sbMesh++) {
            Vector triangleIndixesVector = new Vector();
            Vector stripVector = new Vector();

            for(short i = 0; i < polygonsCount; i++) {
                if(sbMesh == polygonsSubmeshIds[i]) {
                    Short ind = new Short((short) (i * 3 + 0));
                    triangleIndixesVector.addElement(ind);
                    ind = new Short((short) (i * 3 + 1));
                    triangleIndixesVector.addElement(ind);
                    ind = new Short((short) (i * 3 + 2));
                    triangleIndixesVector.addElement(ind);

                    ind = new Short(stripMassive[i]);
                    stripVector.addElement(ind);
                }
            }

            triangleIndixesVector.trimToSize();
            int[] triangleIndexes = new int[triangleIndixesVector.capacity()];

            for(int i = 0; i < triangleIndexes.length; ++i) {
                Short ind = (Short) triangleIndixesVector.elementAt(i);
                triangleIndexes[i] = ind.shortValue();
            }

            stripVector.trimToSize();
            int[] submeshStripLengths = new int[stripVector.capacity()];

            for(int i = 0; i < submeshStripLengths.length; ++i) {
                Short ind = (Short) stripVector.elementAt(i);
                submeshStripLengths[i] = ind.shortValue();
            }

            appearanceMassive[sbMesh] = new Appearance();
            CompositingMode cm = new CompositingMode();
			cm.setAlphaWriteEnable(false);
            
            Material mat = new Material();
            appearanceMassive[sbMesh].setMaterial(mat);
            
            if(submeshAlphaTest[sbMesh] == 0.0F) {
                mat.setColor(Material.EMISSIVE, 0xffffffff);
            /*} else if(submeshAlphaTest[sbMesh] < 0) {
                //Glass material (??)
                //From STALKER MOBILE HD
                mat.setShininess(48);
                mat.setColor(Material.SPECULAR, 0xffffffff);
                mat.setColor(Material.DIFFUSE, 0xff888888);
            */} else {
                cm.setAlphaThreshold(submeshAlphaTest[sbMesh] / 100.0F);
            }

            if(submeshBlendingMode[sbMesh] != 0) {
                cm.setBlending(submeshBlendingMode[sbMesh]);
            }

            appearanceMassive[sbMesh].setCompositingMode(cm);
            indexBufferMassive[sbMesh] = new TriangleStripArray(triangleIndexes, submeshStripLengths);
        }

        Mesh mesh = new Mesh(vb, indexBufferMassive, appearanceMassive);

        try {
            for(int i = 0; i < submeshesCount; ++i) {
                String textureName;
                if((textureName = submeshTexture[i]).length() != 0) {
                    mesh.getAppearance(i).setTexture(0, getTexture(textureName));
                    System.gc();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PolygonMode pm = new PolygonMode();
        pm.setPerspectiveCorrectionEnable(true);
        pm.setCulling(submeshDoublesided[0] == 1 ? PolygonMode.CULL_NONE : PolygonMode.CULL_BACK);

        for(byte i = 0; i < submeshesCount; ++i) {
            if(submeshDoublesided[i] == 1) {
                pm.setTwoSidedLightingEnable(true);
                break;
            }
        }

        for(int i = 0; i < submeshesCount; ++i) {
            mesh.getAppearance(i).setPolygonMode(pm);
        }

        mesh.setScale(0.1F, 0.1F, 0.1F);
		
		ModChanges.updateZ1Models(mesh, path);
		
        return mesh;
    }

    public static Texture2D getTexture(String path) {
        Texture2D tex = null;

        try {
            int textureId = findTextureId(path);
            
            if(textureId != -1) {
                tex = textures[textureId];
                texturesUsedTwice[textureId] = true;
            } else {
                String textureAdress = "/gamedata/textures/" + path;

                Image2D img2d;
                if(path.indexOf("texobj") != -1) {
                    img2d = ModChanges.loadIndexedImage(textureAdress);
                } else {
                    Image img = Image.createImage(textureAdress);
                    //Todo не использовать RGBA, когда возможно
                    img2d = new Image2D(Image2D.RGBA, img);
                }
                
                tex = new Texture2D(img2d);
                
                texturesNames[texturesCount] = path;
                textures[texturesCount] = tex;
                texturesCount++;
            }

            tex.setBlending(Texture2D.FUNC_MODULATE);
            tex.setBlendColor(0x000000);
            tex.setFiltering(Texture2D.FILTER_BASE_LEVEL, Texture2D.FILTER_NEAREST);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tex;
    }

    public static Sprite3D getSprite(int spriteId) {
        Image img;
        Image2D img2D = null;
        Texture2D tex = null;
        String path = spriteId > -100 ? GameScene.objectTextureName[spriteId] : "blood.png";
        if(spriteId == -101) path = ""; // -101 is weapon fire
        
        int textureId = findTextureId(path);

        try {
            if(textureId != -1) {
                tex = textures[textureId];
                
                img2D = tex.getImage();
                texturesUsedTwice[textureId] = true;
            } else if(spriteId == -101) {
                img = PlayerHUD.weaponFireImage;
                
                img2D = new Image2D(Image2D.RGBA, img);
                tex = new Texture2D(img2D);
            } else {
                img = Image.createImage("/gamedata/textures/" + path);
                img2D = new Image2D(Image2D.RGBA, img);
                tex = new Texture2D(img2D);
                
                texturesNames[texturesCount] = path;
                textures[texturesCount] = tex;
                texturesCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CompositingMode cm = new CompositingMode();
        if(spriteId == -100) {
            cm.setBlending(CompositingMode.ALPHA);
        } else if(spriteId == -101) {
            cm.setBlending(CompositingMode.ALPHA_ADD);
        } else {
            cm.setBlending(GameScene.objectSpriteBlendMode[spriteId]);
        }

        if(spriteId != -101) {
            cm.setAlphaThreshold(1.0F);
        } else {
            cm.setAlphaThreshold(0.6F);
        }

        Appearance ap = new Appearance();
        ap.setCompositingMode(cm);
        ap.setTexture(0, tex);
        ap.setLayer(63); //render sprites after all geometry
		
		//Set alpha threshold instead of alpha test
		if(path.indexOf("tree") != -1) {
			cm.setAlphaWriteEnable(false);
			cm.setBlending(CompositingMode.REPLACE);
			cm.setAlphaThreshold(0.5f);
			ap.setLayer(1); //render after geometry
			//probably z write can be disabled?
		}

        Sprite3D spr = new Sprite3D(true, img2D, ap);
        //STALKER MOBILE HD
        //if(path.indexOf("glare") > -1) spr.scale(1.3f, 1.3f, 1.3f);

        System.gc();
        return spr;
    }
}
