package be.ephec.padel.service;

import be.ephec.padel.model.Site;
import be.ephec.padel.repository.SiteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SiteService
{
    private final SiteRepository siteRepository;

// injection de dependance : Spring va fournir le repository automatiquement
    public SiteService(SiteRepository siteRepository)
    {
        this.siteRepository = siteRepository;
    }

    //recuperer tous les sites
    public List<Site> getTousLesSites()
    {
        return siteRepository.findAll();
    }

    //enregistrer un nouveau site
    public Site creerSite(Site site)
    {
        return siteRepository.save(site);
    }

}