# Java_game_tankwar
This is a simple game demo to practice java programming. It is kinda based on a set of open tutorial videos published by a Chinese IT training institution called ShangXueTang. 

The main method is located in TankClient.java which is the manager manipulating other classes. TankClient class includes the instantiation of different game elements, including Tank, Missile, Explode, Blood and Wall, the GUI created by awt package and the generation of threads to repaint. After running the main method, you can move your 'good' tank by direction keys and press 'ctrl' to fire a missile, or 'A' for a series of super-all-direction bombs. The good tank has 100 health which will be reduced by 20 if hit by other tanks. It will recover to full state after colliding that little pink block. Besides, 5 enemy tanks will be regenerated after the first batch get cleared, and if your lovely tank unfortunately boomed, don't forget press'F2' to get a new one. 

I hope more features and modification to be added in this game, like the set of levels with increasing difficulties, super boss tank, more interesting game model. GUI also needs tons of modification.

Have fun in coding and playing! This is a perfect start to deeply understand OOP.  :)
