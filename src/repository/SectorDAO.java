package repository;

import model.Sector;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SectorDAO{

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
}