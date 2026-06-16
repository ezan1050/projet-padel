package be.ephec.padel.model;

public enum TypeMembre
{
    GLOBAL,
    SITE,
    LIBRE
}

/**
 * 
 * Ton cahier des charges décrit 3 catégories, avec des règles différentes :
TypeMatriculeRéserve combien de temps avantOùGlobalcommence par G3 semainestous les sitesSitecommence par S2 semainesson site uniquementLibrecommence par L5 jourstous les sites
Le point clé : un membre est forcément dans l'une de ces 3 catégories, jamais autre chose. C'est exactement le cas d'usage d'un enum.
Qu'est-ce qu'un enum ?
Un enum (énumération) est une liste fermée de valeurs possibles, fixée d'avance. Au lieu de stocker le type comme un texte libre (où on pourrait écrire "Globall" ou "gloabl" par erreur), on dit à Java : « le type d'un membre ne peut être QUE l'une de ces 3 valeurs : GLOBAL, SITE, ou LIBRE. Rien d'autre n'est permis. »
C'est comme un menu à choix fixe : tu ne peux commander que ce qui est sur la carte. Ça évite les fautes de frappe et rend ton code plus sûr

 * 
 */