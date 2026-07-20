package net.mcreator.minexhunter.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.minexhunter.world.inventory.BettlePhoneMenu;
import net.mcreator.minexhunter.init.MineXHunterModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class BettlePhoneScreen extends AbstractContainerScreen<BettlePhoneMenu> implements MineXHunterModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_gps_coordenadas;
	private Button button_escaner_biologico;
	private Button button_llamar_mayordomo;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("mine_x_hunter:textures/screens/bettle_phone.png");

	public BettlePhoneScreen(BettlePhoneMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 222;
		this.imageHeight = 185;
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
		guiGraphics.drawString(this.font, Component.translatable("gui.mine_x_hunter.bettle_phone.label_beetle_os_v07"), 52, 15, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_gps_coordenadas = Button.builder(Component.translatable("gui.mine_x_hunter.bettle_phone.button_gps_coordenadas"), e -> {
		}).bounds(this.leftPos + 48, this.topPos + 44, 120, 20).build();
		this.addRenderableWidget(button_gps_coordenadas);
		button_escaner_biologico = Button.builder(Component.translatable("gui.mine_x_hunter.bettle_phone.button_escaner_biologico"), e -> {
		}).bounds(this.leftPos + 49, this.topPos + 84, 120, 20).build();
		this.addRenderableWidget(button_escaner_biologico);
		button_llamar_mayordomo = Button.builder(Component.translatable("gui.mine_x_hunter.bettle_phone.button_llamar_mayordomo"), e -> {
		}).bounds(this.leftPos + 51, this.topPos + 124, 115, 20).build();
		this.addRenderableWidget(button_llamar_mayordomo);
	}
}