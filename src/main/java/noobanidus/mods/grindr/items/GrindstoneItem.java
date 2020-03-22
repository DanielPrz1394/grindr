package noobanidus.mods.grindr.items;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import noobanidus.mods.grindr.blocks.GrindstoneType;
import noobanidus.mods.grindr.config.ConfigManager;

import javax.annotation.Nullable;
import java.util.List;

public class GrindstoneItem extends Item {
  private final GrindstoneType type;

  public GrindstoneItem(Properties properties, GrindstoneType grindstone) {
    super(properties);
    this.type = grindstone;
  }

  public GrindstoneType getType() {
    return type;
  }

  @Override
  protected boolean isInGroup(ItemGroup group) {
/*    if (ConfigManager.isGrindstoneHidden(type.toString())) {
      return false;
    }*/
    return super.isInGroup(group);
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    super.addInformation(stack, worldIn, tooltip, flagIn);

    if (type != GrindstoneType.EMPTY) {
      double speed = type.getSpeedModifier() * 100;
      boolean neg = false;
      if (speed > 100) {
        speed = (speed - 100);
        neg = true;
      } else {
        speed = (speed - 100);
      }

      double multiplier = type.getResultModifier() * 100;
      boolean neg2 = false;
      if (multiplier < 100) {
        multiplier = (100 - multiplier);
        neg2 = true;
      } else {
        multiplier = -(100 - multiplier);
      }

      tooltip.add(new TranslationTextComponent("grinder.tooltip.grindstone.desc1", (neg ? TextFormatting.RED + "+" : TextFormatting.GREEN) + ((int) speed + "%")));
      tooltip.add(new TranslationTextComponent("grinder.tooltip.grindstone.desc2", (neg2 ? TextFormatting.RED: TextFormatting.GREEN + "+") + ((int) multiplier + "%")));

      if (Screen.hasShiftDown()) {
        tooltip.add(new TranslationTextComponent("grinder.tooltip.speed_desc"));
        tooltip.add(new TranslationTextComponent("grinder.tooltip.result_desc"));
      } else {
        tooltip.add(new TranslationTextComponent("grinder.tooltip.shift_for_more").setStyle(new Style().setColor(TextFormatting.GRAY)));
      }
    }
  }
}
