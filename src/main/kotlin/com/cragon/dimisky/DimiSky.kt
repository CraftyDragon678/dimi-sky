package com.cragon.dimisky

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.random.Random

class DimiSky : JavaPlugin(), Listener {
    class GenerateData(val percent: Double, val block: Material)
    private lateinit var generate: EnumMap<Material, Set<GenerateData>>

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        generate = EnumMap(org.bukkit.Material::class.java)

        run{
            val tmp = HashSet<GenerateData>()
            tmp.add(GenerateData(1.0/6, Material.OAK_LOG))
            tmp.add(GenerateData(1.0/6, Material.SPRUCE_LOG))
            tmp.add(GenerateData(1.0/6, Material.BIRCH_LOG))
            tmp.add(GenerateData(1.0/6, Material.JUNGLE_LOG))
            tmp.add(GenerateData(1.0/6, Material.ACACIA_LOG))
            tmp.add(GenerateData(1.0/6, Material.DARK_OAK_LOG))
            generate[Material.GREEN_GLAZED_TERRACOTTA] = tmp
        }

        run{
            val tmp = HashSet<GenerateData>()
            tmp.add(GenerateData(1.0, Material.OAK_LOG))
            generate[Material.BROWN_GLAZED_TERRACOTTA] = tmp
        }

        run{
            val tmp = HashSet<GenerateData>()
            tmp.add(GenerateData(0.8, Material.STONE))
            tmp.add(GenerateData(0.2, Material.COAL_ORE))
            generate[Material.GRAY_GLAZED_TERRACOTTA] = tmp
        }

        run{
            val tmp = HashSet<GenerateData>()
            tmp.add(GenerateData(0.7, Material.STONE))
            tmp.add(GenerateData(0.2, Material.COAL_ORE))
            tmp.add(GenerateData(0.1, Material.IRON_ORE))
            generate[Material.LIME_GLAZED_TERRACOTTA] = tmp
        }

        run{
            val tmp = HashSet<GenerateData>()
            tmp.add(GenerateData(0.6, Material.STONE))
            tmp.add(GenerateData(0.2, Material.COAL_ORE))
            tmp.add(GenerateData(0.1, Material.IRON_ORE))
            tmp.add(GenerateData(0.05, Material.REDSTONE_ORE))
            tmp.add(GenerateData(0.05, Material.LAPIS_ORE))
            generate[Material.LIGHT_BLUE_GLAZED_TERRACOTTA] = tmp
        }

        run{
            val tmp = HashSet<GenerateData>()
            tmp.add(GenerateData(0.5, Material.STONE))
            tmp.add(GenerateData(0.2, Material.COAL_ORE))
            tmp.add(GenerateData(0.15, Material.IRON_ORE))
            tmp.add(GenerateData(0.05, Material.REDSTONE_ORE))
            tmp.add(GenerateData(0.05, Material.LAPIS_ORE))
            tmp.add(GenerateData(0.04, Material.EMERALD_ORE))
            tmp.add(GenerateData(0.01, Material.DIAMOND_ORE))
            generate[Material.MAGENTA_GLAZED_TERRACOTTA] = tmp
        }

        run{
            val tmp = HashSet<GenerateData>()
            tmp.add(GenerateData(0.6, Material.STONE))
            tmp.add(GenerateData(0.3, Material.IRON_ORE))
            tmp.add(GenerateData(0.05, Material.EMERALD_ORE))
            tmp.add(GenerateData(0.05, Material.DIAMOND_ORE))
            generate[Material.YELLOW_GLAZED_TERRACOTTA] = tmp
        }

    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val under = e.block.world.getBlockAt(e.block.x, e.block.y - 1, e.block.z)
        generate[under.type]?.let {
            val r = Random.nextInt(100).toDouble() / 100
            var cur = 0.0

            it.forEach {
                cur += it.percent
                if (cur > r) {
                    object: BukkitRunnable() {
                        override fun run() {
                            e.block.type = it.block
                        }
                    }.runTaskLater(this, 1L)
                    return
                }
            }
        }
    }
}