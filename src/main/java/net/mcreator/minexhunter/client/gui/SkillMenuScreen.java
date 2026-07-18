package net.mcreator.minexhunter.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.minexhunter.world.inventory.SkillMenuMenu;
import net.mcreator.minexhunter.network.SkillMenuButtonMessage;
import net.mcreator.minexhunter.init.MineXHunterModScreens;
import net.mcreator.minexhunter.MineXHunterMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class SkillMenuScreen extends AbstractContainerScreen<SkillMenuMenu> implements MineXHunterModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_comprar_garras_15_xp;
	private Button button_comprar_paso_de_sombra_25_xp;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("mine_x_hunter:textures/screens/skill_menu.png");

	public SkillMenuScreen(SkillMenuMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 263;
		this.imageHeight = 198;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.mine_x_hunter.skill_menu.label_habilidades_zoldyck"), 65, 5, -65536, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.mine_x_hunter.skill_menu.label_activar_desactivar_garras_tec"), 31, 53, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.mine_x_hunter.skill_menu.label_inmunidad_pasiva"), 81, 74, -6750055, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.mine_x_hunter.skill_menu.label_recibir_100_golpes_de_veneno_o_r"), 36, 92, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.mine_x_hunter.skill_menu.label_activar_desactivar_paso_de_som"), 10, 147, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_comprar_garras_15_xp = Button.builder(Component.translatable("gui.mine_x_hunter.skill_menu.button_comprar_garras_15_xp"), e -> {
			int x = SkillMenuScreen.this.x;
			int y = SkillMenuScreen.this.y;
			if (true) {
				MineXHunterMod.PACKET_HANDLER.sendToServer(new SkillMenuButtonMessage(0, x, y, z));
				SkillMenuButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 59, this.topPos + 25, 135, 20).build();
		this.addRenderableWidget(button_comprar_garras_15_xp);
		button_comprar_paso_de_sombra_25_xp = Button.builder(Component.translatable("gui.mine_x_hunter.skill_menu.button_comprar_paso_de_sombra_25_xp"), e -> {
			int x = SkillMenuScreen.this.x;
			int y = SkillMenuScreen.this.y;
			if (true) {
				MineXHunterMod.PACKET_HANDLER.sendToServer(new SkillMenuButtonMessage(1, x, y, z));
				SkillMenuButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 38, this.topPos + 116, 175, 20).build();
		this.addRenderableWidget(button_comprar_paso_de_sombra_25_xp);
	}
}