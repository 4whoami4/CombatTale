package com.combattale.controllers;

import com.combattale.Game;
import com.combattale.components.BossCharacter;
import com.combattale.components.FlyingTarget;
import com.combattale.components.SafeZone;
import com.combattale.components.ui.Dialog;
import com.combattale.scenes.FirstStageScene;
import com.combattale.utils.Controller;
import com.combattale.utils.Scene;

public class BossController extends Controller {

    private PlayerController playerController;
    private BossCharacter bossCharacter;
    private FlyingTarget flyingTarget;
    private SafeZone safeZone;
    private Dialog dialog;
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
        dialog = currentScene.getComponent(Dialog.class);
    }

    @Override
    public void update(float deltaTime) {
        time += deltaTime;
        if (time > 1 && state == 0) {
            state++;
            bossCharacter.setState(BossCharacter.BossState.STANDING);
            dialog.show(
                    "Banished to a shadow realm for sins you've never committed, " +
                            "you find yourself in 'Combat Tale,' a world where you need to " +
                            "fight for your innocence. Defeat monsters in combat or remain " +
                            "trapped in this dark reality forever. Will you reclaim your " +
                            "innocence and return to the world you once knew, or will your " +
                            "heart be forever bound to this realm of shadows?",
                    () -> state++
            );
        }
        if (state == 2) {
            state++;
            dialog.hide();
            safeZone.isPaused = false;
            playerController.canMove = true;
            bossCharacter.setState(BossCharacter.BossState.FIGHTING);
        }
        if (time > 20f) {
            time = 0;
            flyingTarget.isVisible = true;
            flyingTarget.isPaused = false;
        }
    }

    public void dealDamage(float damage) {
        health -= damage;
        if (health > 0f) return;

        health = 0f;
        bossCharacter.setState(BossCharacter.BossState.DEAD);
        safeZone.isPaused = true;
        safeZone.reset();
    }
}
