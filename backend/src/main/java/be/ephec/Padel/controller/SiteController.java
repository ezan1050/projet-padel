package be.ephec.padel.controller;

import be.ephec.padel.model.Site;
import be.ephec.padel.service.SiteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sites")

public class SiteController
{
    private final SiteService siteService;

    public SiteController(SiteService siteService)
    {
        this.siteService = siteService;
    }

    // GET /api/Sites -> liste de tous les sites
    @GetMapping
    public List<Site> getTousLesSites()
    {
        return siteService.getTousLesSites();
    }

    // POST /api/Sites -> creer un nouveau site
    @PostMapping
    public Site creerSite(@RequestBody Site site)
    {
        return siteService.creerSite(site);
    }
}