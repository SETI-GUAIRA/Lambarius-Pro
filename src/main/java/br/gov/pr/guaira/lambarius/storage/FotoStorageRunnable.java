package br.gov.pr.guaira.lambarius.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.gov.pr.guaira.lambarius.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

  private MultipartFile[] files;
  private DeferredResult<FotoDTO> resultado;
  private FotoStorage fotoStorage;

  public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {
    this.files = files;
    this.resultado = resultado;
    this.fotoStorage = fotoStorage;
  }

  @Override
  public void run() {
    this.fotoStorage.salvar(files[0]);
  }
}
