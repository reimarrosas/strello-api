package me.reimarrosas.strelloapi.utils;

public interface IMapper<TSrc, TDest> {
    TDest srcToDest(TSrc src);

    TSrc destToSrc(TDest dest);
}
