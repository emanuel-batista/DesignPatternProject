package com.designpattern.adapter;

import com.designpattern.legacy.ProcessarAlbunsLegacy;
import com.designpattern.model.Album;
import com.designpattern.services.ProcessadorDeAlbuns;

public class ProcessarAlbunsLegacyAdapter implements ProcessadorDeAlbuns{
    private ProcessarAlbunsLegacy processarAlbunsLegacy;
    public ProcessarAlbunsLegacyAdapter(ProcessarAlbunsLegacy processarAlbunsLegacy){
        this.processarAlbunsLegacy = processarAlbunsLegacy;
    }

    @Override
    public void processarAlbum(Album album) {
        int anoLancamento = album.getDataLancamento().toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .getYear();
        processarAlbunsLegacy.gravarAlbum(album.getNomeArtista(), album.getNome(), anoLancamento);
    }
}
