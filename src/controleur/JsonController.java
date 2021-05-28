package controleur;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import modele.Joueur;
  
public class JsonController
{
    public void sauvegarde_file_json(ArrayList<Joueur> joueurList)
    {
    	try
    	{
    		int taille = joueurList.size();
        	if(taille > 0)
        	{
        		List<JSONObject> jofinale = new ArrayList<JSONObject>(taille);
            	for(int i=0;i<taille;i++)
            	{
            		 // creating JSONObject
                    JSONObject jo = new JSONObject();
            		jo.put("nomJoueur", joueurList.get(i).getNomJoueur());
                    jo.put("score", joueurList.get(i).getScore());
                    jo.put("image", joueurList.get(i).getImage());
                    
                    int taillesoldats = joueurList.get(i).getNombreSoldat();
                    List<Map> listsoldats = new ArrayList<Map>(taille);
                    for(int j=0;j<taillesoldats;j++)
                    {
                    	Map m = new LinkedHashMap(10);
                        m.put("abcisse", joueurList.get(i).getSoldatList().get(j).getAbscisse());
                        m.put("ordonnee", joueurList.get(i).getSoldatList().get(j).getOrdonnees());
                        m.put("image", joueurList.get(i).getSoldatList().get(j).getImage());
                        m.put("typeSoldat", joueurList.get(i).getSoldatList().get(j).getTypeSoldat());
                        m.put("attaque", joueurList.get(i).getSoldatList().get(j).getAttaque());
                        m.put("defense", joueurList.get(i).getSoldatList().get(j).getDefense());
                        m.put("deplacement", joueurList.get(i).getSoldatList().get(j).getDeplacement());
                        m.put("vision", joueurList.get(i).getSoldatList().get(j).getVision());
                        m.put("pv", joueurList.get(i).getSoldatList().get(j).getPv());
                        m.put("ko", joueurList.get(i).getSoldatList().get(j).isKo());
                        listsoldats.add(m);
                    }
                    
                    jo.put("soldats", listsoldats);
                    
                    jo.put("KO", joueurList.get(i).getKO());
                    
                    jofinale.add(jo);
            	}
            	
            	File file = new File("Sauvegarde/Sauvegarder.json");
            	if(file.exists())
            	{
            		file.delete();
            		
            	}
            	PrintWriter pw = new PrintWriter("Sauvegarde/Sauvegarder.json");
                pw.write(jofinale.toString());
                  
                pw.flush();
                pw.close();
        	}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public List<Joueur> read_file_json()
    {
    	try
    	{
    		String jsonstring = FileUtils.readFileToString(new File("Sauvegarde/Sauvegarder.json"), StandardCharsets.UTF_8);
        	System.out.println(jsonstring);
        	ObjectMapper mapper = new ObjectMapper();
        	List<Joueur> listejoueur = mapper.readValue(jsonstring, new TypeReference<List<Joueur>>(){});
        	System.out.println(listejoueur.size());
        	return listejoueur;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return null;
    		
    	}
    }
}