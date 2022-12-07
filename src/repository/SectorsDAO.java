package repository;
import model.Sectors;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SectorsDAO {

    static List<Sectors> sectorsList = new ArrayList<>();
    static int total = 1;

    public static void initSectors(){
        List<String> names = Arrays.asList(
                "Adiministrativo",
                "Departamento de artes",
                "Producter Managers",
                "Marketing Empresarial"
        );

        for (String name: names){
            Sectors sectors = new Sectors();
            sectors.setId(total);
            sectors.setName(name );
            save(sectors);
        }
    }

    public static void save(Sectors sectors){
        if (sectors.getName() != null){
            sectorsList.add(sectors);
            total++;
        }
    }

    public static List<Sectors> fetchAll() { return sectorsList; }

    public static Object[] findSectorsInArray(){
        List<Sectors> sectorsList = SectorsDAO.fetchAll();
        List<String> sectorsNames = new ArrayList<>();

        for (Sectors sectors : sectorsList){
            sectorsNames.add(sectors.getName());
        }
        return  sectorsNames.toArray();
    }

    public static Sectors findSectorsById(int id){
        List<Sectors> sectorsList = SectorsDAO.fetchAll();

        for (Sectors sectors : sectorsList){
            if(sectors.getId() == id){
                return  sectors;
            }
        }
        return null;
    }

    public static Object[] findSectorsInArrayWithId(){
        List<Sectors> sectorsList = SectorsDAO.fetchAll();
        List<String> sectorsNames = new ArrayList<>();

        for (Sectors sectors : sectorsList){
            sectorsNames.add(sectors.getId() + " - " + sectors.getName());
        }
        return sectorsNames.toArray();
    }

}
