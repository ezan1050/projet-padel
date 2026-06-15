package be.ephec.padel.service;

import be.ephec.padel.model.Terrain;
import be.ephec.padel.repository.TerrainRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TerrainService
{
    private final TerrainRepository terrainRepository;

    // injection de dependance : Spring va fournir le repository automatiquement
    public TerrainService(TerrainRepository terrainRepository)
    {
        this.terrainRepository=terrainRepository;
    }

    //recuperer tous les terrains
    public List<Terrain> getTousLesTerrains()
    {
        return terrainRepository.findAll();
    }

    //enregistrer un nouveau site
    public Terrain creerTerrain(Terrain terrain)
    {
        return terrainRepository.save(terrain);
    }
}