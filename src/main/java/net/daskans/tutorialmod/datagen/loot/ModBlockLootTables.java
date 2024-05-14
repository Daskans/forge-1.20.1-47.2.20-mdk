package net.daskans.tutorialmod.datagen.loot;

import net.daskans.tutorialmod.block.ModBlocks;
import net.daskans.tutorialmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());
        this.dropSelf(ModBlocks.STRAWBERRY_CRATE.get());

        this.add(ModBlocks.SAPPHIRE_ORE.get(),
                block -> createModOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get(), 1.0F, 1.0F));
        this.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                block -> createModOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get(), 1.0F, 1.0F));
        this.add(ModBlocks.END_STONE_SAPPHIRE_ORE.get(),
                block -> createModOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get(), 1.0F, 3.0F));
        this.add(ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                block -> createModOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get(), 0.0F, 1.0F));

        this.dropSelf(ModBlocks.SAPPHIRE_STAIRS.get());
        this.dropSelf(ModBlocks.SAPPHIRE_BUTTON.get());
        this.dropSelf(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_TRAPDOOR.get());
        this.dropSelf(ModBlocks.SAPPHIRE_FENCE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_WALL.get());

        this.add(ModBlocks.SAPPHIRE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.SAPPHIRE_SLAB.get()));
        this.add(ModBlocks.SAPPHIRE_DOOR.get(),
                block -> createDoorTable(ModBlocks.SAPPHIRE_SLAB.get()));
    }

    protected LootTable.Builder createModOreDrops(Block pBlock, Item item, float pMin, float pMax) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(pMin, pMax)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
