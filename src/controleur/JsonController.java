package controleur;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import modele.Joueur;
import modele.ScenarioStandard;
import modele.Soldat;
  
public class JsonController
{
    public void sauvegarde_file_json(ScenarioStandard scenario)
    {
    	try
    	{
            JSONObject jo = new JSONObject();
            jo.put("typeScenario", scenario.getTypeScenario());
            
    		int taille = scenario.getJoueurs().size();
        	if(taille > 0)
        	{
        		ArrayList<Map> listjoueur = new ArrayList<Map>(taille);
            	for(int i=0;i<taille;i++)
            	{
            		 // creating JSONObject
            		Map joueurs = new LinkedHashMap(5);
            		joueurs.put("nomJoueur", scenario.getJoueurs().get(i).getNomJoueur());
            		int taillesoldats = scenario.getJoueurs().get(i).getNombreSoldat();
            		ArrayList<Map> listsoldats = new ArrayList<Map>(taillesoldats);
            		for(int j=0;j<taillesoldats;j++)
                    {
                    	Map m = new LinkedHashMap(10);
                        m.put("abcisse", scenario.getJoueurs().get(i).getSoldatList().get(j).getAbscisse());
                        m.put("ordonnee", scenario.getJoueurs().get(i).getSoldatList().get(j).getOrdonnees());
                        m.put("image", scenario.getJoueurs().get(i).getSoldatList().get(j).getImage());
                        m.put("typeSoldat", scenario.getJoueurs().get(i).getSoldatList().get(j).getTypeSoldat());
                        m.put("imagePivotee", scenario.getJoueurs().get(i).getSoldatList().get(j).getImagePivotee());
                        m.put("attaque", scenario.getJoueurs().get(i).getSoldatList().get(j).getAttaque());
                        m.put("defense", scenario.getJoueurs().get(i).getSoldatList().get(j).getDefense());
                        m.put("deplacement", scenario.getJoueurs().get(i).getSoldatList().get(j).getDeplacement());
                        m.put("vision", scenario.getJoueurs().get(i).getSoldatList().get(j).getVision());
                        m.put("pv", scenario.getJoueurs().get(i).getSoldatList().get(j).getPv());
                        m.put("ko", scenario.getJoueurs().get(i).getSoldatList().get(j).isKo());
                        listsoldats.add(m);
                    }
                    
            		joueurs.put("soldats", listsoldats);
            		joueurs.put("score", scenario.getJoueurs().get(i).getScore());
            		joueurs.put("image", scenario.getJoueurs().get(i).getImage());
            		joueurs.put("KO", scenario.getJoueurs().get(i).getKO());
                    
            		listjoueur.add(joueurs);
            	}
            	jo.put("joueurs", listjoueur);
            	
            	File file = new File("Sauvegarde/Sauvegarder.json");
            	if(file.exists())
            	{
            		file.delete();
            		
            	}
            	PrintWriter pw = new PrintWriter("Sauvegarde/Sauvegarder.json");
                pw.write(jo.toString());
                  
                pw.flush();
                pw.close();
        	}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public ScenarioStandard read_file_json()
    {
    	try
    	{
    		String jsonstring = FileUtils.readFileToString(new File("Sauvegarde/Sauvegarder.json"), StandardCharsets.UTF_8);
    		JSONObject json  = new JSONObject(jsonstring);
    		ScenarioStandard result = new ScenarioStandard();
    		result.setTypeScenario(json.getString("typeScenario"));
    		System.out.println(json.getJSONArray("joueurs").length());
    		
    		JSONArray joueurs = json.getJSONArray("joueurs");
    		ArrayList<Joueur> listdata = new ArrayList<Joueur>();
    		
    		if(joueurs.length() > 0)
    		{
    			for(int i=0;i<joueurs.length();i++)
                {
                    JSONObject jsonObject1 = joueurs.getJSONObject(i);
                    Joueur jo = new Joueur();
                    jo.setNomJoueur(jsonObject1.getString("nomJoueur"));
                    jo.setScore(jsonObject1.getInt("score"));
                    jo.setImage(jsonObject1.getString("image"));
                    
                    JSONArray soldats = jsonObject1.getJSONArray("soldats");
                    ArrayList<Soldat> listdata2 = new ArrayList<Soldat>();
                    for(int j=0;j<soldats.length();j++)
                    {
                    	JSONObject jsonObject2 = soldats.getJSONObject(j);
                    	Soldat s = new Soldat(jsonObject2.getInt("abcisse"), jsonObject2.getInt("ordonnee"), jsonObject2.getString("typeSoldat"), jsonObject2.getString("imagePivotee"), jsonObject2.getString("image"), jsonObject2.getInt("attaque"),jsonObject2.getInt("defense"), jsonObject2.getInt("deplacement"), jsonObject2.getInt("vision"), jsonObject2.getInt("pv"), jsonObject2.getBoolean("ko"));
                    	listdata2.add(s);
                    }
                    
                    JSONArray ko = jsonObject1.getJSONArray("KO");
                    int [] kotab = new int[ko.length()];
                    for(int k=0;k<ko.length();k++)
                    {
                    	kotab[i] = ko.optInt(k);
                    }
                    jo.setSoldatList(listdata2);
                    jo.setKO(kotab);
                    listdata.add(jo);
                }
    		}
    		result.setJoueurs(listdata);
    		return result;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return null;
    		
    	}
    }
}