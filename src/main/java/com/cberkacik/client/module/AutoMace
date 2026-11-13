package com.cberkacik.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.AxeItem;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

import java.util.IdentityHashMap;
import java.util.Map;

public class Am {
    private static final long COOLDOWN_MS = 600L;
    private static final Map<PlayerEntity, Long> lastAttackMap = new IdentityHashMap<>();

    public static void checkAndAttack(MinecraftClient mc) {
        if (!Tg.isTeamCheckEnabled()) {
            return;
        }

        ClientPlayerEntity player = mc.player;
        if (player == null || mc.world == null) {
            return;
        }

        HitResult crosshairTarget = mc.crosshairTarget;
        if (!(crosshairTarget instanceof EntityHitResult)) {
            return;
        }

        Entity targetEntity = ((EntityHitResult) crosshairTarget).getEntity();
        if (!(targetEntity instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity targetPlayer = (PlayerEntity) targetEntity;
        if (targetPlayer == player || !targetPlayer.isAlive() || player.distanceTo(targetPlayer) > 3.25f) {
            return;
        }

        if (TargetValidator.dda160347(targetPlayer)) {
            return;
        }

        boolean isShieldBlocking = targetPlayer.isUsingItem() && targetPlayer.getActiveItem().getItem() == Items.SHIELD;
        if (!isShieldBlocking) {
            return;
        }

        long now = System.currentTimeMillis();
        Long lastAttack = lastAttackMap.get(targetPlayer);
        if (lastAttack != null && now - lastAttack < COOLDOWN_MS) {
            return;
        }

        int maceSlot = findMaceSlot(player);
        if (maceSlot < 0) {
            return;
        }

        PlayerInventory inv = player.getInventory();
        int originalSlot = inv.selectedSlot;

        player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(maceSlot));
        player.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(targetPlayer, player.isSneaking()));
        player.swingHand(Hand.MAIN_HAND);
        player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(originalSlot));

        lastAttackMap.put(targetPlayer, now);
        if (lastAttackMap.size() > 64) {
            lastAttackMap.clear();
        }
    }

    private static int findMaceSlot(ClientPlayerEntity player) {
        PlayerInventory inv = player.getInventory();
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inv.getStack(i);
            if (stack != null && stack.getItem() instanceof AxeItem) {
                return i;
            }
        }
        return -1;
    }
}
