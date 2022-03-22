package com.faboslav.friendsandfoes.client.render.entity;

import com.faboslav.friendsandfoes.FriendsAndFoes;
import com.faboslav.friendsandfoes.client.render.entity.model.IceologerIceChunkModel;
import com.faboslav.friendsandfoes.entity.mob.IceologerIceChunkEntity;
import com.faboslav.friendsandfoes.init.ModEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
@SuppressWarnings({"rawtypes", "unchecked"})
public class IceologerIceChunkRenderer extends EntityRenderer<IceologerIceChunkEntity>
{
	private static final Identifier TEXTURE = FriendsAndFoes.makeID("textures/entity/illager/ice_chunk.png");
	private final IceologerIceChunkModel<IceologerIceChunkEntity> model;

	public IceologerIceChunkRenderer(Context context) {
		super(context);
		this.model = new IceologerIceChunkModel(context.getPart(ModEntityRenderer.ICEOLOGER_ICE_CHUNK_LAYER));
	}

	public void render(
		IceologerIceChunkEntity iceChunkEntity,
		float f,
		float g,
		MatrixStack matrixStack,
		VertexConsumerProvider vertexConsumerProvider,
		int i
	) {
		var summonAnimationProgress = MathHelper.lerp(
			MinecraftClient.getInstance().getTickDelta(),
			iceChunkEntity.getLastSummonAnimationProgress(),
			iceChunkEntity.getSummonAnimationProgress()
		);
		matrixStack.push();
		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
		matrixStack.scale(summonAnimationProgress, summonAnimationProgress, summonAnimationProgress);
		this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.pop();
		super.render(iceChunkEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}

	@Override
	public Identifier getTexture(IceologerIceChunkEntity entity) {
		return TEXTURE;
	}
}
