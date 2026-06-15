package be.ephec.padel.repository;

import be.ephec.padel.model.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerrainRepository extends JpaRepository<Terrain, Long>
{

}