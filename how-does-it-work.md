# Super-Projet-4A : How does it work ?


### Synopsis
Super-projet-4A est un jeu vidéo inovant et distruptif mettant en scène une simulation ultra réaliste de combat mettant en scène des robots marsiens de type Rover.

### Comment jouer
Super-projet-4A se joue au travers d'une interface web au tour par tour. En effet, la manière dont est conscu notre système ne permet pas de jouer en temps réél. A chaque tour le joueur pourra choisir de déplacer son MarsRover ou de tirer au laser.

### Vos informations personnelles
Le respect de votre vie privée est notre priorité. Le fonctionnement de notre jeu de nécessite pas la récolte de vos données personnelles.

### Multiples parties
Notre système très évolué gère plusieurs partie en même temps. À chaque instance de jeu est associé un id unique qui sert pour nos requêtes API.

### Multiples parties par joueurs
Comme chaque instance possède un ID unique, un seul et unique joueur peut disputer plusieurs combats en même temps.

### Lorsque ça crash
Si notre serveur backend se retrouve en état de disfonctionnement, notre super serveur frontend affichera un message d'excuse. Les parties ne seront alors plus accessibles. Au redémarrage de notre serveur backend, le dernier état des instances de jeu stocké dans la base de données sera chargé. Ainsi, les parties pourront reprendre.

### Le stockage des données
Chaque joueur est authentifié par son pseudonyme. A chaque tour joué dans une instance, un dump est fait. Ce dump joue le rôle d'une base de donnée chargée et déchargée au lancement ou coupure du serveur.

### Gestion de la charge
Si le nombre d'instances de jeu est supérieur à ce que la RAM de notre serveur peut contenir, il faudrait monter un cluster de serveur d'instances de jeu. Ce cluster gérerait dynamquement le nombre de VMs ou de conteneurs disponibles.

### Schéma fonctionnel
![Si vous ne voyez pas cette image c'est dommage](https://i.ibb.co/BB500dL/Capture-d-cran-de-2020-04-12-13-04-15.png)
