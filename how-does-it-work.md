# Super-Projet-4A : How does it work ?


### Synopsis
Super-projet-4A est un jeu vidéo ultra réaliste de combat innovant et disruptif. Il met en scène des robots marsiens de type Rover équipés de lasers.

### Comment jouer
Super-projet-4A se joue au travers d'une interface web en tour par tour. En effet, notre système est conçu de cette manière et ne permet donc pas de jouer en temps réel. A chaque tour le joueur aura le choix entre déplacer son MarsRover et tirer avec son laser.

### Vos informations personnelles
Le respect de votre vie privée est notre priorité. Le bon fonctionnement de notre jeu ne nécessite en aucun cas la récolte de vos données personnelles.

### Multiples parties
Notre système, de part son architecture est capable de gérer de multiples parties en parallèle. Chaque partie/instance du jeu est associée un id unique qui sert pour nos requêtes API.

### Multiples parties par joueur
Comme chaque instance possède un ID unique, chaque joueur peut combattre dans plusieurs parties en même temps.

### Lorsque ça crash
Si notre serveur backend se retrouve en état de dysfonctionnement, alors notre super serveur frontend affichera un message d'excuse aux joueurs souhaitant démarrer une partie. Les parties ne seront alors plus accessibles. Lors du redémarrage de notre serveur backend, le dernier état de chaque partie/instance de jeu stocké dans la base de données sera restauré. Ainsi, les parties pourront reprendre.

### Le stockage des données
Chaque joueur est authentifié par son pseudonyme. A chaque tour joué dans une instance, un dump est réalisé. Ce dump joue le rôle d'une base de donnée chargée et déchargée au lancement ou coupure du serveur.

### Gestion de la charge
Si le nombre d'instances de jeu est supérieur à la capacité de la RAM de notre serveur, alors il serait nécessaire de monter un cluster de serveur d'instances de jeu. Ce cluster gérerait dynamiquement le nombre de VMs ou de conteneurs disponibles.

### Schéma fonctionnel
![Si vous ne voyez pas cette image c'est vraiment dommage](https://i.ibb.co/BB500dL/Capture-d-cran-de-2020-04-12-13-04-15.png)
