vision statement  
----------------  
Escala is a simulation game that gives players the opportunity to spread a fictional commercial product around the world. This software features educational aspects but is primarily targeted at gamers. Many of the world’s cultural, social, logistical, and economic complexities are baked into the simulation engine, which means this game offers insight into the systems of product diffusion and economies-of-scale that many people take for granted.  

Escala is inspired by [Plague Inc.](https://www.ndemiccreations.com/en/22-plague-inc), a mobile application whose primary objective is to enable players to destroy humanity with a biblical pandemic... Unlike Plague Inc., Escala’s objective is much more benign and *civilization-friendly*.

user stories  
------------  
| User Stories | Validation | Priority | Story Points |
|:------------:|:----------:|:--------:|:------------:|
| I want to be able to change the resolution of the game. | User sees resolution change. | 5 | 2 |
| I want to be able to change the difficulty of the game. | User has different experience at different difficulties. | 5 | 21 |
| I want to be able to select different products for the the game. | User has different experience with different products. | 5 | 3 |
| I want to be able to pause the game on click or when I open a menu. | Game pauses. | 2 | 2 |
| I want to be able to change the speed of the game. | Game changes speed. | 3 | 3 |
| I want the software to load at the center of my screen. | Game starts at the center of the screen. | 1 | 1 |
| I want to be able to change the settings before starting the game. | Settings menu has effect on game. | 3 | 8 |
| I want the graphics to change when I hover over a region. | Game regions change on color. | 1 | 8 |
| I want to see a demo before playing the game. | User is taught how to play the game prior to starting. | 5 | 21 |
| I want to be able to click on the map, and see the effects of my click. | User see change on click. | 1 | 13 |
| I want the game to continue without having to click a button. | Automatic game progression. | 1 | 3 |
| I want to be able to upgrade my product, marketing and logistics. | User can change company stats. | 1 | 21 |
| I want to see my product being shipped across the globe. | Sprites move across globe. | 5 | 5 |
| I want the game to have some randomness, so I can't play the same game twice. | Randomness occurs throught the game. | 4 | 8 |
| I want to see my stats without having to enter a separate menu. | Stats are displayed on the map. | 1 | 3 |
| I want to be able to change my game settings mid-game. | Settings tweaks take effect. | 5 | 21 |
| I want to be able to name my product, and see it used in the game. | Product name is used throughout the game. | 5 | 5 |
| I want to be able to save my game and reload it. | Game can be reloaded. | 5 | 8 |
| I want each region to have immersive data. | User sees region data in-game. | 5 | 8 |

backlogs  
--------  
[Product Backlog](https://github.com/tgsachse/escala/issues?utf8=%E2%9C%93&q=is%3Aissue+label%3A%22product+backlog%22+)  
[Sprint Backlog](https://github.com/tgsachse/escala/issues?utf8=%E2%9C%93&q=is%3Aissue+label%3A%22sprint+2+backlog%22+)  

burndown chart  
---------------  
![Image cannot be displayed.](BURNDOWN.png)

design diagrams  
---------------  
![Image cannot be displayed.](ARCHITECTURE_DIAGRAM.png)  
[larger view](https://raw.githubusercontent.com/tgsachse/escala/dev/docs/sprints/sprint2/ARCHITECTURE_DIAGRAM.png)  

![Image cannot be displayed.](CLASS_DIAGRAM.png)  
[larger view](https://raw.githubusercontent.com/tgsachse/escala/dev/docs/sprints/sprint2/CLASS_DIAGRAM.png)  

demonstration UPDATE
-------------
coming soon

code  
----  
This game requires [Java8](http://www.oracle.com/technetwork/java/javase/downloads/index.html). Download the zip [here](https://github.com/tgsachse/escala/releases/download/v0.2/escala.zip) and unzip the contents. To the run game, double click `run.sh` on Linux and macOS or `run.bat` on Windows. The scripts can also be executed from the command line.

tests UPDATE
-----
Will either ship tests with archive or require repo download.

requirements
------------

- A on screen slider can change the reslution.
- Different levels have different math assocaited with it. 
- Each product has unique charateristics.
- Each Porduct has some unique math involved.
- A pause button to pause game.
- A play button to resume game.
- Game starts at middle of screen.
- A fastword button to increase speed of game.
- Each region has multiple graphics asscoaietd with it depending on status.
- Clisk listener to select region and hightlight.
- Mouse location listener to allow for highlighting on hover.
- Stats stored and shown to player.
- Graphs on bottom of screen tp diplay stats.
- All stats values are an integer 1-100.
- Sprites move along specified path.
- Sprites repesent transportation means.
- Paths are defined along logical path for said transposrtation type.
- Events are stored in the database.
- A random event selector selects evernt randomly.
- Settings page, contains mulpile settings.
- Settings page asscessible from menu.
- USer can enter product name.
- Product name is continued to be used throughout the game.
- Each region has its own object.
- Each region charatyeristics are unique.
- Each region owned has effect on gameplay.
- Time passes automatically at steady rate.
- The ability to increase speed of time via fatsforward button.
- The stats automatically update as time passes.
- The user can but goods with cash accumalted.
- If the user does not have enoght money they cannot purchase a good.
