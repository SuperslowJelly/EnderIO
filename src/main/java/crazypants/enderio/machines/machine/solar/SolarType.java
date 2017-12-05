package crazypants.enderio.machines.machine.solar;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.enderio.core.common.util.NullHelper;

import crazypants.enderio.machines.config.config.SolarConfig;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public enum SolarType implements IStringSerializable {

  SIMPLE(""),
  ADVANCED(".advanced"),
  VIBRANT(".vibrant");

  public static final @Nonnull PropertyEnum<SolarType> KIND = PropertyEnum.<SolarType> create("kind", SolarType.class);

  private final @Nonnull String unlocalisedName;

  private SolarType(@Nonnull String unlocalisedName) {
    this.unlocalisedName = unlocalisedName;
  }

  public boolean connectTo(@Nonnull SolarType other) {
    return this == other;
  }

  @Override
  public @Nonnull String getName() {
    return NullHelper.notnullJ(name().toLowerCase(Locale.ENGLISH), "String.toLowerCase()");
  }

  public static @Nonnull SolarType getTypeFromMeta(int meta) {
    return NullHelper.notnullJ(values()[meta >= 0 && meta < values().length ? meta : 0], "Enum.values()");
  }

  public static int getMetaFromType(@Nonnull SolarType fusedQuartzType) {
    return fusedQuartzType.ordinal();
  }

  public @Nonnull String getUnlocalisedName() {
    return unlocalisedName;
  }

  public int getRfperTick() {
    switch (this) {
    case SIMPLE:
      return SolarConfig.solarPanelOneOutput.get();
    case ADVANCED:
      return SolarConfig.solarPanelTwoOutput.get();
    case VIBRANT:
      return SolarConfig.solarPanelThreeOutput.get();
    default:
      return 0;
    }
  }
}