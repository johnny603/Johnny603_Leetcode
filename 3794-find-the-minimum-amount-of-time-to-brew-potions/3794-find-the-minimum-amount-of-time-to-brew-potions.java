class Solution {
  public long minTime(int[] skill, int[] mana) {
    // Total skill sum across all wizards.
    // For any potion, total brewing time if done sequentially = sumSkill * mana[j].
    long totalSkill = Arrays.stream(skill).sum();

    // Time when the last wizard finishes brewing the very first potion.
    long lastWizardFinishTime = totalSkill * mana[0];

    // Process each potion starting from the second one.
    for (int potionIndex = 1; potionIndex < mana.length; potionIndex++) {
      // The time when the previous potion (potionIndex - 1) was fully brewed.
      long prevPotionFinishTime = lastWizardFinishTime;

      // Iterate backwards through the wizards to update brewing times efficiently.
      for (int wizardIndex = skill.length - 2; wizardIndex >= 0; wizardIndex--) {
        // Move the timeline back by how long the next wizard (i + 1)
        // took to brew the previous potion.
        prevPotionFinishTime -= (long) skill[wizardIndex + 1] * mana[potionIndex - 1];

        // Compute when this wizard can start the current potion:
        // - It can't start before the previous potion is finished by the next wizard.
        // - It also can't start before this wizard finishes the previous potion.
        lastWizardFinishTime = Math.max(
          prevPotionFinishTime, 
          lastWizardFinishTime - (long) skill[wizardIndex] * mana[potionIndex]
        );
      }

      // After all wizards finish this potion,
      // add the total brewing time for the current potion.
      lastWizardFinishTime += totalSkill * mana[potionIndex];
    }

    // The final time when the last wizard finishes the last potion.
    return lastWizardFinishTime;
  }
}
