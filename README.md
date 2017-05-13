# Collective_OHGJ_Game
A "Collective" game made by a comunity for a comunity.

Devs:
  - t0m
  - DevilLime
  - HellowPixl
  
Artists:
  - t0m
  - HellowPixl
  


TODO:
  - Base Engine.


## How it works

First, please don't edit the engine package.
All your assets should be placed in the core/assets/minigames/yourgamename/.
For example, if you want to add a texture, named a.png, put it in : core/assets/minigames/myawesomegame/a.png
In the code :
```java
Texture a = new Texture("minigames/myawesomegame/a.png");
```

### Creating a minigame
To create a mini game, create a class in the minigames package, wich extends the MiniGame class.
Note that in this lib, 1 unit represents 100 pixels, so if you draw a square that's 1 width it will be 100 pixels width on the screen. Same for location and so (just like in Unity).
In this class you will have a few methods :
```java

class MyGame extends MiniGame {

    public MyGame(Game game) {
        super(game);
        //I don't recommand you to write anything here
    }

    void show() {
        //Will be called when the game is created, so here are initialization stuffs, like object creation and so.
    }

    void update() {
        //Update function, called every 16ms
    }

    void render() {
        //Here you can draw all your stuffs, with the Draw class
        Draw.rect(1, 1, 1, 1, Color.RED);
    }

}

```


