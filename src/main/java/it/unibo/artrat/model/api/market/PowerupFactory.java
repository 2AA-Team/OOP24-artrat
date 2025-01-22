package it.unibo.artrat.model.api.market;

/** 
 * Every powerup has its own category.
 * 
*/
public interface PowerupFactory {

    AdvancedPowerup createAdvancedPowerup();    //NELL'ADVANCED POSSO METTERE MALUS, L'ADVANCED USA UN DECORATOR PATTERN

    LootPowerup createLootPowerup();    //IL POWERUP E' UNA PASSIVA PER IL LOOT

    SpeedPowerup createSpeedPowerup();  //IL POWERUP E' UNA PASSIVA PER LO SPEED

    StealthPowerup createStealthPowerup();  //LO STEALTH CONSISTE NELL'INVISIBILITA' A GUARDIE BASE, AVANZATE O TELECAMERE
}

