# Collective_OHGJ_Game
A "Collective" game made by a comunity for a comunity.  

TODO:
  - Improve Engine
  - Add Games
  - Add Rooms
## Keys
  
  - DebugMode: "G"

## How it works

First, please don't edit the engine package, and don't touch the desktop folder. All the game's code will be in the core/src folder.
All your assets should be placed in the core/assets/minigames/yourgamename/.
For example, if you want to add a texture, named a.png, put it in : core/assets/minigames/myawesomegame/a.png
In the code :
```java
Texture a = new Texture("minigames/myawesomegame/a.png");
```
# Artists
### T0m:
- Console (Main Room)
- Floor tiles (Main Room)

### Puarsliburf:
- Rock (Crazy Road)

# Developers
### T0m:
- Pong
- Game Engine

### HellowPixl:
- HoldUp
- Game Engine

### DevilLime:
- Crazy Road

# Creating a minigame
To create a mini game, create a class in the minigames package, wich extends the MiniGame class.
Note that in this lib, 1 unit represents 100 pixels, so if you draw a square that's 1 width it will be 100 pixels width on the screen. Same for location and so (just like in Unity).
*_Please create a new git branch for new minigames_*.
In this class you will have a few methods :
```java

public class TestMiniGame extends MiniGame {

    public void draw() {
        Draw.rect(1, 1, 1, 1, Color.BLUE);
    }

    public void update() {

    }

    public void show() {

    }
}

```
To link your game to an arcade (in the show function of the ArcadeRoom class) :
```java
add(new Arcade(new Vector2(5, 5), () -> {
            loadMiniGame(new TestMiniGame());
            return false;
        }));
        add(new Arcade(new Vector2(6, 5), () -> {
            loadMiniGame(new MyMiniGame());
            return false;
        }));
        add(new Arcade(new Vector2(7, 5), () -> {
            loadMiniGame(new MyMiniGame());
            return false;
        }));
```


