package com.combattale.controllers;

import com.badlogic.gdx.Input;
import com.combattale.Game;
import com.combattale.components.BossCharacter;
import com.combattale.components.FlyingTarget;
import com.combattale.components.SafeZone;
import com.combattale.components.ui.Dialog;
import com.combattale.components.ui.HitText;
import com.combattale.components.ui.Score;
import com.combattale.scenes.VictoryScreen;
import com.combattale.utils.Controller;
import com.combattale.utils.Scene;

public class BossController extends Controller {

    private PlayerController playerController;
    private BossCharacter bossCharacter;
    private FlyingTarget flyingTarget;
    private SafeZone safeZone;
    private HitText hitText;
    private Dialog dialog;
    private Score score;
    private double time = 0;
    private float health = 100;
    private int state = 0;

    @Override
    public void create() {
        final Scene currentScene = Game.instance.getActiveScene();
        playerController = currentScene.getComponent(PlayerController.class);
        bossCharacter = currentScene.getComponent(BossCharacter.class);
        flyingTarget = currentScene.getComponent(FlyingTarget.class);
        safeZone = currentScene.getComponent(SafeZone.class);
        hitText = currentScene.getComponent(HitText.class);
        dialog = currentScene.getComponent(Dialog.class);
        score = currentScene.getComponent(Score.class);
    }

    @Override
    public void update(float deltaTime) {
        time += deltaTime;
        if (time > 1 && state == 0) {
            bossCharacter.setState(BossCharacter.BossState.STANDING);
            dialog.show(
                    "Banished to a shadow realm for sins you've never committed, " +
                            "you find yourself in 'Combat Tale,' a world where you need to " +
                            "fight for your innocence. Defeat monsters in combat or remain " +
                            "trapped in this dark reality forever. Will you reclaim your " +
                            "innocence and return to the world you once knew, or will your " +
                            "heart be forever bound to this realm of shadows?",
                    this::nextState
            );
            nextState();
        }
        if (state == 2) {
            dialog.hide();
            safeZone.isPaused = false;
            playerController.canMove = true;
            bossCharacter.setState(BossCharacter.BossState.FIGHTING);
            nextState();
        }
        if (state == 3) {
            if (time > 5f) {
                time = 0;
                flyingTarget.reset();
            }
        }
        if (state == 4) {
            if (time > 4f) {
                Game.instance.setScene(new VictoryScreen(score.getScore()));
            }
        }
    }

    public void dealDamage(float damage) {
        health -= damage;
        if (damage < 10) {
            hitText.show("MISS");
        } else if (damage < 25) {
            hitText.show("ALMOST");
        } else if (damage < 49) {
            hitText.show("HIT");
        } else {
            hitText.show("CRITICAL HIT");
        }
        if (health > 0f) return;
        setState(4);
    }

    @Override
    public void keyboardEvent(Input input, float deltaTime) {
        if (state == 1 && input.isKeyPressed(Input.Keys.SPACE)) {
            setState(2);
        }
    }

    private void setState(int state) {
        this.time = 0;
        this.state = state;
    }

    private void nextState() {
        setState(state + 1);
    }
}
