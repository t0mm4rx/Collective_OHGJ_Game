package com.ohgj.collectivegame.minigames.notepad;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.ohgj.collectivegame.GameClass;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Game.Draw;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.IO.Touch;
import com.ohgj.engine.JSON.JSONObject;
import com.ohgj.engine.Net.HTTP;
import com.ohgj.engine.Net.HTTPListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notepad extends MiniGame {

    final String URL = "https://jsonblob.com/api/jsonBlob/860cf92a-3891-11e7-ae4c-1f38b7c71da0";

    TextArea textArea;

    String message = "Loading...", lastEdit = "";
    Color buttonColor;

    public void show() {

        buttonColor = new Color(0.4f, 0.4f, 0.4f, 1f);

        HTTP.get(URL, new HTTPListener() {
            public void onFinish(String data) {
                JSONObject json = new JSONObject(data);
                String content = json.get("content").toString();
                lastEdit = json.get("last_edit").toString();

                TextField.TextFieldStyle style = new TextField.TextFieldStyle();
                style.font = GameClass.font12;
                style.fontColor = new Color(1, 1, 1, 1);
                textArea = new TextArea(content, style);
                textArea.setWidth(500);
                textArea.setHeight(300);
                textArea.setPosition(Game.center.x - 250, Game.center.y - 150);
                getStage().addActor(textArea);

                message = "Save";
                buttonColor = new Color(0.2f, 0.3f, 1f, 1f);
            }

            public void onProgress(float percentage) {}

            public void onFail(String error) {
                System.out.println("Error : " + error);
            }
        });
    }

    public void draw() {
        Draw.rect(Game.center.x, 1, 1.7f, 0.6f, buttonColor);
        GameClass.gl.setText(GameClass.font15, message);
        Draw.text(message, Game.center.x - GameClass.gl.width / 100 / 2, 1 + GameClass.gl.height / 100 / 2, new Color(1, 1, 1, 1), GameClass.font15);
        Draw.text("Last edit on " + lastEdit, 0.3f, Game.size.y - 0.3f, new Color(1, 1, 1, 1), GameClass.font12);
    }

    public void update() {
        if (Touch.isJustTouched()) {
            if (message.equals("Save")) {
                Vector2 pos = Touch.getProjectedPosition();
                if (pos.x > Game.center.x - 0.85f && pos.x < Game.center.x + 0.85f) {
                    if (pos.y > 1 - 0.3f && pos.y < 1 + 0.3f) {
                        save();
                    }
                }
            }
        }
    }

    public void save() {
        message = "Saving...";
        buttonColor = new Color(0.4f, 0.4f, 0.4f, 1f);
        String edit = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());

        HTTP.put(URL, "{\"content\":\"" + textArea.getText() + "\", \"last_edit\":\"" + edit + "\"}", new HTTPListener() {
            public void onFinish(String data) {
                message = "Save";
                buttonColor = new Color(0.2f, 0.3f, 1f, 1f);
                lastEdit = edit;
            }

            public void onProgress(float percentage) {

            }

            public void onFail(String error) {
                System.out.println(error);
            }
        });
    }

}
