package repository;

import model.Sector;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SectorDAO{

    public static Sector String;
    static List<Sector> sectors = new ArrayList<>();

    public void save(Sector sector) {
        SectorRepository sectorRepository = new SectorRepository();
        try {
            if (sector.getId() != null) {
                sectorRepository.update(sector);
            } else {
                sector.setId(sectorRepository.nextId());
                sectorRepository.insert(sector);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        sectors.add(sector);
    }

    public void remove(Sector sector) throws SQLException, ClassNotFoundException {
        SectorRepository sectorRepository = new SectorRepository();
        sectorRepository.delete(sector);
    }

    public List<Sector> searchAll() throws SQLException, ClassNotFoundException {
        System.out.println(sectors);

        SectorRepository sectorRepository = new SectorRepository();
        try{
            sectors = sectorRepository.search();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return sectors;
    }

    public List<Sector> searchWithName(String name) {
        List<Sector> filtredSectors = new ArrayList<>();
        for (Sector sector : sectors){
            if (sector.getName().contains(name)) {
                filtredSectors.add(sector);
            }
        }
        return filtredSectors;
    }

    public Sector searchById(Integer id) {
        SectorRepository sectorRepository = new SectorRepository();
        Sector sector = new Sector();
        try{
            sectors = sectorRepository.searchById(id);

            for (Sector auxSector : sectors){
                if (auxSector.getId().equals(id)) {
                    sector = auxSector;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sector;
    }

    public Object[] searchAllOnlyWithName() throws SQLException, ClassNotFoundException {
        SectorRepository sectorRepository = new SectorRepository();
        ArrayList<String> names = new ArrayList<>();
        try{
            sectors = sectorRepository.search();

            for (Sector sector : sectors) {
                names.add(sector.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return names.toArray();
    }
    public Object[] searchAllWithIdOnName() {
        SectorRepository sectorRepository = new SectorRepository();
        List<String> sectorsName = new ArrayList<>();

        try{
            sectors = sectorRepository.search();

            for (Sector sector : sectors) {
                sectorsName.add(sector.getId() + " - " + sector.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return sectorsName.toArray();
    }
}