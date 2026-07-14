import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const token = localStorage.getItem('token');

  if (token) {
    const reqAvecToken = req.clone({
      setHeaders: {
        Authorization: 'Bearer ' + token
      }
    });
    return next(reqAvecToken);
  }

  return next(req);
};

/**
 * const token = localStorage.getItem('token') → on récupère le token rangé au login (dans la poche).
if (token) → s'il y en a un (l'utilisateur est connecté) :

req.clone({ setHeaders: { Authorization: 'Bearer ' + token } }) → on fait une copie de la requête en y ajoutant l'en-tête Authorization: Bearer <token>. (On clone car en Angular, une requête est immuable — on ne la modifie pas, on en crée une version enrichie.)
return next(reqAvecToken) → on laisse partir la requête avec le bracelet.


return next(req) (à la fin) → s'il n'y a pas de token, la requête part telle quelle. Les routes publiques (GET sites, login) marcheront quand même.

Tu reconnais la logique de ton JwtFilter Java, non ? "Si un token est là, je le prends en compte ; sinon je laisse passer sans bloquer."
 */