package speiger.src.tinymodularthings.client.models.storage;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTinyTank extends ModelBase
{
	// fields
	ModelRenderer Cube_Top_Front;
	ModelRenderer Cube_Top_Right;
	ModelRenderer Cube_Top_Left;
	ModelRenderer Cube_Top_Back;
	ModelRenderer Cube_Front_Bottom;
	ModelRenderer Cube_Front_Right;
	ModelRenderer Cube_Front_Left;
	ModelRenderer Cube_Front_Top;
	ModelRenderer Cube_Right_Bottom;
	ModelRenderer Cube_Right_Right;
	ModelRenderer Cube_Right_Left;
	ModelRenderer Cube_Right_Top;
	ModelRenderer Cube_Back_Bottom;
	ModelRenderer Cube_Back_Right;
	ModelRenderer Cube_Back_Left;
	ModelRenderer Cube_Back_Top;
	ModelRenderer Cube_Left_Bottom;
	ModelRenderer Cube_Left_Right;
	ModelRenderer Cube_Left_Left;
	ModelRenderer Cube_Left_Top;
	ModelRenderer Cube_Bottom_Front;
	ModelRenderer Cube_Bottom_Right;
	ModelRenderer Cube_Bottom_Left;
	ModelRenderer Cube_Bottom_Back;
	
	public ModelTinyTank()
	{
		textureWidth = 128;
		textureHeight = 128;
		
		Cube_Top_Front = new ModelRenderer(this, 78, 0);
		Cube_Top_Front.addBox(0F, 0F, 0F, 12, 2, 2);
		Cube_Top_Front.setRotationPoint(-6F, 8F, -6F);
		Cube_Top_Front.setTextureSize(64, 32);
		Cube_Top_Front.mirror = true;
		setRotation(Cube_Top_Front, 0F, 0F, 0F);
		Cube_Top_Right = new ModelRenderer(this, 107, 0);
		Cube_Top_Right.addBox(0F, 0F, 0F, 2, 2, 8);
		Cube_Top_Right.setRotationPoint(-6F, 8F, -4F);
		Cube_Top_Right.setTextureSize(64, 32);
		Cube_Top_Right.mirror = true;
		setRotation(Cube_Top_Right, 0F, 0F, 0F);
		Cube_Top_Left = new ModelRenderer(this, 107, 12);
		Cube_Top_Left.addBox(0F, 0F, 0F, 2, 2, 8);
		Cube_Top_Left.setRotationPoint(4F, 8F, -4F);
		Cube_Top_Left.setTextureSize(64, 32);
		Cube_Top_Left.mirror = true;
		setRotation(Cube_Top_Left, 0F, 0F, 0F);
		Cube_Top_Back = new ModelRenderer(this, 78, 6);
		Cube_Top_Back.addBox(0F, 0F, 0F, 12, 2, 2);
		Cube_Top_Back.setRotationPoint(-6F, 8F, 4F);
		Cube_Top_Back.setTextureSize(64, 32);
		Cube_Top_Back.mirror = true;
		setRotation(Cube_Top_Back, 0F, 0F, 0F);
		Cube_Front_Bottom = new ModelRenderer(this, 91, 30);
		Cube_Front_Bottom.addBox(0F, 0F, 0F, 16, 4, 2);
		Cube_Front_Bottom.setRotationPoint(-8F, 20F, -8F);
		Cube_Front_Bottom.setTextureSize(64, 32);
		Cube_Front_Bottom.mirror = true;
		setRotation(Cube_Front_Bottom, 0F, 0F, 0F);
		Cube_Front_Right = new ModelRenderer(this, 78, 30);
		Cube_Front_Right.addBox(0F, 0F, 0F, 4, 8, 2);
		Cube_Front_Right.setRotationPoint(-8F, 12F, -8F);
		Cube_Front_Right.setTextureSize(64, 32);
		Cube_Front_Right.mirror = true;
		setRotation(Cube_Front_Right, 0F, 0F, 0F);
		Cube_Front_Left = new ModelRenderer(this, 78, 42);
		Cube_Front_Left.addBox(0F, 0F, 0F, 4, 8, 2);
		Cube_Front_Left.setRotationPoint(4F, 12F, -8F);
		Cube_Front_Left.setTextureSize(64, 32);
		Cube_Front_Left.mirror = true;
		setRotation(Cube_Front_Left, 0F, 0F, 0F);
		Cube_Front_Top = new ModelRenderer(this, 91, 38);
		Cube_Front_Top.addBox(0F, 0F, 0F, 16, 4, 2);
		Cube_Front_Top.setRotationPoint(-8F, 8F, -8F);
		Cube_Front_Top.setTextureSize(64, 32);
		Cube_Front_Top.mirror = true;
		setRotation(Cube_Front_Top, 0F, 0F, 0F);
		Cube_Right_Bottom = new ModelRenderer(this, 99, 58);
		Cube_Right_Bottom.addBox(0F, 0F, 0F, 2, 4, 12);
		Cube_Right_Bottom.setRotationPoint(-8F, 20F, -6F);
		Cube_Right_Bottom.setTextureSize(64, 32);
		Cube_Right_Bottom.mirror = true;
		setRotation(Cube_Right_Bottom, 0F, 0F, 0F);
		Cube_Right_Right = new ModelRenderer(this, 90, 58);
		Cube_Right_Right.addBox(0F, 0F, 0F, 2, 8, 2);
		Cube_Right_Right.setRotationPoint(-8F, 12F, 4F);
		Cube_Right_Right.setTextureSize(64, 32);
		Cube_Right_Right.mirror = true;
		setRotation(Cube_Right_Right, 0F, 0F, 0F);
		Cube_Right_Left = new ModelRenderer(this, 90, 70);
		Cube_Right_Left.addBox(0F, 0F, 0F, 2, 8, 2);
		Cube_Right_Left.setRotationPoint(-8F, 12F, -6F);
		Cube_Right_Left.setTextureSize(64, 32);
		Cube_Right_Left.mirror = true;
		setRotation(Cube_Right_Left, 0F, 0F, 0F);
		Cube_Right_Top = new ModelRenderer(this, 99, 76);
		Cube_Right_Top.addBox(0F, 0F, 0F, 2, 4, 12);
		Cube_Right_Top.setRotationPoint(-8F, 8F, -6F);
		Cube_Right_Top.setTextureSize(64, 32);
		Cube_Right_Top.mirror = true;
		setRotation(Cube_Right_Top, 0F, 0F, 0F);
		Cube_Back_Bottom = new ModelRenderer(this, 91, 97);
		Cube_Back_Bottom.addBox(0F, 0F, 0F, 16, 4, 2);
		Cube_Back_Bottom.setRotationPoint(-8F, 20F, 6F);
		Cube_Back_Bottom.setTextureSize(64, 32);
		Cube_Back_Bottom.mirror = true;
		setRotation(Cube_Back_Bottom, 0F, 0F, 0F);
		Cube_Back_Right = new ModelRenderer(this, 78, 97);
		Cube_Back_Right.addBox(0F, 0F, 0F, 4, 8, 2);
		Cube_Back_Right.setRotationPoint(-8F, 12F, 6F);
		Cube_Back_Right.setTextureSize(64, 32);
		Cube_Back_Right.mirror = true;
		setRotation(Cube_Back_Right, 0F, 0F, 0F);
		Cube_Back_Left = new ModelRenderer(this, 78, 109);
		Cube_Back_Left.addBox(0F, 0F, 0F, 4, 8, 2);
		Cube_Back_Left.setRotationPoint(4F, 12F, 6F);
		Cube_Back_Left.setTextureSize(64, 32);
		Cube_Back_Left.mirror = true;
		setRotation(Cube_Back_Left, 0F, 0F, 0F);
		Cube_Back_Top = new ModelRenderer(this, 91, 105);
		Cube_Back_Top.addBox(0F, 0F, 0F, 16, 4, 2);
		Cube_Back_Top.setRotationPoint(-8F, 8F, 6F);
		Cube_Back_Top.setTextureSize(64, 32);
		Cube_Back_Top.mirror = true;
		setRotation(Cube_Back_Top, 0F, 0F, 0F);
		Cube_Left_Bottom = new ModelRenderer(this, 46, 0);
		Cube_Left_Bottom.addBox(0F, 0F, 0F, 2, 4, 12);
		Cube_Left_Bottom.setRotationPoint(6F, 20F, -6F);
		Cube_Left_Bottom.setTextureSize(64, 32);
		Cube_Left_Bottom.mirror = true;
		setRotation(Cube_Left_Bottom, 0F, 0F, 0F);
		Cube_Left_Right = new ModelRenderer(this, 37, 0);
		Cube_Left_Right.addBox(0F, 0F, 0F, 2, 8, 2);
		Cube_Left_Right.setRotationPoint(6F, 12F, 4F);
		Cube_Left_Right.setTextureSize(64, 32);
		Cube_Left_Right.mirror = true;
		setRotation(Cube_Left_Right, 0F, 0F, 0F);
		Cube_Left_Left = new ModelRenderer(this, 37, 12);
		Cube_Left_Left.addBox(0F, 0F, 0F, 2, 8, 2);
		Cube_Left_Left.setRotationPoint(6F, 12F, -6F);
		Cube_Left_Left.setTextureSize(64, 32);
		Cube_Left_Left.mirror = true;
		setRotation(Cube_Left_Left, 0F, 0F, 0F);
		Cube_Left_Top = new ModelRenderer(this, 46, 18);
		Cube_Left_Top.addBox(0F, 0F, 0F, 2, 4, 12);
		Cube_Left_Top.setRotationPoint(6F, 8F, -6F);
		Cube_Left_Top.setTextureSize(64, 32);
		Cube_Left_Top.mirror = true;
		setRotation(Cube_Left_Top, 0F, 0F, 0F);
		Cube_Bottom_Front = new ModelRenderer(this, 46, 42);
		Cube_Bottom_Front.addBox(0F, 0F, 0F, 12, 2, 2);
		Cube_Bottom_Front.setRotationPoint(-6F, 22F, -6F);
		Cube_Bottom_Front.setTextureSize(64, 32);
		Cube_Bottom_Front.mirror = true;
		setRotation(Cube_Bottom_Front, 0F, 0F, 0F);
		Cube_Bottom_Right = new ModelRenderer(this, 25, 42);
		Cube_Bottom_Right.addBox(0F, 0F, 0F, 2, 2, 8);
		Cube_Bottom_Right.setRotationPoint(-6F, 22F, -4F);
		Cube_Bottom_Right.setTextureSize(64, 32);
		Cube_Bottom_Right.mirror = true;
		setRotation(Cube_Bottom_Right, 0F, 0F, 0F);
		Cube_Bottom_Left = new ModelRenderer(this, 25, 54);
		Cube_Bottom_Left.addBox(0F, 0F, 0F, 2, 2, 8);
		Cube_Bottom_Left.setRotationPoint(4F, 22F, -4F);
		Cube_Bottom_Left.setTextureSize(64, 32);
		Cube_Bottom_Left.mirror = true;
		setRotation(Cube_Bottom_Left, 0F, 0F, 0F);
		Cube_Bottom_Back = new ModelRenderer(this, 46, 48);
		Cube_Bottom_Back.addBox(0F, 0F, 0F, 12, 2, 2);
		Cube_Bottom_Back.setRotationPoint(-6F, 22F, 4F);
		Cube_Bottom_Back.setTextureSize(64, 32);
		Cube_Bottom_Back.mirror = true;
		setRotation(Cube_Bottom_Back, 0F, 0F, 0F);
	}
	
	public void render(float f5)
	{
		Cube_Top_Front.render(f5);
		Cube_Top_Right.render(f5);
		Cube_Top_Left.render(f5);
		Cube_Top_Back.render(f5);
		Cube_Front_Bottom.render(f5);
		Cube_Front_Right.render(f5);
		Cube_Front_Left.render(f5);
		Cube_Front_Top.render(f5);
		Cube_Right_Bottom.render(f5);
		Cube_Right_Right.render(f5);
		Cube_Right_Left.render(f5);
		Cube_Right_Top.render(f5);
		Cube_Back_Bottom.render(f5);
		Cube_Back_Right.render(f5);
		Cube_Back_Left.render(f5);
		Cube_Back_Top.render(f5);
		Cube_Left_Bottom.render(f5);
		Cube_Left_Right.render(f5);
		Cube_Left_Left.render(f5);
		Cube_Left_Top.render(f5);
		Cube_Bottom_Front.render(f5);
		Cube_Bottom_Right.render(f5);
		Cube_Bottom_Left.render(f5);
		Cube_Bottom_Back.render(f5);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Cube_Top_Front.render(f5);
		Cube_Top_Right.render(f5);
		Cube_Top_Left.render(f5);
		Cube_Top_Back.render(f5);
		Cube_Front_Bottom.render(f5);
		Cube_Front_Right.render(f5);
		Cube_Front_Left.render(f5);
		Cube_Front_Top.render(f5);
		Cube_Right_Bottom.render(f5);
		Cube_Right_Right.render(f5);
		Cube_Right_Left.render(f5);
		Cube_Right_Top.render(f5);
		Cube_Back_Bottom.render(f5);
		Cube_Back_Right.render(f5);
		Cube_Back_Left.render(f5);
		Cube_Back_Top.render(f5);
		Cube_Left_Bottom.render(f5);
		Cube_Left_Right.render(f5);
		Cube_Left_Left.render(f5);
		Cube_Left_Top.render(f5);
		Cube_Bottom_Front.render(f5);
		Cube_Bottom_Right.render(f5);
		Cube_Bottom_Left.render(f5);
		Cube_Bottom_Back.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
	
}
