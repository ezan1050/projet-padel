package be.ephec.padel.repository;

import be.ephec.padel.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long>
{
    
}