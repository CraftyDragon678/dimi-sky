package com.cragon.dimisky

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random

class DimiSky : JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val under = e.block.world.getBlockAt(e.block.x, e.block.y - 1, e.block.z)
        e.isCancelled = true
        when (under.type) {
            Material.GREEN_GLAZED_TERRACOTTA -> {
                when (Random.nextInt(6)) {
                    0 -> e.block.type = Material.OAK_LOG
                    1 -> e.block.type = Material.SPRUCE_LOG
                    2 -> e.block.type = Material.BIRCH_LOG
                    3 -> e.block.type = Material.JUNGLE_LOG
                    4 -> e.block.type = Material.ACACIA_LOG
                    5 -> e.block.type = Material.DARK_OAK_LOG
                }
            }
            Material.BROWN_GLAZED_TERRACOTTA -> {
                e.block.type = Material.OAK_LOG
            }
            Material.GRAY_GLAZED_TERRACOTTA -> {
                when (Random.nextInt(100)) {
                    in 0..79 -> e.block.type = Material.STONE
                    else -> e.block.type = Material.COAL_ORE
                }
            }
            Material.LIME_GLAZED_TERRACOTTA -> {
                when (Random.nextInt(100)) {
                    in 0..69 -> e.block.type = Material.STONE
                    in 70..89 -> e.block.type = Material.COAL_ORE
                    else -> e.block.type = Material.IRON_ORE
                }
            }
            Material.LIGHT_BLUE_GLAZED_TERRACOTTA -> {
                when (Random.nextInt(100)) {
                    in 0..59 -> e.block.type = Material.STONE
                    in 60..79 -> e.block.type = Material.COAL_ORE
                    in 80..89 -> e.block.type = Material.IRON_ORE
                    in 90..94 -> e.block.type = Material.REDSTONE_ORE
                    else -> e.block.type = Material.LAPIS_ORE
                }
            }
            Material.MAGENTA_GLAZED_TERRACOTTA -> {
                when (Random.nextInt(100)) {
                    in 0..49 -> e.block.type = Material.STONE
                    in 50..69 -> e.block.type = Material.COAL_ORE
                    in 70..84 -> e.block.type = Material.IRON_ORE
                    in 85..89 -> e.block.type = Material.REDSTONE_ORE
                    in 90..94 -> e.block.type = Material.LAPIS_ORE
                    in 95..98 -> e.block.type = Material.EMERALD_ORE
                    else -> e.block.type = Material.DIAMOND_ORE
                }
            }
            Material.YELLOW_GLAZED_TERRACOTTA -> {
                when (Random.nextInt(100)) {
                    in 0..59 -> e.block.type = Material.STONE
                    in 60..89 -> e.block.type = Material.IRON_ORE
                    in 90..94 -> e.block.type = Material.EMERALD_ORE
                    else -> e.block.type = Material.DIAMOND_ORE
                }
            }
            else -> e.isCancelled = false
        }
    }
}