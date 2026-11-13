package com.cberkacik.client.module;

import net.minecraft.entity.player.PlayerEntity;

public class TargetValidator {
    public static boolean dda160347(PlayerEntity player) {
        // Hedef doğrulama kontrolleri
        return player == null || !player.isAlive();
    }
}
