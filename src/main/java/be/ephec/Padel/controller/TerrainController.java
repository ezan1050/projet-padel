package be.ephec.padel.controller;

import be.ephec.padel.model.Terrain;
import be.ephec.padel.service.TerrainService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/terrains")

public class TerrainController
{
    private final TerrainService terrainService;

    public TerrainController(TerrainService terrainService)
    {
        this.terrainService = terrainService;
    }

    // GET /api/terrains -> liste de tous les terrains
    @GetMapping
    public List<Terrain> getTousLesTerrains()
    {
        return terrainService.getTousLesTerrains();
    }

    // POST /api/terrains -> creer un nouveau terrain
    @PostMapping
    public Terrain creerTerrain(@RequestBody Terrain terrain)
    {
        return terrainService.creerTerrain(terrain);
    }
}