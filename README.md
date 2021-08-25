# Solitaire
Jeu du solitaire (avec les billes)

Dans le cadre d'un cours sur les interfaces graphiques en Java lors de mon DUT Informatique réalisé à l'IUT Paul Sabatier de Toulouse, j'ai été chargé de réaliser un projet seul de mon choix mettant en pratique les notions apprises.

J'aidécidé de réaliser un jeu du solitaire avec des billes, le concept est simple ; on peut déplacer une bille par dessus une autre bille adjacente seulement si derrière celle-ci se trouve une case vide. Une fois le déplacement effectué, la bille s'étant fait passé dessus est retirée du plateau. Le but du jeu est de n'avoir qu'une seule bille sur le plateau à la fin.
Pour réaliser ceci, j'ai donc utiliser un pattern MVC avec java swing. Un système de sérialisation a également été mis en place afin d'assurer la possibilité de sauvegarder et charger une unique partie.
