package com.combattale.controllers;

import com.badlogic.gdx.Input;
import com.combattale.Game;
import com.combattale.components.FlyingTarget;
import com.combattale.components.MiniGameBorder;
import com.combattale.utils.Controller;
import com.combattale.utils.Scene;

public class FlyingTargetController extends Controller {
    private PlayerController playerController;
    private BossController bossController;
    private MiniGameBorder border;
    private FlyingTarget target;

    @Override
    public void create() {
        final Scene currentScene = Game.instance.getActiveScene();
        playerController = currentScene.getComponent(PlayerController.class);
        bossController = currentScene.getComponent(BossController.class);
        border = currentScene.getComponent(MiniGameBorder.class);
        target = currentScene.getComponent(FlyingTarget.class);
    }

    @Override
    public void keyboardEvent(Input input, float deltaTime) {
        if (input.isKeyPressed(Input.Keys.SPACE) && !target.isPaused && target.isVisible) {
            playerController.show();
            border.isBoardVisible = false;
            target.isPaused = true;
            bossController.time = 0;
            final double damage = target.getDamageMultiplier();
            bossController.dealDamage((float) (damage * 50f));
        }
    }
}
