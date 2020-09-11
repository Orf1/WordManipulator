package dev.orf1.worldmanipulator;

import net.minecraft.server.v1_16_R2.BlockPosition;
import net.minecraft.server.v1_16_R2.ChunkSection;
import net.minecraft.server.v1_16_R2.IBlockData;

public class Manipulator {
    private static IBlockData ibd;
    private static net.minecraft.server.v1_16_R2.World nmsWorld;

    public static void setIbd(IBlockData ibd2) {
        ibd = ibd2;
    }

    public static void setNmsWorld(net.minecraft.server.v1_16_R2.World nmsWorld2) {
        nmsWorld = nmsWorld2;
    }

    public static void setBlockInNativeWorld(int x, int y, int z, boolean applyPhysics) {
        BlockPosition bp = new BlockPosition(x, y, z);
        nmsWorld.setTypeAndData(bp, ibd, applyPhysics ? 3 : 2);
    }

    public static void setBlockInNativeChunk(int x, int y, int z, boolean applyPhysics) {
        net.minecraft.server.v1_16_R2.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        nmsChunk.setType(bp, ibd, applyPhysics);
    }

    public static void setBlockInNativeChunkSection(int x, int y, int z) {

        net.minecraft.server.v1_16_R2.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);

        ChunkSection cs = nmsChunk.getSections()[y >> 4];

        if (cs == nmsChunk.a() || cs == null) {
            cs = new ChunkSection(y);
            nmsChunk.getSections()[y >> 4] = cs;
        }

        cs.setType(x & 15, y & 15, z & 15, ibd);
    }

    public static void setBlockInNativeDataPalette(int x, int y, int z) {
        net.minecraft.server.v1_16_R2.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        ChunkSection cs = nmsChunk.getSections()[y >> 4];
        if (cs == nmsChunk.a() || cs == null) {
            cs = new ChunkSection(y);
            nmsChunk.getSections()[y >> 4] = cs;
        }
        cs.getBlocks().b(x & 15, y & 15, z & 15, ibd);
    }

    public static void setBlockInNativeChunkSection(int x, int y, int z, int blockId, byte data) {
        net.minecraft.server.v1_16_R2.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        IBlockData ibd = net.minecraft.server.v1_16_R2.Block.getByCombinedId(blockId + (data << 12));

        ChunkSection cs = nmsChunk.getSections()[y >> 4];
        if (cs == nmsChunk.a() || cs == null) {
            cs = new ChunkSection(y >> 4 << 4);
            nmsChunk.getSections()[y >> 4] = cs;
        }
        cs.setType(x & 15, y & 15, z & 15, ibd);
    }

    public static void setBlockInNativeDataPalette(int x, int y, int z, int blockId, byte data) {
        net.minecraft.server.v1_16_R2.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        IBlockData ibd = net.minecraft.server.v1_16_R2.Block.getByCombinedId(blockId + (data << 12));

        ChunkSection cs = nmsChunk.getSections()[y >> 4];
        if (cs == nmsChunk.a() || cs == null) {
            cs = new ChunkSection(y >> 4 << 4);
            nmsChunk.getSections()[y >> 4] = cs;
        }
        cs.getBlocks().b(x & 15, y & 15, z & 15, ibd);
    }

    public static void setBlockInNativeChunkSection(int x, int y, int z, int blockId) {
        net.minecraft.server.v1_16_R2.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        IBlockData ibd = net.minecraft.server.v1_16_R2.Block.getByCombinedId(blockId);

        ChunkSection cs = nmsChunk.getSections()[y >> 4];
        if (cs == nmsChunk.a() || cs == null) {
            cs = new ChunkSection(y >> 4 << 4);
            nmsChunk.getSections()[y >> 4] = cs;
        }
        cs.setType(x & 15, y & 15, z & 15, ibd);
    }

    public static void setBlockInNativeDataPalette(int x, int y, int z, int blockId) {
        net.minecraft.server.v1_16_R2.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        IBlockData ibd = net.minecraft.server.v1_16_R2.Block.getByCombinedId(blockId);

        ChunkSection cs = nmsChunk.getSections()[y >> 4];
        if (cs == nmsChunk.a() || cs == null) {
            cs = new ChunkSection(y >> 4 << 4);
            nmsChunk.getSections()[y >> 4] = cs;
        }
        cs.getBlocks().b(x & 15, y & 15, z & 15, ibd);
    }
}