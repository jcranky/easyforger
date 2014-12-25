package com.easyforger.creatures

import cpw.mods.fml.common.registry.EntityRegistry
import net.minecraft.entity.{EntityLiving, EnumCreatureType}
import net.minecraft.entity.monster.{EntitySkeleton, EntityZombie, EntityCreeper}
import net.minecraft.world.biome.BiomeGenBase
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry

import scala.collection.JavaConverters._

class CreaturesHandler(creatures: Seq[CreatureConfig]) {
  val creeper: Option[CreeperConfig] = creatures.find(_.isInstanceOf[CreeperConfig]).map(_.asInstanceOf[CreeperConfig])

  /**
   * This should be called as soon as possible, to avoid the vanilla creatures to be ever used without the modded behavior.
   */
  def registerModdedVanillaCreatures(): Unit = {
    val allBiomes = BiomeGenBase.getBiomeGenArray.filterNot(_ == null)

    swapMonster(allBiomes, "CustomCreeper", 0x66ff99, 0x77ee55, classOf[EntityCreeper], classOf[CustomCreeper])
    swapMonster(allBiomes, "CustomZombie", 0x003333, 0x337734, classOf[EntityZombie], classOf[CustomZombie])
    swapMonster(allBiomes, "CustomSkeleton", 0x002266, 0x332266, classOf[EntitySkeleton], classOf[CustomSkeleton])
  }

  def swapMonster(allBiomes: Array[BiomeGenBase], monsterName: String, backgroundEggColour: Int, foregroundEggColour: Int,
                  monsterOldClass: Class[_ <: EntityLiving], monsterNewClass: Class[_ <: EntityLiving]) = {

    EntityRegistry.registerGlobalEntityID(monsterNewClass, monsterName, EntityRegistry.findGlobalUniqueEntityId(), backgroundEggColour, foregroundEggColour)

    val creeperBiomes = allBiomes.foldLeft(Map.empty[BiomeGenBase, SpawnListEntry]) { (biomes, biome) =>
      val entryOpt = biome.getSpawnableList(EnumCreatureType.monster).asScala.find(_.asInstanceOf[SpawnListEntry].entityClass == monsterOldClass)
      entryOpt.map(entry => biomes + (biome -> entry.asInstanceOf[SpawnListEntry])).getOrElse(biomes)
    }

    EntityRegistry.removeSpawn(monsterOldClass, EnumCreatureType.monster, creeperBiomes.keySet.toArray: _*)
    creeperBiomes.foreach { case (biome, entry) =>
      EntityRegistry.addSpawn(monsterNewClass, entry.itemWeight, entry.minGroupCount, entry.maxGroupCount, EnumCreatureType.monster, biome)
    }
  }
}