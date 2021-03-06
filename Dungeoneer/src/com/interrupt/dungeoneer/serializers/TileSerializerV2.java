package com.interrupt.dungeoneer.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.interrupt.dungeoneer.tiles.Tile;
import com.interrupt.dungeoneer.tiles.Tile.TileSpaceType;

public class TileSerializerV2 extends Serializer<Tile> {

	{
    	setAcceptsNull(true);
    }

	@Override
	public Tile read(Kryo kryo, Input input, Class<Tile> tileClass) {
		Tile tile = new Tile();
		
		tile.renderSolid = input.readBoolean();
		tile.blockMotion = input.readBoolean();
		tile.wallTex = input.readByte();
		tile.seen = input.readBoolean();
		
		if(!tile.renderSolid) {
			tile.canNav = input.readBoolean();
			tile.ceilTex = input.readByte();
			tile.floorTex = input.readByte();
			
			tile.ceilHeight = input.readFloat();
			tile.ceilSlopeNE = input.readFloat();
			tile.ceilSlopeNW = input.readFloat();
			tile.ceilSlopeSE = input.readFloat();
			tile.ceilSlopeSW = input.readFloat();
			
			tile.floorHeight = input.readFloat();
			tile.slopeNE = input.readFloat();
			tile.slopeNW = input.readFloat();
			tile.slopeSE = input.readFloat();
			tile.slopeSW = input.readFloat();
			
			byte wallBottomByte = input.readByte();
			if(wallBottomByte >= 0) tile.wallBottomTex = wallBottomByte;
			
			tile.ceilTexRot = input.readByte();
			tile.floorTexRot = input.readByte();
			
			tile.tileType = input.readByte();
			tile.tileSpaceType = TileSpaceType.values()[input.readByte()];
			
			tile.drawCeiling = input.readBoolean();
			tile.drawWalls = input.readBoolean();
		}

        byte northWallByte = input.readByte();
        if(northWallByte >= 0) tile.northTex = northWallByte;

        byte eastWallByte = input.readByte();
        if(eastWallByte >= 0) tile.eastTex = eastWallByte;

        byte southWallByte = input.readByte();
        if(southWallByte >= 0) tile.southTex = southWallByte;

        byte westWallByte = input.readByte();
        if(westWallByte >= 0) tile.westTex = westWallByte;
		
		return tile;
	}

	@Override
	public void write(Kryo kryo, Output output, Tile tile) {
		output.writeBoolean(tile.renderSolid);
		output.writeBoolean(tile.blockMotion);
		output.writeByte(tile.wallTex);
		output.writeBoolean(tile.seen);
		
		if(!tile.renderSolid) {
			output.writeBoolean(tile.canNav);
			output.writeByte(tile.ceilTex);
			output.writeByte(tile.floorTex);
			
			output.writeFloat(tile.ceilHeight);
			output.writeFloat(tile.ceilSlopeNE);
			output.writeFloat(tile.ceilSlopeNW);
			output.writeFloat(tile.ceilSlopeSE);
			output.writeFloat(tile.ceilSlopeSW);
			
			output.writeFloat(tile.floorHeight);
			output.writeFloat(tile.slopeNE);
			output.writeFloat(tile.slopeNW);
			output.writeFloat(tile.slopeSE);
			output.writeFloat(tile.slopeSW);
			
			if(tile.wallBottomTex != null) output.writeByte(tile.wallBottomTex);
			else output.writeByte(-1);
			
			output.writeByte(tile.ceilTexRot);
			output.writeByte(tile.floorTexRot);
			
			output.writeByte(tile.tileType);
			output.writeByte(tile.tileSpaceType.ordinal());
			
			output.writeBoolean(tile.drawCeiling);
			output.writeBoolean(tile.drawWalls);
		}

        if(tile.northTex != null) output.writeByte(tile.northTex);
        else output.writeByte(-1);

        if(tile.eastTex != null) output.writeByte(tile.eastTex);
        else output.writeByte(-1);

        if(tile.southTex != null) output.writeByte(tile.southTex);
        else output.writeByte(-1);

        if(tile.westTex != null) output.writeByte(tile.westTex);
        else output.writeByte(-1);
	}

}
