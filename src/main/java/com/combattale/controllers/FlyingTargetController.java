package com.combattale.controllers;

import com.badlogic.gdx.Input;
import com.combattale.Game;
import com.combattale.components.FlyingTarget;
import com.combattale.utils.Controller;
import com.combattale.utils.Scene;

public class FlyingTargetController extends Controller {
    private BossController bossController;
    private FlyingTarget target;

    @Override
    public void create() {
        final Scene currentScene = Game.instance.getActiveScene();
        bossController = currentScene.getComponent(BossController.class);
        target = currentScene.getComponent(FlyingTarget.class);
    }

    @Override
    public void keyboardEvent(Input input, float deltaTime) {
        if (input.isKeyPressed(Input.Keys.SPACE) && !target.isPaused && target.isVisible) {
            target.isPaused = true;
            final double damage = target.getDamageMultiplier();
            bossController.dealDamage((float) (damage * 50f));
        }
    }
}
