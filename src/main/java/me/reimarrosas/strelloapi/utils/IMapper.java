package me.reimarrosas.strelloapi.utils;

import java.util.Collection;

public interface IMapper<TSrc, TDest> {
    TDest srcToDest(TSrc src);

    TSrc destToSrc(TDest dest);

    Collection<TDest> srcToDest(Collection<TSrc> src);

    Collection<TSrc> destToSrc(Collection<TDest> dest);
}
