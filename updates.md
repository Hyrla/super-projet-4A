# Super-Projet-4A : Updates


Afin de conserver et d'animer notre communauté de joueurs, nous allons bien évidemment mettre à jour notre jeu. Cette document a pour but de définir le processus de mise à jour.

### Eviter les coupures de service
Il faut préscrire au maximum les coupures de service. A chaque mise à jour, nous n'allons donc pas couper notre cluster entier pour ensuite le remettre en fonctionnement. Nous allons progressivement poussé les mises à jour sur chacunes des VMs du cluster.

### Que faire des joueurs ?
Pendant le processus de mise à jour, la moitié des clusters seront mis à jour. Une fois 50% de clusters mis à jour, nous allons pousser la mise à jour au client. Les clients à jour pourront jouer sur la moitié de serveurs à jour. L'autre moitié de client sur les anciens serveurs. Une fois que 80% des clients sont à jour, nous allons imposer la mise à jour aux joueurs qui n'ont pas encore migrés.

Ce cas de figure sera relativement rare. En effet, les mises à jour courantes (ajout de bonus notamment) ne sont qu'à faire côté serveur et ne vont pas nécessiter de mise à jour client. La deuxième partie de cette méthode de mise à jour ne sera donc pas utilisée.

### Avertir les joueurs de la mise à jour
Afin d'avertir les joueurs de la mise à jour, le client fera une requête API de vérification de version. Le client se chargera de notifier le joueur une seule fois.
