# Super-Projet-4A : Updates

Afin de conserver et de continuer à animer notre communauté de joueurs, nous allons bien évidemment mettre à jour notre jeu. Cette documentation a pour but de définir le processus de mise à jour.

### Éviter les coupures de service
Il faut prescrire au maximum les coupures de service. A chaque mise à jour, nous n'allons donc pas couper notre cluster entier pour ensuite le remettre en fonctionnement. Nous allons progressivement poussé les mises à jour sur chacunes des VMs du cluster.

### Que faire des joueurs ?
Pendant le processus de mise à jour, la moitié des clusters seront mis à jour. Une fois 50% de clusters mis à jour, nous allons envoyer la mise à jour au client. De cette manière les clients à jour pourront jouer sur la moitié de serveurs à jour. L'autre moitié de client en revanche continueront sur les anciens serveurs. Une fois que 80% des clients sont à jour, nous imposeront la mise à jour aux derniers joueurs qui n'ont pas encore fait la migration.

Ce cas de figure sera relativement rare. En effet, les mises à jour courantes (ajout de bonus notamment) ne sont qu'à réaliser du côté serveur et donc ne nécessite pas de mise à jour du côté du client. La deuxième partie de cette méthode de mise à jour ne sera donc pas utilisée.

### Avertir les joueurs de la mise à jour
Afin d'avertir les joueurs de la mise à jour, le client fera une requête API de vérification de version. Le client se chargera de notifier le joueur une seule fois.